package au.gov.vic.ballarat.ballarat;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import au.gov.vic.ballarat.ballarat.pojo.NewsItem;

/**
 * Created by troy on 12/07/2014.
 */
public class Utils {
    public static ArrayList<NewsItem> loadNews(Context context) {
        try {
            InputStream raw = context.getAssets().open("json/news.json");
            Reader reader = new BufferedReader(new InputStreamReader(raw, "UTF8"));

            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<NewsItem>>() {}.getType();

            return gson.fromJson(reader, listType);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<NewsItem>();
    }

    public static Bitmap getBitmapFromAsset(Context context, String strName)
    {
        AssetManager assetManager = context.getAssets();
        InputStream istr = null;
        try {
            istr = assetManager.open(strName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(istr);
        return bitmap;
    }

    public static Bitmap ScaleBitmap(Bitmap bm, float scalingFactor) {
        int scaleHeight = (int) (bm.getHeight() * scalingFactor);
        int scaleWidth = (int) (bm.getWidth() * scalingFactor);

        return Bitmap.createScaledBitmap(bm, scaleWidth, scaleHeight, true);
    }
}
