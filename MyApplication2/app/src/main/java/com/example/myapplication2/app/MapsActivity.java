package com.example.myapplication2.app;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.*;

import java.util.ArrayList;
import java.util.Arrays;

public class MapsActivity extends FragmentActivity implements GooglePlayServicesClient.ConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private LocationClient mlocc;
    private MarkerOptions me;
    private TileOverlay to;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
        mMap.setMyLocationEnabled(true);
        //connect();

        mlocc = new LocationClient(this,this,this);
        mlocc.connect();
        //*
        TileProvider tp= new TileProvider() {
            private final int max =10;
            private Arrays[][][] tiles;

            //public TileProvider(){
            //    tiles=new Arrays[max][max][max];
            //}

            @Override
            public Tile getTile(int i, int i1, int i2) {

                return null;
                //return tiles[i][i1][i2];
            }
        };


        to= mMap.addTileOverlay(new TileOverlayOptions().tileProvider(tp));

        class update implements LocationListener{


             @Override
             public void onLocationChanged(Location location) {
                 Location cur = mlocc.getLastLocation();
                 to.remove();
                 me= new MarkerOptions().position(new LatLng(cur.getLatitude(),cur.getLongitude())).icon(BitmapDescriptorFactory.fromResource(R.raw.tank));
                 mMap.addMarker(me);

             }
         }
        //*/

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }



    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {

        //mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }

    @Override
    public void onConnected(Bundle bundle) {
        Location cur;


        cur = mlocc.getLastLocation();
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(cur.getLatitude(),cur.getLongitude()))
                .icon(BitmapDescriptorFactory.fromResource(R.raw.tankv2)));

    }

    @Override
    public void onDisconnected() {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
