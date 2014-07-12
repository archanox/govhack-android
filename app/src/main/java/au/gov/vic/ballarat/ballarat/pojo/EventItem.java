package au.gov.vic.ballarat.ballarat.pojo;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by troy on 12/07/2014.
 */
public class EventItem implements Serializable {
    private String title;
    private String location;
    private String startDate;
    private String endDate;
    private String category;
    private String description;
    private String contactName;
    private String email;
    private String website;
    private String facebookPage;
    private String image;

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getDate() {
        String startDate = getStartDate();
        String endDate = getEndDate();

        if (startDate.equals(endDate)) {
            return startDate;
        }
        Date start = dateFromString(startDate);
        Date end = dateFromString(endDate);

        DateFormat df = new SimpleDateFormat("d MMM yyyy");
        return df.format(start) + " - " + df.format(end);
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getContactName() {
        return contactName;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }

    public String getFacebookPage() {
        return facebookPage;
    }

    private Date dateFromString(String dateString) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH).parse(dateString);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public String getImage() {
        return image;
    }
}
