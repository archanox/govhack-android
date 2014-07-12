package au.gov.vic.ballarat.ballarat;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
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

        if (imageName != null && imageName.length() > 0) {
            Bitmap imageBitmap = Utils.getBitmapFromAsset(this, "images/" + item.getImage());
            imageImageView = (ImageView)findViewById(R.id.event_image);
            imageImageView.setImageBitmap(imageBitmap);
        }
        else {
            imageImageView.setVisibility(View.INVISIBLE);
        }

        ((TextView)findViewById(R.id.event_title)).setText(Html.fromHtml(item.getTitle()));
        ((TextView)findViewById(R.id.event_date)).setText(item.getDate());
        ((TextView)findViewById(R.id.event_description)).setText(item.getDescription());
        ((TextView)findViewById(R.id.event_contact)).setText(item.getContactName());

        String email = item.getEmail();
        TextView emailTextView = (TextView)findViewById(R.id.event_email);
        if (email != null && email.length() > 0) {
            String emailLink = "<a href=\"mailto:" + item.getEmail() + "\">Send an Email</a>";
            emailTextView.setText(Html.fromHtml(emailLink));
            emailTextView.setMovementMethod(LinkMovementMethod.getInstance());
        }
        else {
            emailTextView.setText("N/A");
        }

        String website = item.getWebsite();
        TextView websiteTextView = (TextView)findViewById(R.id.event_website);
        if (website != null && website.length() > 0) {
            String websiteLink = "<a href=\""+ item.getWebsite() + "\">Visit Website</a>";
            websiteTextView.setText(Html.fromHtml(websiteLink));
            websiteTextView.setMovementMethod(LinkMovementMethod.getInstance());
        }
        else {
            websiteTextView.setText("N/A");
        }

        String facebookPage = item.getFacebookPage();
        TextView facebookTextView = (TextView)findViewById(R.id.event_facebook);
        if (facebookPage != null && facebookPage.length() > 0) {
            String facebookLink = "<a href=\""+ item.getFacebookPage() + "\">Like Us</a>";
            facebookTextView.setText(Html.fromHtml(facebookLink));
            facebookTextView.setMovementMethod(LinkMovementMethod.getInstance());
        }
        else {
            facebookTextView.setText("N/A");
        }

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
