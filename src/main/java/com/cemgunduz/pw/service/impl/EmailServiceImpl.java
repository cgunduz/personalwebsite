package com.cemgunduz.pw.service.impl;

import com.cemgunduz.pw.dao.EmailDao;
import com.cemgunduz.pw.model.Email;
import com.cemgunduz.pw.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cgunduz on 9/8/14.
 */

@Service
public class EmailServiceImpl implements EmailService
{
    @Autowired
    @SuppressWarnings(value = "all")
    EmailDao emailDao;

    @Override
    public List<Email> getAll() {

        return emailDao.findAll();
    }

    @Override
    public void save(Email email) {

        emailDao.save(email);
    }
}
