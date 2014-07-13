package au.gov.vic.ballarat.ballarat;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import au.gov.vic.ballarat.ballarat.R;
import au.gov.vic.ballarat.ballarat.db.BallaratContentProviderClient;
import au.gov.vic.ballarat.ballarat.db.BallaratDB;

public class DirectoryCategoryActivity extends Activity {
    private SimpleCursorAdapter dataAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory_category);
        String categoryName = getIntent().getStringExtra("categoryName");
        setTitle(categoryName);
        displayListView(categoryName);
    }

    private void displayListView(String categoryName) {
//        Cursor cursor = BallaratContentProviderClient.getAllDirectory(this);
        Cursor cursor = BallaratContentProviderClient.getDirectoryWith_categoryName(this, categoryName);
        // The desired columns to be bound
        String[] columns = new String[] {
                BallaratDB.DIRECTORY_SERVICENAME_COLUMN,
                BallaratDB.DIRECTORY_DESCRIPTION_COLUMN,
        };

        // the XML defined views which the data will be bound to
        int[] to = new int[] {
                android.R.id.text1,
                android.R.id.text2
        };

        // create the adapter using the cursor pointing to the desired data
        //as well as the layout information
        dataAdapter = new SimpleCursorAdapter(
                this, android.R.layout.simple_list_item_2,
                cursor,
                columns,
                to,
                0);

        ListView listView = (ListView) findViewById(R.id.listView);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.directory_category, menu);
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
