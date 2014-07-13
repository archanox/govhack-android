package au.gov.vic.ballarat.ballarat;



import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.MapFragment;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class NeighbourhoodMapFragment extends MapFragment {


    public NeighbourhoodMapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_neighbourhood_map, container, false);
    }


}
