package au.gov.vic.ballarat.ballarat.db;

import android.content.*;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import java.util.ArrayList;

public final class BallaratContentProviderClient {

  /**
   * directory OPERATIONS
   */


  public static Cursor getDirectoryWith_id(Context c, int _id) {
    ContentResolver cr = c.getContentResolver();
    return cr.query(BallaratContentProvider.DIRECTORY_URI, null, BallaratDB.DIRECTORY__ID_COLUMN + "=?", new String[]{String.valueOf(_id)},null);
  }

    public static Cursor getDirectoryWith_categoryName(Context c, String categoryName) {
        ContentResolver cr = c.getContentResolver();
        return cr.query(BallaratContentProvider.DIRECTORY_URI, null, BallaratDB.DIRECTORY_CATEGORYNAME_COLUMN + "=?", new String[]{categoryName},null);
    }

  public static Uri addDirectory(Context c, String categoryName, String subCategoryName, String serviceName, String description, String activities, String contactName, String openingHours, String mailingAddress, String streetAddress, String businessPhone, String mobilePhone, String email, String website, String fax) {
    ContentValues contentValues = new ContentValues();
    contentValues.put(BallaratDB.DIRECTORY_CATEGORYNAME_COLUMN,categoryName);
    contentValues.put(BallaratDB.DIRECTORY_SUBCATEGORYNAME_COLUMN,subCategoryName);
    contentValues.put(BallaratDB.DIRECTORY_SERVICENAME_COLUMN,serviceName);
    contentValues.put(BallaratDB.DIRECTORY_DESCRIPTION_COLUMN,description);
    contentValues.put(BallaratDB.DIRECTORY_ACTIVITIES_COLUMN,activities);
    contentValues.put(BallaratDB.DIRECTORY_CONTACTNAME_COLUMN,contactName);
    contentValues.put(BallaratDB.DIRECTORY_OPENINGHOURS_COLUMN,openingHours);
    contentValues.put(BallaratDB.DIRECTORY_MAILINGADDRESS_COLUMN,mailingAddress);
    contentValues.put(BallaratDB.DIRECTORY_STREETADDRESS_COLUMN,streetAddress);
    contentValues.put(BallaratDB.DIRECTORY_BUSINESSPHONE_COLUMN,businessPhone);
    contentValues.put(BallaratDB.DIRECTORY_MOBILEPHONE_COLUMN,mobilePhone);
    contentValues.put(BallaratDB.DIRECTORY_EMAIL_COLUMN,email);
    contentValues.put(BallaratDB.DIRECTORY_WEBSITE_COLUMN,website);
    contentValues.put(BallaratDB.DIRECTORY_FAX_COLUMN,fax);
    ContentResolver cr = c.getContentResolver();
    return cr.insert(BallaratContentProvider.DIRECTORY_URI, contentValues);
  }

  public static int removeDirectoryWith_id(Context c, int _id) {
    ContentResolver cr = c.getContentResolver();
    return cr.delete(BallaratContentProvider.DIRECTORY_URI, BallaratDB.DIRECTORY__ID_COLUMN + "=?", new String[]{String.valueOf(_id)});
  }

  public static int removeAllDirectory(Context c) {
    ContentResolver cr = c.getContentResolver();
    return cr.delete(BallaratContentProvider.DIRECTORY_URI, null, null);
  }

  public static Cursor getAllDirectory(Context c) {
    ContentResolver cr = c.getContentResolver();
    String[] result_columns = new String[]{
        BallaratDB.DIRECTORY__ID_COLUMN,
        BallaratDB.DIRECTORY_CATEGORYNAME_COLUMN,
        BallaratDB.DIRECTORY_SUBCATEGORYNAME_COLUMN,
        BallaratDB.DIRECTORY_SERVICENAME_COLUMN,
        BallaratDB.DIRECTORY_DESCRIPTION_COLUMN,
        BallaratDB.DIRECTORY_ACTIVITIES_COLUMN,
        BallaratDB.DIRECTORY_CONTACTNAME_COLUMN,
        BallaratDB.DIRECTORY_OPENINGHOURS_COLUMN,
        BallaratDB.DIRECTORY_MAILINGADDRESS_COLUMN,
        BallaratDB.DIRECTORY_STREETADDRESS_COLUMN,
        BallaratDB.DIRECTORY_BUSINESSPHONE_COLUMN,
        BallaratDB.DIRECTORY_MOBILEPHONE_COLUMN,
        BallaratDB.DIRECTORY_EMAIL_COLUMN,
        BallaratDB.DIRECTORY_WEBSITE_COLUMN,
        BallaratDB.DIRECTORY_FAX_COLUMN,
        };
    String where = null;
    String whereArgs[] = null;
    String order = null;
    Cursor resultCursor = cr.query(BallaratContentProvider.DIRECTORY_URI, result_columns, where, whereArgs, order);
    return resultCursor;
  }

