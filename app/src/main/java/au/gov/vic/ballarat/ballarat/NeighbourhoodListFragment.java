package au.gov.vic.ballarat.ballarat;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

import au.gov.vic.ballarat.ballarat.dummy.DummyContent;
import au.gov.vic.ballarat.ballarat.pojo.NeighbourhoodItem;

public class NeighbourhoodListFragment extends ListFragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    private ArrayList<NeighbourhoodItem> mNeighbourhoodItems;

    // TODO: Rename and change types of parameters
    public static NeighbourhoodListFragment newInstance(int sectionNumber) {
        NeighbourhoodListFragment fragment = new NeighbourhoodListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NeighbourhoodListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mNeighbourhoodItems = Utils.loadNeighbourhoods(getActivity());
        setListAdapter(new NeighbourhoodArrayAdapter(getActivity(), R.layout.list_item_neighbourhoods, mNeighbourhoodItems));
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent intent = new Intent(getActivity(), NeighbourhoodActivity.class);
        intent.putExtra("item", mNeighbourhoodItems.get(position));
        startActivity(intent);
    }

    private class NeighbourhoodArrayAdapter extends ArrayAdapter<NeighbourhoodItem> {
        private final Activity activity;
        private final ArrayList<NeighbourhoodItem> mNeighbourhoodItems;

        public NeighbourhoodArrayAdapter(Context context, int resource, ArrayList<NeighbourhoodItem> items) {
            super(context, resource, items);
            this.activity = (Activity) context;
            this.mNeighbourhoodItems = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView = convertView;
            ViewHolder view;

            if(rowView == null) {
                // Get a new instance of the row layout view
                LayoutInflater inflater = activity.getLayoutInflater();
                rowView = inflater.inflate(R.layout.list_item_neighbourhoods, null);

                // Hold the view objects in an object, that way the don't need to be "re-  finded"
                view = new ViewHolder();
                view.suburbNameTextView = (TextView) rowView.findViewById(R.id.suburb_name);
                view.populationTextView = (TextView) rowView.findViewById(R.id.population);
                rowView.setTag(view);
            } else {
                view = (ViewHolder) rowView.getTag();
            }

            /** Set data to your Views. */
            NeighbourhoodItem item = mNeighbourhoodItems.get(position);
            view.suburbNameTextView.setText(item.getSuburb());
            view.populationTextView.setText(Float.toString(item.getArea()));

            return rowView;
        }

        @Override
        public int getCount() {
            return this.mNeighbourhoodItems.size();
        }

        private class ViewHolder {
            protected TextView populationTextView;
            protected TextView suburbNameTextView;

        }
    }
}
