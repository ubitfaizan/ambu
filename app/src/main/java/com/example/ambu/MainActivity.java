package com.example.ambu;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ambu.Model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {


    Button btnSignIn, btnRegister;
    RelativeLayout rootLayout;

    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("Fonts/Arkhip_font.tff")
                .setFontAttrId(R.attr.fontPath).build());


        //Init Firebase
        auth = FirebaseAuth.getInstance();
        db= FirebaseDatabase.getInstance();
        users= db.getReference("Users");


        setContentView(R.layout.activity_main);
        //Init View
        btnRegister= (Button)findViewById(R.id.btnRegister);
        btnSignIn= (Button)findViewById(R.id.btnSignIn);
        rootLayout=(RelativeLayout)findViewById(R.id.rootLayout);
        //Event
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegisterDialog();
            }
        });
1
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoginDialog();
            }
        });


    }

    private void showLoginDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Sign In");
        dialog.setMessage("Please use Email to Sign In");

        LayoutInflater inflater= LayoutInflater.from(this);
        View login_layout= inflater.inflate(R.layout.layout_login,null);
        final MaterialEditText editMail=login_layout.findViewById(R.id.editEmail);
        final MaterialEditText editPassword=login_layout.findViewById(R.id.editPassword);

        dialog.setView(login_layout);
        //set Button
        dialog.setPositiveButton("Sign In", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
                btnSignIn.setEnabled(false);
               /* ProgressBar waitingDialog = new ProgressBar(MainActivity.this);
                waitingDialog.showContextMenu();


                *//*AlertDialog waiting_dialog = new SpotsDialog.Builder()
                        .setContext(this)
                        .setMessage(R.string.app_name)
                        .setCancellable(false)
                        .build();*/

                //Check Valiadation
                if (TextUtils.isEmpty(editMail.getText().toString())) {
                    Snackbar.make(rootLayout, "Please enter Email Address", Snackbar.LENGTH_SHORT).show();

                    return;
                }

                if (TextUtils.isEmpty(editPassword.getText().toString())) {
                    Snackbar.make(rootLayout, "Please enter Password", Snackbar.LENGTH_SHORT).show();

                    return;
                }
                if (editPassword.getText().toString().length() < 6) {
                    Snackbar.make(rootLayout, "Password too short !!!", Snackbar.LENGTH_SHORT).show();

                    return;
                }

               /* AlertDialog waitingDialog= new  SpotsDialog(MainActivity.this);*/

                auth.signInWithEmailAndPassword(editMail.getText().toString(), editPassword.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                startActivity(new Intent(MainActivity.this, Welcome.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(rootLayout, "Failed " + e.getMessage(), Snackbar.LENGTH_SHORT)
                                .show();

                        //Active Button
                        btnSignIn.setEnabled(true);
                    }
                })
                ;

            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });
        dialog.show();

//idher se shuru krna hai kaaam karna 36:09



    }

    private void showRegisterDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("REGISTER");
        dialog.setMessage("Please use Email to register");

        LayoutInflater inflater= LayoutInflater.from(this);
        View register_layout= inflater.inflate(R.layout.layout_register,null);
        final MaterialEditText editMail=register_layout.findViewById(R.id.editEmail);
        final MaterialEditText editPassword=register_layout.findViewById(R.id.editPassword);
        final MaterialEditText editName=register_layout.findViewById(R.id.editName);
        final MaterialEditText editPhone=register_layout.findViewById(R.id.editPhone);


        dialog.setView(register_layout);
        //set Button
        dialog.setPositiveButton("REGISTER", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();

                //Check Valiadation
                if (TextUtils.isEmpty(editMail.getText().toString())) {
                    Snackbar.make(rootLayout, "Please enter Email Address", Snackbar.LENGTH_SHORT).show();

                            return;
                }
                if (TextUtils.isEmpty(editPhone.getText().toString())) {
                    Snackbar.make(rootLayout, "Please enter Phone Number", Snackbar.LENGTH_SHORT).show();

                    return;
                }
                if (TextUtils.isEmpty(editPassword.getText().toString())) {
                    Snackbar.make(rootLayout, "Please enter Password", Snackbar.LENGTH_SHORT).show();

                    return;
                }
                if (editPassword.getText().toString().length()< 6) {
                    Snackbar.make(rootLayout, "Password too short !!!", Snackbar.LENGTH_SHORT).show();

                    return;
                }
                //Register New User
                auth.createUserWithEmailAndPassword(editMail.getText().toString(),editPassword.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                            User user=new User();
                            user.setEmail(editMail.getText().toString());
                            user.setName(editName.getText().toString());
                            user.setPhone(editPhone.getText().toString());
                            user.setPassword(editPassword.getText().toString());


                            // use email to key
                            users.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Snackbar.make(rootLayout, "Registered Sucessfully", Snackbar.LENGTH_SHORT).show();

                                            return;
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Snackbar.make(rootLayout, "Failed"+e.getMessage(), Snackbar.LENGTH_SHORT).show();

                                    return;
                                }
                            });





                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(rootLayout, "Failed"+e.getMessage(), Snackbar.LENGTH_SHORT).show();

                        return;
                    }
                });

            }
        });
        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
             dialogInterface.dismiss();
            }
        });
        dialog.show();






    }
}
