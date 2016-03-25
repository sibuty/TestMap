package testmap.com.testmap.fragment;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by Igor on 25.03.2016.
 */
public class MapFragment extends SupportMapFragment {
    
    private static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    
    GoogleMap map;
    Marker mMarker;
    LatLng loc;

    public static MapFragment newInstance(int page) {
        MapFragment mapFragment = new MapFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        mapFragment.setArguments(arguments);
        return mapFragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        map = getMap();
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.setMyLocationEnabled(true);
        map.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                loc = new LatLng(location.getLatitude(), location.getLongitude());
                CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(loc, 18);
                map.animateCamera(cu);
            }
        });
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Toast.makeText(getActivity(), latLng.latitude + "  " + latLng.latitude, Toast.LENGTH_LONG).show();
            }
        });
        super.onActivityCreated(savedInstanceState);
    }
}
