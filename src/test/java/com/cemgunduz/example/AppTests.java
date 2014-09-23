package com.cemgunduz.example;

import com.cemgunduz.pw.dao.BlogEntryDao;
import com.cemgunduz.pw.dao.EmailDao;
import com.cemgunduz.pw.model.BlogEntry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/spring-config/testConfig.xml")
public class AppTests {

    @Autowired
    @SuppressWarnings(value = "all")
    private EmailDao emailDao;

    @Autowired
    @SuppressWarnings(value = "all")
    private BlogEntryDao blogEntryDao;

    @Test
    public void mongoTest()
    {
        System.out.println(emailDao.count());
    }

    @Test
    public void persistBlogEntry()
    {
        BlogEntry blogEntry = new BlogEntry();
        blogEntry.setAuthor("Cem Gündüz");
        blogEntry.setCategoryIdList(Arrays.asList(3L, 4L));
        blogEntry.setDate(new Date());
        blogEntry.setHtmlContent("Lorem ipsum dot amet.");
        blogEntry.setTitle("My Test title");

        blogEntryDao.save(blogEntry);
        List<BlogEntry> blogEntries = blogEntryDao.findAll();
    }
}
