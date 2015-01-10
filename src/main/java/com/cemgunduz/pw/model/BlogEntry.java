package com.cemgunduz.pw.model;

import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cgunduz on 9/8/14.
 */
@Component
@Scope("prototype")
@Document(collection = "BlogEntry")
public class BlogEntry {

    @Id
    private String id;

    private String title;
    private String author;
    private String authorLink;
    private Date date;
    private String htmlContent;

    private String imageLocation;

    private List<Long> categoryIdList = new ArrayList<Long>();
    private List<String> commentIdList = new ArrayList<String>();

    @Transient
    private BlogThumbnail blogEntryThumbnail;

    @Transient
    private List<Comment> commentList;

    @Transient
    private List<String> categoryNameList = new ArrayList<String>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Long> getCategoryIdList() {
        return categoryIdList;
    }

    public void setCategoryIdList(List<Long> categoryIdList) {
        this.categoryIdList = categoryIdList;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public List<String> getCommentIdList() {
        return commentIdList;
    }

    public void setCommentIdList(List<String> commentIdList) {
        this.commentIdList = commentIdList;
    }

    public BlogThumbnail getBlogEntryThumbnail() {
        return blogEntryThumbnail;
    }

    public void setBlogEntryThumbnail(BlogThumbnail blogEntryThumbnail) {
        this.blogEntryThumbnail = blogEntryThumbnail;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public String getAuthorLink() {
        return authorLink;
    }

    public void setAuthorLink(String authorLink) {
        this.authorLink = authorLink;
    }

    public List<String> getCategoryNameList() {
        return categoryNameList;
    }

    public void setCategoryNameList(List<String> categoryNameList) {
        this.categoryNameList = categoryNameList;
    }
}
