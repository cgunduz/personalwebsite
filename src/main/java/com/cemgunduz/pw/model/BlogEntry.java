package com.cemgunduz.pw.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cgunduz on 9/8/14.
 */
@Document(collection = "BlogEntry")
public class BlogEntry {

    @Id
    private String id;

    private String title;
    private String author;
    private Date date;
    private String htmlContent;

    private List<String> categoryIdList = new ArrayList<String>();
    private List<String> commentIdList = new ArrayList<String>();

    @Transient
    private BlogThumbnail blogEntryThumbnail;

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

    public List<String> getCategoryIdList() {
        return categoryIdList;
    }

    public void setCategoryIdList(List<String> categoryIdList) {
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
}
