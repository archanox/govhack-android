package au.gov.vic.ballarat.ballarat.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public final class BallaratDB extends SQLiteOpenHelper {

  private static final String TAG = "BallaratDB";
  private static final String DATABASE_NAME = "BallaratDB.db";
  private static final int DATABASE_VERSION = 1;
  public static final String ROW_ID = "_id";

  // Table directory constants 
  public static final String DIRECTORY_TABLE = "directory";
  public static final String DIRECTORY__ID_COLUMN = "_id";
  public static final int DIRECTORY__ID_COLUMN_POSITION = 0;
  public static final String DIRECTORY_CATEGORYNAME_COLUMN = "categoryName";
  public static final int DIRECTORY_CATEGORYNAME_COLUMN_POSITION = 1;
  public static final String DIRECTORY_SUBCATEGORYNAME_COLUMN = "subCategoryName";
  public static final int DIRECTORY_SUBCATEGORYNAME_COLUMN_POSITION = 2;
  public static final String DIRECTORY_SERVICENAME_COLUMN = "serviceName";
  public static final int DIRECTORY_SERVICENAME_COLUMN_POSITION = 3;
  public static final String DIRECTORY_DESCRIPTION_COLUMN = "description";
  public static final int DIRECTORY_DESCRIPTION_COLUMN_POSITION = 4;
  public static final String DIRECTORY_ACTIVITIES_COLUMN = "activities";
  public static final int DIRECTORY_ACTIVITIES_COLUMN_POSITION = 5;
  public static final String DIRECTORY_CONTACTNAME_COLUMN = "contactName";
  public static final int DIRECTORY_CONTACTNAME_COLUMN_POSITION = 6;
  public static final String DIRECTORY_OPENINGHOURS_COLUMN = "openingHours";
  public static final int DIRECTORY_OPENINGHOURS_COLUMN_POSITION = 7;
  public static final String DIRECTORY_MAILINGADDRESS_COLUMN = "mailingAddress";
  public static final int DIRECTORY_MAILINGADDRESS_COLUMN_POSITION = 8;
  public static final String DIRECTORY_STREETADDRESS_COLUMN = "streetAddress";
  public static final int DIRECTORY_STREETADDRESS_COLUMN_POSITION = 9;
  public static final String DIRECTORY_BUSINESSPHONE_COLUMN = "businessPhone";
  public static final int DIRECTORY_BUSINESSPHONE_COLUMN_POSITION = 10;
  public static final String DIRECTORY_MOBILEPHONE_COLUMN = "mobilePhone";
  public static final int DIRECTORY_MOBILEPHONE_COLUMN_POSITION = 11;
  public static final String DIRECTORY_EMAIL_COLUMN = "email";
  public static final int DIRECTORY_EMAIL_COLUMN_POSITION = 12;
  public static final String DIRECTORY_WEBSITE_COLUMN = "website";
  public static final int DIRECTORY_WEBSITE_COLUMN_POSITION = 13;
  public static final String DIRECTORY_FAX_COLUMN = "fax";
  public static final int DIRECTORY_FAX_COLUMN_POSITION = 14;

  // directory create statement
  private static final String DATABASE_DIRECTORY_CREATE = "CREATE TABLE directory (" + 
			 "_id integer primary key autoincrement," + 
			 DIRECTORY_CATEGORYNAME_COLUMN + " text," + 
			 DIRECTORY_SUBCATEGORYNAME_COLUMN + " text," + 
			 DIRECTORY_SERVICENAME_COLUMN + " text," + 
			 DIRECTORY_DESCRIPTION_COLUMN + " text," + 
			 DIRECTORY_ACTIVITIES_COLUMN + " text," + 
			 DIRECTORY_CONTACTNAME_COLUMN + " text," + 
			 DIRECTORY_OPENINGHOURS_COLUMN + " text," + 
			 DIRECTORY_MAILINGADDRESS_COLUMN + " text," + 
			 DIRECTORY_STREETADDRESS_COLUMN + " text," + 
			 DIRECTORY_BUSINESSPHONE_COLUMN + " text," + 
			 DIRECTORY_MOBILEPHONE_COLUMN + " text," + 
			 DIRECTORY_EMAIL_COLUMN + " text," + 
			 DIRECTORY_WEBSITE_COLUMN + " text," + 
			 DIRECTORY_FAX_COLUMN + " text)";


  public BallaratDB(Context context) {
    super( context, DATABASE_NAME, null, DATABASE_VERSION );
  }


  // Called when no database exists on disk we need to create a new one.
  @Override
  public void onCreate(SQLiteDatabase db) {
    Log.d(TAG, "Creating a new Database. Current version " + DATABASE_VERSION);
    db.execSQL(DATABASE_DIRECTORY_CREATE);
  }

  // Called when there is a database version mismatch meaning that the version of the database on disk needs to be upgraded to the current version.
  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(TAG, "Upgrading from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");

    // The simplest case is to drop the old table and create a new one.
    db.execSQL("DROP TABLE IF EXISTS directory;");
    onCreate( db );
  }
}
