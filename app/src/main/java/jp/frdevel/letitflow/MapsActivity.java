package jp.frdevel.letitflow;

import android.location.Location;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


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
                .image(BitmapDescriptorFactory.fromResource(R.mipmap.hazard))
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

}
