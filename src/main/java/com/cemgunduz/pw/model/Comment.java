package com.cemgunduz.pw.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by cgunduz on 9/8/14.
 */
@Document(collection = "Comment")
public class Comment {

    @Id
    private String id;

    private String blogId;

    private String commentSource;
    private String commentSourceLink;
    private Date date;
    private String commentContent;
    private String imageSource;

    private Boolean approved = false;

    public String getId() {
        return id;
    }

    public String getCommentSourceLink() {
        return commentSourceLink;
    }

    public void setCommentSourceLink(String commentSourceLink) {
        this.commentSourceLink = commentSourceLink;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommentSource() {
        return commentSource;
    }

    public void setCommentSource(String commentSource) {
        this.commentSource = commentSource;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
}
