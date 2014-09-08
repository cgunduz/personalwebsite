package com.cemgunduz.pw.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by cgunduz on 8/25/14.
 */

@Document(collection = "Email")
public class Email {

    private String title;
    private String emailAddress;
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
