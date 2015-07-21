package jp.frdevel.letitflow;

import android.location.Location;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;


/**
 * Some important variables...
 * protected GoogleMap mMap;
 * protected Location mCurrentLocation;
 * protected String mLastUpdateTime;
 *
 * Some important callbacks...
 * public void onLocationChanged(Location location)
 * public void checkLocationSettings()
 * protected void initializeMap()
 * protected List<MapsDrawerItem> getSliderItem()
 * public void onItemClick(AdapterView<?> parent, View view, int pos, long id)
 * public boolean onOptionsItemSelected(MenuItem item)
 */
public class MapsActivity extends MapsActivityBase {

    protected final String TAG = "FireRubyDev MapGPSample";

    private Marker mMarker;
    private GroundOverlayOptions mOverlay;

    @Override
    public void onResume() {
        super.onResume();
        checkLocationSettings();
    }

    @Override
    public void initializeMap() {
        mMap.moveCamera(CameraUpdateFactory.zoomTo(15.0f));
        mMarker = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0)).visible(false));
        mMarker.setTitle("your position");
        // TODO Retrieve image from server: This is local
        LatLngBounds bounds = new LatLngBounds(
                new LatLng(  33.88480983,  134.6542902),
                new LatLng(  33.91108717,  134.6806122)
        );
        mOverlay = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.mipmap.hazard3))
                .positionFromBounds(bounds);
        mMap.addGroundOverlay(mOverlay);
    }

    @Override
    public void onLocationChanged(Location location) {
        super.onLocationChanged(location);
        Log.i(TAG, location.toString());

        if(!mMarker.isVisible())
            mMarker.setVisible(true);
        mMarker.setPosition(new LatLng(
                location.getLatitude(), location.getLongitude()));

        mMap.animateCamera(CameraUpdateFactory.newLatLng(
                new LatLng(location.getLatitude(), location.getLongitude())
        ));
    }

    @Override
    protected List<MapsDrawerItem> getSliderItem() {
        ArrayList<MapsDrawerItem> ret = new ArrayList<MapsDrawerItem>();
        ret.add(new MapsDrawerItem("Map", R.mipmap.icon_map));
        ret.add(new MapsDrawerItem("Config", R.mipmap.icon_config));
        ret.add(new MapsDrawerItem("About", R.mipmap.ic_launcher));
        return ret;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
        if(pos == 0) { // open map

        } else if(pos == 1) { // open config

        } else if(pos == 2) { // about
            Toast.makeText(this,
                    "Let it Flow version alpha",
                    Toast.LENGTH_LONG).show();
        }
    }

    // TODO need test
    private void drawPath(List<LatLng> path) {
        // Instantiates a new Polyline object and adds points to define a rectangle
        PolylineOptions pathOptions = new PolylineOptions();
        for(LatLng p: path) {
            pathOptions = pathOptions.add(p);
        }
        // Get back the mutable Polyline
        Polyline polyline = mMap.addPolyline(pathOptions);
    }

}
