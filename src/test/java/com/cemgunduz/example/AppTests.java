package com.cemgunduz.example;

import com.cemgunduz.pw.dao.EmailDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/spring-config/testConfig.xml")
public class AppTests {

    @Autowired
    @SuppressWarnings(value = "all")
    private EmailDao emailDao;

    @Test
    public void mongoTest()
    {
        System.out.println(emailDao.count());
    }

}
