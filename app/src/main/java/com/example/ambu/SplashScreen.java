package com.example.ambu;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    /* private GifImageView  gifImageView;
     private ProgressBar   progressBar;
 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

    }
}

/*

        gifImageView =(GifImageView)findViewById(R.id.gifImageView);
        progressBar =(ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(progressBar.VISIBLE);

        try{
            InputStream inputStream = getAssets().open("ambuscreen.gif");
            byte[] bytes = IOUtils.toByteArray(inputStream);
            gifImageView.setBytes(bytes);
            gifImageView.startAnimation();
        }
        catch (
                IOException ex){

        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SplashScreen.this.startActivity(new Intent(SplashScreen.this,MainActivity.class));

                SplashScreen.this.finish();
            }

        },10000);
    }

    mapscode
    public class Welcome extends FragmentActivity implements OnMapReadyCallback,


        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener
{

    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationClient;
    //Play Services
    private static final int MY_PERMISSION_REQUEST_CODE= 7000;
    private static final int PLAY_SERVICE_REG_REQUEST= 7001;

    private LocationRequest nLocationRequest;
    private GoogleApiClient nGoogleApiClient;
    private Location nLastLocation;

    private static int UPDATE_INTERVAL=5000;
    private static int FATEST_INTERVAL=3000;
    private static int DISPLACEMENT =10;

    DatabaseReference drivers;
    GeoFire geoFire;

    Marker nCurrent;

    MaterialAnimatedSwitch location_switch;

    SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Init View
        location_switch=(MaterialAnimatedSwitch)findViewById(R.id.location_switch);
        location_switch.setOnCheckedChangeListener(new MaterialAnimatedSwitch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(boolean isOnline) {
                if(isOnline){
                    startLocationUpdates();
                    displayLocation();
                    Snackbar.make(mapFragment.getView(),"You are online",Snackbar.LENGTH_SHORT )
                        .show();
                }
                else{
                    stopLocationUpdates();
                    nCurrent.remove();
                    Snackbar.make(mapFragment.getView(),"You are offline",Snackbar.LENGTH_SHORT )
                            .show();
                }
            }
        });

        //Geo Fire Base
        drivers= FirebaseDatabase.getInstance().getReference("Drivers");
        geoFire= new GeoFire(drivers);

        setUpLocation();


    }

    private void setUpLocation() {

        if
            (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // Request Runtime Permission
                {
                    ActivityCompat.requestPermissions(this, new String[]{
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION
                    }, MY_PERMISSION_REQUEST_CODE);
                }
        }
            else
            {
               if(checkPlayServices())
               {
                   buildGoogleApiClient();
                   createLocationRequest();
                   if(location_switch.isChecked())
                       displayLocation();
               }
            }
        }

    private void createLocationRequest() {

    nLocationRequest=new LocationRequest();
        nLocationRequest.setFastestInterval(UPDATE_INTERVAL);
        nLocationRequest.setFastestInterval(FATEST_INTERVAL);
        nLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        nLocationRequest.setSmallestDisplacement(DISPLACEMENT);
    }

    private void buildGoogleApiClient() {
        nGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        nGoogleApiClient.connect();
    }

    private boolean checkPlayServices() {
        int resultCode= GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if(resultCode != ConnectionResult.SUCCESS)
        {
            if(GooglePlayServicesUtil.isUserRecoverableError(resultCode))
            GooglePlayServicesUtil.getErrorDialog(resultCode, this,PLAY_SERVICE_REG_REQUEST).show();
            else{
                Toast.makeText(this, "This device is not supported", Toast.LENGTH_SHORT).show();
                finish();
            }
            return false;
        }
        return true;
    }


    private void stopLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
     *//*   FusedLocationApi.removeLocationUpdates(nGoogleApiClient, (com.google.android.gms.location.LocationListener) this);*//*
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location currentlocation) {
                    }


                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("error", e.getLocalizedMessage());
            }
        });

    }

    private void displayLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        *//* nLastLocation= FusedLocationApi.getLastLocation(nGoogleApiClient);*//*
        //newCode

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location currentlocation) {
                    }


                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("error", e.getLocalizedMessage());
            }
        });


        if(nLastLocation != null){
            if(location_switch.isChecked())
            {
                final double latitude = nLastLocation.getLatitude();
                final double longitude= nLastLocation.getLongitude();
                //Update to Firebase
                geoFire.setLocation(FirebaseAuth.getInstance().getCurrentUser().getUid(), new GeoLocation(latitude, longitude), new GeoFire.CompletionListener() {
                    @Override
                    public void onComplete(String key, DatabaseError error) {
                        //Add Marker
                        if(nCurrent != null)
                            nCurrent.remove();
                        nCurrent= mMap.addMarker(new MarkerOptions()
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.iconambu))
                                .position(new LatLng(latitude,longitude))
                                .title("You"));
                        //Move camera to this position
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude),15.0f));

                        rotateMarker(nCurrent, -360,mMap);
                    }
                });


            }
            else{
                Log.d("ERROR", "Cannot get your location");
            }
        }

    }

   *//* sss
   fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location currentlocation) {
                                }
                    }


                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("error", e.getLocalizedMessage());
            }
        });

        private FusedLocationProviderClient fusedLocationClient;

sss*//*




    private void rotateMarker(final Marker nCurrent, final float i, GoogleMap mMap) {
        final Handler handler=new Handler();
        final long start= SystemClock.uptimeMillis();
        final float startRotation= nCurrent.getRotation();
        final long duration= 1500;

        final LinearInterpolator interpolator= new LinearInterpolator();
        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t= interpolator.getInterpolation((float)elapsed/duration);
                float rot=t*i+(1-t)*startRotation;
                nCurrent.setRotation(-rot > 100?rot/2:rot);
                if(t<1.0){
                    handler.postDelayed(this,16);
                }

            }
        });
    }

    private void startLocationUpdates() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        FusedLocationApi.requestLocationUpdates(nGoogleApiClient, nLocationRequest, (com.google.android.gms.location.LocationListener) this);


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case MY_PERMISSION_REQUEST_CODE:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)

                {
                    if(checkPlayServices())
                    {
                        buildGoogleApiClient();
                        createLocationRequest();
                        if(location_switch.isChecked())
                            displayLocation();
                    }
                }

        }

    }

    *//**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     *//*


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void onLocationChanged(Location location) {
        nLastLocation= location;
        displayLocation();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
        displayLocation();
        startLocationUpdates();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        nGoogleApiClient.connect();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
}

*/








