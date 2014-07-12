package au.gov.vic.ballarat.ballarat;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import au.gov.vic.ballarat.ballarat.dummy.DummyContent;
import au.gov.vic.ballarat.ballarat.pojo.NewsItem;

public class NewsFragment extends ListFragment {

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NewsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<NewsItem> newsItems = Utils.loadNews(getActivity());
        setListAdapter(new NewsArrayAdapter(getActivity(), R.layout.list_item_news, newsItems));
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

    }

    private class NewsArrayAdapter extends ArrayAdapter<NewsItem> {
        private final Activity activity;
        private final ArrayList<NewsItem> list;

        public NewsArrayAdapter(Context context, int resource, ArrayList<NewsItem> items) {
            super(context, resource, items);
            this.activity = (Activity) context;
            this.list = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView = convertView;
            ViewHolder view;

            if(rowView == null) {
                // Get a new instance of the row layout view
                LayoutInflater inflater = activity.getLayoutInflater();
                rowView = inflater.inflate(R.layout.list_item_news, null);

                // Hold the view objects in an object, that way the don't need to be "re-  finded"
                view = new ViewHolder();
                view.authorTextView = (TextView) rowView.findViewById(R.id.news_author);
                view.dateTextView = (TextView) rowView.findViewById(R.id.news_date);
                view.titleTextView = (TextView) rowView.findViewById(R.id.news_title);

                view.authorImageView = (ImageView) rowView.findViewById(R.id.news_author_pic);
                view.imageImageView = (ImageView) rowView.findViewById(R.id.news_image);

                rowView.setTag(view);
            } else {
                view = (ViewHolder) rowView.getTag();
            }

            /** Set data to your Views. */
            NewsItem item = list.get(position);
            view.authorTextView.setText(item.getAuthor());
            view.dateTextView.setText(item.getDate());
            view.titleTextView.setText(item.getTitle());

            // Images
            Bitmap authorBitmap = Utils.getBitmapFromAsset(getActivity(), "images/" + item.getAuthor_pic());
            view.authorImageView.setImageBitmap(authorBitmap);

            String imageName = item.getImage();
            if (imageName.length() > 0) {
                Bitmap imageBitmap = Utils.getBitmapFromAsset(getActivity(), "images/" + item.getImage());
                view.imageImageView.setImageBitmap(imageBitmap);
            }
            return rowView;
        }

        @Override
        public int getCount() {
            return this.list.size();
        }

        private class ViewHolder {
            protected ImageView authorImageView;
            protected TextView authorTextView;
            protected TextView dateTextView;
            protected ImageView imageImageView;
            protected TextView titleTextView;

        }
    }
}
