package au.gov.vic.ballarat.ballarat.db;

import android.content.*;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.database.sqlite.SQLiteStatement;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Map;

public final class BallaratContentProvider extends ContentProvider {

  private static final String TAG = "BallaratContentProvider";
  private static final String DATABASE_NAME = "BallaratDB.db";
  private static final int DATABASE_VERSION = 1;
  private static final String ROW_ID = "_id";

  public static final String AUTHORITY = "au.gov.vic.ballarat.ballarat";

  private BallaratDB mLocalDatabase;

  // directory constants
  public static final String DIRECTORY_TABLE = "directory";
  public static final int ALLdirectory = 1;
  public static final int SINGLEdirectory = 2;
    public static final int CATEGORIES = 3;

  // views constants

  public static final Uri DIRECTORY_URI = Uri.parse("content://au.gov.vic.ballarat.ballarat/directory");
    public static final Uri CATEGORY_URI = Uri.parse("content://au.gov.vic.ballarat.ballarat/directory/categories");


  private static final UriMatcher uriMatcher;
  static {
    uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    uriMatcher.addURI("au.gov.vic.ballarat.ballarat", "directory", ALLdirectory);
    uriMatcher.addURI("au.gov.vic.ballarat.ballarat", "directory/#", SINGLEdirectory);
    uriMatcher.addURI("au.gov.vic.ballarat.ballarat", "directory/categories", CATEGORIES);
  }

  @Override
  public boolean onCreate() {
    mLocalDatabase = new BallaratDB (getContext());
    return true;
  }

  private String getTableNameFromUri(Uri uri) {
    switch(uriMatcher.match(uri)) {
      	case ALLdirectory:
        case SINGLEdirectory:
        case CATEGORIES:
          	return DIRECTORY_TABLE;
      default: break;
    }

    return null;
  }

  private String getUniqueKey(Uri uri) {
    // Only for actual tables
    switch(uriMatcher.match(uri)) {
      	case ALLdirectory:
          case SINGLEdirectory:
          	return "_id";
        case CATEGORIES:
            return "categoryName";
      default: break;
    }

    return null;
  }

  private boolean containsUnique(Uri uri, ContentValues contentvalues) {
    String unique = getUniqueKey(uri);
    for (String key : contentvalues.keySet()) {
      if (key.equals(unique)) {
        return true;
      }
    }
    return false;
  }

  private Uri getContentUriFromUri(Uri uri) {
    // Only for actual tables
    switch(uriMatcher.match(uri)) {
      	case ALLdirectory:
          case SINGLEdirectory:
        case CATEGORIES:
          	return DIRECTORY_URI;

      default: break;
    }

    return null;
  }

  private ArrayList<Uri> getAssociatedViewUris(Uri uri) {
    // Only for actual views
    ArrayList<Uri> viewUris = null;
    switch(uriMatcher.match(uri)) {
      	case ALLdirectory:
          case SINGLEdirectory:
          	break;
      default: break;
    }

    return viewUris;
  }

  @Override
  public String getType(Uri uri) {
    // Return a string that identifies the MIME type for a Content Provider URI
    switch(uriMatcher.match(uri)) {
      	case ALLdirectory:
          	return "vnd.android.cursor.dir/vnd.au.gov.vic.ballarat.ballarat.db.directory";
      	case SINGLEdirectory:
          	return "vnd.android.cursor.dir/vnd.au.gov.vic.ballarat.ballarat.db.directory";
        case CATEGORIES:
            return "vnd.android.cursor.dir/vnd.au.gov.vic.ballarat.ballarat.db.directory";
      default:
           throw new IllegalArgumentException("Unsupported URI: " + uri);
    }
  }

  @Override
  public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
    // Open database
    SQLiteDatabase db;
    try {
      db = mLocalDatabase.getWritableDatabase();
    } catch (SQLiteException ex) {
      db = mLocalDatabase.getReadableDatabase();
    }
    // Replace these with valid SQL statements if necessary.
    String groupBy = null;
    String having = null;
    SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
    // If this is a row query, limit the result set to the passed in row.
    String rowID;
    switch(uriMatcher.match(uri)) {
      case SINGLEdirectory:
          	rowID = uri.getPathSegments().get(1);
          	queryBuilder.appendWhere(ROW_ID + "=" + rowID);
          	break;
        case CATEGORIES:
            groupBy = "categoryName";
            break;
      default: break;
    }
    // Specify the table on which to perform the query. This can be a specific table or a join as required.
    queryBuilder.setTables(getTableNameFromUri(uri));

