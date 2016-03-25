package testmap.com.testmap.fragment;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.*;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import org.androidannotations.annotations.*;
import testmap.com.testmap.MainActivity;
import testmap.com.testmap.R;
import testmap.com.testmap.entity.MapPoint;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Igor on 25.03.2016.
 */
@EFragment(R.layout.f_map)
@OptionsMenu(R.menu.m_f_map)
public class MapFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMarkerClickListener {

    private static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";

    @InstanceState
    Bundle bundle;
    @InstanceState
    Location currentLocation;
    @InstanceState
    LatLngBounds visibleRegionBounds;

    @ViewById(R.id.mapView)
    MapView mapView;
    @ViewById(R.id.llAddMapPoint)
    LinearLayout llAddMapPoint;
    @ViewById(R.id.etTitle)
    EditText etTitle;
    @ViewById(R.id.etDescription)
    EditText etDescription;
    @ViewById(R.id.tvLat)
    TextView tvLat;
    @ViewById(R.id.tvLng)
    TextView tvLng;


    MainActivity activity;
    ImageView addMapPoint;

    List<MapPoint> mapPointList;
    HashMap<MapPoint, Marker> markers = new HashMap<MapPoint, Marker>();
    GoogleApiClient googleApiClient;

    GoogleMap map;
    Marker mMarker;

    @AfterViews
    protected void init() {
        mapView.onCreate(bundle);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        this.activity = (MainActivity) getActivity();
        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(activity)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        map = mapView.getMap();
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.setMyLocationEnabled(true);
        map.setOnMarkerClickListener(this);
        map.setOnCameraChangeListener(position ->
                visibleRegionBounds = map.getProjection().getVisibleRegion().latLngBounds);
        this.mapPointList = activity.mapPointList;
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        this.bundle = savedInstanceState;
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        googleApiClient.connect();
        super.onStart();
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    public void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        currentLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        /*if (currentLocation != null && mMarker == null) {
            LatLng currentPosition = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
            mMarker = map.addMarker(new MarkerOptions().position(currentPosition));
            CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(currentPosition, 18);
            map.animateCamera(cu);
        }*/
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @OptionsItem(R.id.miAddMapPoint)
    protected void addNewMapPoint() {
        llAddMapPoint.setVisibility(View.VISIBLE);
        activity.showHome(true);
    }

    @OptionsItem(android.R.id.home)
    protected void cancel() {
        llAddMapPoint.setVisibility(View.GONE);
        clearAddMapPointForm();
        activity.showHome(false);
    }

    @UiThread
    protected void clearAddMapPointForm() {
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (marker.equals(mMarker)) {
            Toast.makeText(activity, "MY MARKER", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    @Background
    public void updateMarkers() {
        for (MapPoint mapPoint : mapPointList) {
            Marker marker = markers.get(mapPoint);
            LatLng position = mapPoint.coordinates;
            if (marker == null) {
                markers.put(mapPoint, map.addMarker(new MarkerOptions().position(position)));
            } else {
                marker.setPosition(position);
            }
        }
    }


}