  public static int updateDirectory(Context c, int _id, String categoryName, String subCategoryName, String serviceName, String description, String activities, String contactName, String openingHours, String mailingAddress, String streetAddress, String businessPhone, String mobilePhone, String email, String website, String fax) {
    ContentValues contentValues = new ContentValues();
    contentValues.put(BallaratDB.DIRECTORY_CATEGORYNAME_COLUMN,categoryName);
    contentValues.put(BallaratDB.DIRECTORY_SUBCATEGORYNAME_COLUMN,subCategoryName);
    contentValues.put(BallaratDB.DIRECTORY_SERVICENAME_COLUMN,serviceName);
    contentValues.put(BallaratDB.DIRECTORY_DESCRIPTION_COLUMN,description);
    contentValues.put(BallaratDB.DIRECTORY_ACTIVITIES_COLUMN,activities);
    contentValues.put(BallaratDB.DIRECTORY_CONTACTNAME_COLUMN,contactName);
    contentValues.put(BallaratDB.DIRECTORY_OPENINGHOURS_COLUMN,openingHours);
    contentValues.put(BallaratDB.DIRECTORY_MAILINGADDRESS_COLUMN,mailingAddress);
    contentValues.put(BallaratDB.DIRECTORY_STREETADDRESS_COLUMN,streetAddress);
    contentValues.put(BallaratDB.DIRECTORY_BUSINESSPHONE_COLUMN,businessPhone);
    contentValues.put(BallaratDB.DIRECTORY_MOBILEPHONE_COLUMN,mobilePhone);
    contentValues.put(BallaratDB.DIRECTORY_EMAIL_COLUMN,email);
    contentValues.put(BallaratDB.DIRECTORY_WEBSITE_COLUMN,website);
    contentValues.put(BallaratDB.DIRECTORY_FAX_COLUMN,fax);
    Uri rowURI = ContentUris.withAppendedId(BallaratContentProvider.DIRECTORY_URI,_id);
    String where = null;
    String whereArgs[] = null;
    ContentResolver cr = c.getContentResolver();
    return cr.update(rowURI, contentValues, where, whereArgs);
  }

  public static int updateDirectoryWhere_id(Context c, int _id, String categoryName, String subCategoryName, String serviceName, String description, String activities, String contactName, String openingHours, String mailingAddress, String streetAddress, String businessPhone, String mobilePhone, String email, String website, String fax) {
    ContentValues contentValues = new ContentValues();
    contentValues.put(BallaratDB.DIRECTORY_CATEGORYNAME_COLUMN,categoryName);
    contentValues.put(BallaratDB.DIRECTORY_SUBCATEGORYNAME_COLUMN,subCategoryName);
    contentValues.put(BallaratDB.DIRECTORY_SERVICENAME_COLUMN,serviceName);
    contentValues.put(BallaratDB.DIRECTORY_DESCRIPTION_COLUMN,description);
    contentValues.put(BallaratDB.DIRECTORY_ACTIVITIES_COLUMN,activities);
    contentValues.put(BallaratDB.DIRECTORY_CONTACTNAME_COLUMN,contactName);
    contentValues.put(BallaratDB.DIRECTORY_OPENINGHOURS_COLUMN,openingHours);
    contentValues.put(BallaratDB.DIRECTORY_MAILINGADDRESS_COLUMN,mailingAddress);
    contentValues.put(BallaratDB.DIRECTORY_STREETADDRESS_COLUMN,streetAddress);
    contentValues.put(BallaratDB.DIRECTORY_BUSINESSPHONE_COLUMN,businessPhone);
    contentValues.put(BallaratDB.DIRECTORY_MOBILEPHONE_COLUMN,mobilePhone);
    contentValues.put(BallaratDB.DIRECTORY_EMAIL_COLUMN,email);
    contentValues.put(BallaratDB.DIRECTORY_WEBSITE_COLUMN,website);
    contentValues.put(BallaratDB.DIRECTORY_FAX_COLUMN,fax);
    Uri rowURI = BallaratContentProvider.DIRECTORY_URI;
    String where = BallaratDB.DIRECTORY__ID_COLUMN + "=?";
    String whereArgs[] = new String[]{String.valueOf(_id)};
    ContentResolver cr = c.getContentResolver();
    return cr.update(rowURI, contentValues, where, whereArgs);
  }

  /**
   * Simple get operations for Views
   */
}