    // Execute...
    Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, groupBy, having, sortOrder);
    cursor.setNotificationUri(getContext().getContentResolver(), uri);
    return cursor;
  }
  @Override
  public ContentProviderResult[] applyBatch(ArrayList <ContentProviderOperation> operations)
      throws OperationApplicationException {
    SQLiteDatabase db = mLocalDatabase.getWritableDatabase();
    db.beginTransaction();
    final int numOperations = operations.size();
    final ContentProviderResult[] results = new ContentProviderResult[numOperations];
    Log.i(TAG, "Applying a batch of " + numOperations + " operations.");
    try {
      for (int i = 0; i < numOperations; i++) {
        results[i] = operations.get(i).apply(this, results, i);
      }
      db.setTransactionSuccessful();
    } finally {
      db.endTransaction();
    }
    return results;
  }

  @Override
  public int delete(Uri uri, String selection, String[] selectionArgs) {
    // Open database
    SQLiteDatabase db = mLocalDatabase.getWritableDatabase();
    String rowID;
    String UNIQUEID;
    switch(uriMatcher.match(uri)) {
      	case SINGLEdirectory:
          	UNIQUEID = "_id";
          	rowID = uri.getPathSegments().get(1);
          	selection = UNIQUEID + "=" + rowID + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : "");
      default: break;
    }

    if (selection == null) {
      selection = "1";
    }

    int deleteCount = db.delete(getTableNameFromUri(uri), selection, selectionArgs);
    getContext().getContentResolver().notifyChange(uri, null);

    // For non-query statements, we also check if we need to notify any view urls. If we update/insert/remove something from a table used by a view, the view must know.
    ArrayList<Uri> viewUris = getAssociatedViewUris(uri);
    if (viewUris != null) {
      for (Uri viewUri : viewUris) {
        getContext().getContentResolver().notifyChange(viewUri, null);
      }
    }

    return deleteCount;
  }

  @Override
  public Uri insert(Uri uri, ContentValues values) {
    // Open database
    SQLiteDatabase db = mLocalDatabase.getWritableDatabase();
    // Try to do an insert as per usual
    String nullColumnHack = null;
    long id = db.insert(getTableNameFromUri(uri), nullColumnHack, values);

    if (id > -1) {
      // the insert was successful
      Uri insertedId = ContentUris.withAppendedId(getContentUriFromUri(uri), id);
      getContext().getContentResolver().notifyChange(uri, null);

      // For non-query statements, we also check if we need to notify any view urls. If we update/insert/remove something from a table used by a view, the view must know.
      ArrayList<Uri> viewUris = getAssociatedViewUris(uri);
      if (viewUris != null) {
        for (Uri viewUri : viewUris) {
          getContext().getContentResolver().notifyChange(viewUri, null);
        }
      }

      return insertedId;
    }
    return null;
  }

  @Override
  public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
    // Open database
    SQLiteDatabase db = mLocalDatabase.getWritableDatabase();
    // If this is a row URI, limit the deletion to the specified row.
    String rowID;
    String UNIQUEID;
    switch(uriMatcher.match(uri)) {
      	case SINGLEdirectory:
          	UNIQUEID = "_id";
          	rowID = uri.getPathSegments().get(1);
          	selection = UNIQUEID + "=" + rowID + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : "");
      default: break;
    }
    // Perform update
    int updateCount = db.update(getTableNameFromUri(uri), values, selection, selectionArgs);
    getContext().getContentResolver().notifyChange(uri, null);

    // For non-query statements, we also check if we need to notify any view urls. If we update/insert/remove something from a table used by a view, the view must know.
    ArrayList<Uri> viewUris = getAssociatedViewUris(uri);
    if (viewUris != null) {
      for (Uri viewUri : viewUris) {
        getContext().getContentResolver().notifyChange(viewUri, null);
      }
    }

    return updateCount;
  }
}
