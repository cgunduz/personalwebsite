package com.cemgunduz.pw.service;

import com.cemgunduz.pw.model.Email;

import java.util.List;

/**
 * Created by cgunduz on 9/8/14.
 */
public interface EmailService {

    List<Email> getAll();
    void save(Email email);
}
