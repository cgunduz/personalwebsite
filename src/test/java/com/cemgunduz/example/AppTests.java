package com.cemgunduz.example;

import com.cemgunduz.pw.dao.BlogEntryDao;
import com.cemgunduz.pw.dao.CommentDao;
import com.cemgunduz.pw.dao.EmailDao;
import com.cemgunduz.pw.model.BlogEntry;
import com.cemgunduz.pw.model.Comment;
import com.cemgunduz.pw.service.BlogService;
import org.junit.Ignore;
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

    @Autowired
    private BlogService blogService;

    @Autowired
    @SuppressWarnings(value="all")
    private CommentDao commentDao;

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

    @Test
    @Ignore
    public void persistComment()
    {
        List<BlogEntry> blogEntries = blogService.getBlogEntries(3);
        for(BlogEntry blogEntry : blogEntries)
        {
            Comment comment = new Comment();
            comment.setApproved(true);
            comment.setBlogId(blogEntry.getId());
            comment.setCommentSource("Cem Gündüz");
            comment.setCommentSourceLink("http://www.google.com");
            comment.setCommentContent("Deneme comment deneme comment deneme comment");
            comment.setDate(new Date());
            comment.setImageSource("../assets/images/logo.png");
            commentDao.save(comment);
        }
    }
}
