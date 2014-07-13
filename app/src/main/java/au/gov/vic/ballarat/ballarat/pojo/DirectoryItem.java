package au.gov.vic.ballarat.ballarat.pojo;

import java.io.Serializable;

/**
 * Created by troy on 13/07/2014.
 */
public class DirectoryItem implements Serializable {
    private String categoryName;
    private String subCategoryName;
    private String serviceName;
    private String description;
    private String activities;
    private String contactName;
    private String openingHours;
    private String mailingAddress;
    private String streetAddress;
    private String businessPhone;
    private String mobilePhone;
    private String email;
    private String website;
    private String fax;

    public String getCategoryName() {
        return categoryName;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getDescription() {
        return description;
    }

    public String getActivities() {
        return activities;
    }

    public String getContactName() {
        return contactName;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }

    public String getFax() {
        return fax;
    }
}
