package au.gov.vic.ballarat.ballarat;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import au.gov.vic.ballarat.ballarat.R;
import au.gov.vic.ballarat.ballarat.pojo.EventItem;
import au.gov.vic.ballarat.ballarat.pojo.NewsItem;

public class EventItemActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        EventItem item = (EventItem)getIntent().getSerializableExtra("item");

        setTitle(item.getTitle());

        ImageView imageImageView = (ImageView) findViewById(R.id.event_image);
        String imageName = item.getImage();

        if (imageName.length() > 0) {
            Bitmap imageBitmap = Utils.getBitmapFromAsset(this, "images/" + item.getImage());
            imageImageView = (ImageView)findViewById(R.id.event_image);
            imageImageView.setImageBitmap(imageBitmap);
        }
        else {
            imageImageView.setVisibility(View.INVISIBLE);
        }

        ((TextView)findViewById(R.id.event_title)).setText(item.getTitle());
        ((TextView)findViewById(R.id.event_description)).setText(item.getDescription());
        ((TextView)findViewById(R.id.event_contact)).setText(item.getContactName());
        ((TextView)findViewById(R.id.event_email)).setText(Html.fromHtml(item.getEmail()));
        ((TextView)findViewById(R.id.event_website)).setText(item.getContactName());
        ((TextView)findViewById(R.id.event_facebook)).setText(item.getContactName());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.event, menu);
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
