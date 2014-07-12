package au.gov.vic.ballarat.ballarat;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import au.gov.vic.ballarat.ballarat.R;
import au.gov.vic.ballarat.ballarat.pojo.NewsItem;

public class NewsItemActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_item);

        NewsItem item = (NewsItem)getIntent().getSerializableExtra("item");

        setTitle(item.getTitle());

        TextView authorTextView = (TextView)findViewById(R.id.news_author);
        authorTextView.setText(item.getAuthor());

        TextView dateTextView = (TextView)findViewById(R.id.news_date);
        dateTextView.setText(item.getDate());

        TextView titleTextView = (TextView)findViewById(R.id.news_title);
        titleTextView.setText(item.getTitle());

        TextView bodyTextView = (TextView)findViewById(R.id.news_body);
        bodyTextView.setText(item.getBody());

        // Images

        Bitmap authorBitmap = Utils.getBitmapFromAsset(this, "images/" + item.getAuthor_pic());
        ImageView authorImageView = (ImageView)findViewById(R.id.news_author_pic);
        authorImageView.setImageBitmap(authorBitmap);

        String imageName = item.getImage();
        if (imageName.length() > 0) {
            Bitmap imageBitmap = Utils.getBitmapFromAsset(this, "images/" + item.getImage());
            ImageView imageImageView = (ImageView)findViewById(R.id.news_image);
            imageImageView.setImageBitmap(imageBitmap);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.news_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
