package com.cemgunduz.pw.model;

import com.cemgunduz.pw.model.constants.Constants;

import java.util.Date;

/**
 * Created by cgunduz on 8/22/14.
 */
public class BlogThumbnail {

    public static final BlogThumbnail DEMO_THUMBNAIL;

    static
    {
        BlogThumbnail blogThumbnail = new BlogThumbnail();
        blogThumbnail.setDate(new Date());
        blogThumbnail.setImageLocation("assets/images/logo.png");
        blogThumbnail.setSummary(Constants.DUMMY_TEXT.LOREM_IPSUM);
        blogThumbnail.setTitle("Blog Post Title");
        blogThumbnail.setNavigationAddress("#");
        DEMO_THUMBNAIL = blogThumbnail;
    }

    private String imageLocation;
    private String title;
    private Date date;
    private String summary;
    private String navigationAddress;

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getNavigationAddress() {
        return navigationAddress;
    }

    public void setNavigationAddress(String navigationAddress) {
        this.navigationAddress = navigationAddress;
    }
}
