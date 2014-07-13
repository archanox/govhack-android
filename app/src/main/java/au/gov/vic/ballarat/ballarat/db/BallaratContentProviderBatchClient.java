package au.gov.vic.ballarat.ballarat.db;

import android.content.*;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import java.util.ArrayList;

public final class BallaratContentProviderBatchClient {

  private ArrayList<ContentProviderOperation> batchOperations;


  /**
   * Starts a new batch of operations to run on commit.
   */
  public void start() {
    batchOperations = new ArrayList<ContentProviderOperation>();
  }

  /**
   * Commits a started batch of operations, this can include any variety of operations.
   */
  public ContentProviderResult[] commit(Context c)
      throws OperationApplicationException, RemoteException {
    if (batchOperations != null)  {
      ContentResolver cr = c.getContentResolver();
      ContentProviderResult[] result = cr.applyBatch( BallaratContentProvider.AUTHORITY, batchOperations );
      batchOperations.clear();
      return result;
    } else {
      throw new RuntimeException("BallaratContentProviderBatchClient.start() needs to be called first!");
    }
  }

  /**
   * add an operation to the batch of operations, if this client was started.
   */
  public void add(ContentProviderOperation operation) {
    if (batchOperations != null)  {
      batchOperations.add(operation);
    } else {
      throw new RuntimeException("BallaratContentProviderBatchClient.start() needs to be called first!");
    }
  }

  /**
   * directory OPERATIONS
   * all operations require this client to first run start
   */

  public void addDirectory(String categoryName, String subCategoryName, String serviceName, String description, String activities, String contactName, String openingHours, String mailingAddress, String streetAddress, String businessPhone, String mobilePhone, String email, String website, String fax) {
    ContentProviderOperation.Builder operationBuilder = ContentProviderOperation.newInsert(BallaratContentProvider.DIRECTORY_URI);
    operationBuilder.withValue(BallaratDB.DIRECTORY_CATEGORYNAME_COLUMN,categoryName);
    operationBuilder.withValue(BallaratDB.DIRECTORY_SUBCATEGORYNAME_COLUMN,subCategoryName);
    operationBuilder.withValue(BallaratDB.DIRECTORY_SERVICENAME_COLUMN,serviceName);
    operationBuilder.withValue(BallaratDB.DIRECTORY_DESCRIPTION_COLUMN,description);
    operationBuilder.withValue(BallaratDB.DIRECTORY_ACTIVITIES_COLUMN,activities);
    operationBuilder.withValue(BallaratDB.DIRECTORY_CONTACTNAME_COLUMN,contactName);
    operationBuilder.withValue(BallaratDB.DIRECTORY_OPENINGHOURS_COLUMN,openingHours);
    operationBuilder.withValue(BallaratDB.DIRECTORY_MAILINGADDRESS_COLUMN,mailingAddress);
    operationBuilder.withValue(BallaratDB.DIRECTORY_STREETADDRESS_COLUMN,streetAddress);
    operationBuilder.withValue(BallaratDB.DIRECTORY_BUSINESSPHONE_COLUMN,businessPhone);
    operationBuilder.withValue(BallaratDB.DIRECTORY_MOBILEPHONE_COLUMN,mobilePhone);
    operationBuilder.withValue(BallaratDB.DIRECTORY_EMAIL_COLUMN,email);
    operationBuilder.withValue(BallaratDB.DIRECTORY_WEBSITE_COLUMN,website);
    operationBuilder.withValue(BallaratDB.DIRECTORY_FAX_COLUMN,fax);
    if (batchOperations != null) {
      batchOperations.add(operationBuilder.build());
    } else {
      throw new RuntimeException("BallaratContentProviderBatchClient.start() needs to be called first!");
    }
  }

  public void removeDirectoryWith_id(int _id) {
    ContentProviderOperation.Builder operationBuilder = ContentProviderOperation.newDelete(BallaratContentProvider.DIRECTORY_URI);
    operationBuilder.withSelection(BallaratDB.DIRECTORY__ID_COLUMN + "=?", new String[]{String.valueOf(_id)});
    if (batchOperations != null) {
      batchOperations.add(operationBuilder.build());
    } else {
      throw new RuntimeException("BallaratContentProviderBatchClient.start() needs to be called first!");
    }
  }

  public void removeAllDirectory() {
    ContentProviderOperation.Builder operationBuilder = ContentProviderOperation.newDelete(BallaratContentProvider.DIRECTORY_URI);
    if (batchOperations != null) {
      batchOperations.add(operationBuilder.build());
    } else {
      throw new RuntimeException("BallaratContentProviderBatchClient.start() needs to be called first!");
    }
  }

  public void updateDirectory(String categoryName, String subCategoryName, String serviceName, String description, String activities, String contactName, String openingHours, String mailingAddress, String streetAddress, String businessPhone, String mobilePhone, String email, String website, String fax) {
    ContentProviderOperation.Builder operationBuilder = ContentProviderOperation.newUpdate(BallaratContentProvider.DIRECTORY_URI);
    operationBuilder.withValue(BallaratDB.DIRECTORY_CATEGORYNAME_COLUMN,categoryName);
    operationBuilder.withValue(BallaratDB.DIRECTORY_SUBCATEGORYNAME_COLUMN,subCategoryName);
    operationBuilder.withValue(BallaratDB.DIRECTORY_SERVICENAME_COLUMN,serviceName);
    operationBuilder.withValue(BallaratDB.DIRECTORY_DESCRIPTION_COLUMN,description);
    operationBuilder.withValue(BallaratDB.DIRECTORY_ACTIVITIES_COLUMN,activities);
    operationBuilder.withValue(BallaratDB.DIRECTORY_CONTACTNAME_COLUMN,contactName);
    operationBuilder.withValue(BallaratDB.DIRECTORY_OPENINGHOURS_COLUMN,openingHours);
    operationBuilder.withValue(BallaratDB.DIRECTORY_MAILINGADDRESS_COLUMN,mailingAddress);
    operationBuilder.withValue(BallaratDB.DIRECTORY_STREETADDRESS_COLUMN,streetAddress);
    operationBuilder.withValue(BallaratDB.DIRECTORY_BUSINESSPHONE_COLUMN,businessPhone);
    operationBuilder.withValue(BallaratDB.DIRECTORY_MOBILEPHONE_COLUMN,mobilePhone);
    operationBuilder.withValue(BallaratDB.DIRECTORY_EMAIL_COLUMN,email);
    operationBuilder.withValue(BallaratDB.DIRECTORY_WEBSITE_COLUMN,website);
    operationBuilder.withValue(BallaratDB.DIRECTORY_FAX_COLUMN,fax);
    if (batchOperations != null) {
      batchOperations.add(operationBuilder.build());
    } else {
      throw new RuntimeException("BallaratContentProviderBatchClient.start() needs to be called first!");
    }
  }
}
