package testmap.com.testmap.fragment;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Igor on 25.03.2016.
 */
public class MapFragment extends SupportMapFragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMarkerClickListener {

    private static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";

    Context activity;
    GoogleApiClient googleApiClient;

    GoogleMap map;
    Marker mMarker;
    Location lastLocation;

    public static MapFragment newInstance(int page) {
        MapFragment mapFragment = new MapFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        mapFragment.setArguments(arguments);
        return mapFragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        this.activity = getActivity();
        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(activity)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        map = getMap();
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.setMyLocationEnabled(true);
        map.setOnMarkerClickListener(this);
        map.setOnMapClickListener(latLng -> Toast.makeText(activity, latLng.latitude + "  " + latLng.latitude, Toast.LENGTH_LONG).show());
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        googleApiClient.connect();
        super.onStart();
    }

    @Override
    public void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (lastLocation != null && mMarker == null) {
            LatLng currentPosition = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
            mMarker = map.addMarker(new MarkerOptions().position(currentPosition));
            CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(currentPosition, 18);
            map.animateCamera(cu);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (marker.equals(mMarker)) {
            Toast.makeText(activity, "MY MARKER", Toast.LENGTH_LONG).show();
        }
        return false;
    }
}
