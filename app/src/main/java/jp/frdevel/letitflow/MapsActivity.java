package jp.frdevel.letitflow;

import android.location.Location;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
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

    @Override
    public void onResume() {
        super.onResume();
        checkLocationSettings();
    }

    @Override
    public void initializeMap() {
        //mMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f));
        mMarker = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0)).visible(false));
        mMarker.setTitle("your position");
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
