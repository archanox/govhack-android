package au.gov.vic.ballarat.ballarat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.ListFragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

import au.gov.vic.ballarat.ballarat.pojo.EventItem;

public class EventListFragment extends ListFragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    private ArrayList<EventItem> mEventList;

    // TODO: Rename and change types of parameters
    public static EventListFragment newInstance(int sectionNumber) {
        EventListFragment fragment = new EventListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public EventListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mEventList = Utils.loadEvents(getActivity());
        setListAdapter(new EventsArrayAdapter(getActivity(), R.layout.list_item_events, mEventList));
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent intent = new Intent(getActivity(), EventItemActivity.class);
        intent.putExtra("item", mEventList.get(position));
        startActivity(intent);
    }

    private class EventsArrayAdapter extends ArrayAdapter<EventItem> {
        private final Activity activity;
        private final ArrayList<EventItem> mEventItems;

        public EventsArrayAdapter(Context context, int resource, ArrayList<EventItem> items) {
            super(context, resource, items);
            this.activity = (Activity) context;
            this.mEventItems = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView = convertView;
            ViewHolder view;

            if(rowView == null) {
                // Get a new instance of the row layout view
                LayoutInflater inflater = activity.getLayoutInflater();
                rowView = inflater.inflate(R.layout.list_item_events, null);

                // Hold the view objects in an object, that way the don't need to be "re-  finded"
                view = new ViewHolder();
                view.titleTextView = (TextView) rowView.findViewById(R.id.event_title);
                view.dateTextView = (TextView) rowView.findViewById(R.id.event_date);
                rowView.setTag(view);
            } else {
                view = (ViewHolder) rowView.getTag();
            }

            /** Set data to your Views. */
            EventItem item = mEventList.get(position);
            view.titleTextView.setText(Html.fromHtml(item.getTitle()));
            view.dateTextView.setText(item.getDate());

            return rowView;
        }

        @Override
        public int getCount() {
            return this.mEventItems.size();
        }

        private class ViewHolder {
            protected TextView titleTextView;
            protected TextView dateTextView;
        }
    }
}
