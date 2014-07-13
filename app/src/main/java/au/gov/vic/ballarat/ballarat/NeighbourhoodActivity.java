package au.gov.vic.ballarat.ballarat;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import au.gov.vic.ballarat.ballarat.R;
import au.gov.vic.ballarat.ballarat.pojo.NeighbourhoodItem;

public class NeighbourhoodActivity extends Activity {
    private GoogleMap mMap;
    private NeighbourhoodItem mNeighbourhoodItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbourhood);

        mNeighbourhoodItem = (NeighbourhoodItem)getIntent().getSerializableExtra("item");

        setTitle(mNeighbourhoodItem.getSuburb());

        setUpMapIfNeeded();

        populateViews();
    }

    @Override
    protected void onResume() {
        super.onResume();

        setUpMapIfNeeded();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.neighbourhood, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                // The Map is verified. It is now safe to manipulate the map.

                LatLng latLng = new LatLng(mNeighbourhoodItem.getMap().getViewLat(),mNeighbourhoodItem.getMap().getViewLng());
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, mNeighbourhoodItem.getMap().getZoom()));

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(mNeighbourhoodItem.getMap().getLat(),mNeighbourhoodItem.getMap().getLng()))
                        .title(mNeighbourhoodItem.getSuburb()));
            }
        }
    }

    private void populateViews() {
        TextView suburbTextView = (TextView)findViewById(R.id.neighbourhood_suburb);
        TextView descriptionTextView = (TextView)findViewById(R.id.neighbourhood_description);
        TextView populationTextView = (TextView)findViewById(R.id.neighbourhood_population);
        TextView densityTextView = (TextView)findViewById(R.id.neighbourhood_density);
        TextView areaTextView = (TextView)findViewById(R.id.neighbourhood_area);
        ImageView landUseImageView = (ImageView)findViewById(R.id.neighbourhood_land_use);

        suburbTextView.setText(mNeighbourhoodItem.getSuburb());
        descriptionTextView.setText(mNeighbourhoodItem.getDescription());

        populationTextView.setText(Integer.toString(mNeighbourhoodItem.getPopulation()));
        densityTextView.setText(Float.toString(mNeighbourhoodItem.getPopulationDensity()) + " p/sq.km");
        areaTextView.setText(Float.toString(mNeighbourhoodItem.getArea()) + " sq.km");

        landUseImageView.setImageBitmap(Utils.getBitmapFromAsset(this, "images/stats/statsLandUse-Ballarat.png"));
    }
}
