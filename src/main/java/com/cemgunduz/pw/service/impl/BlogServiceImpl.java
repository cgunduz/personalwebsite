package com.cemgunduz.pw.service.impl;

import com.cemgunduz.pw.dao.BlogEntryDao;
import com.cemgunduz.pw.dao.CommentDao;
import com.cemgunduz.pw.model.BlogEntry;
import com.cemgunduz.pw.model.Comment;
import com.cemgunduz.pw.model.constants.Category;
import com.cemgunduz.pw.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cgunduz on 9/9/14.
 *
 * There is no optimization, total blog count would always be too low
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    @SuppressWarnings(value = "all")
    BlogEntryDao blogEntryDao;

    @Autowired
    @SuppressWarnings(value = "all")
    CommentDao commentDao;

    @Override
    public BlogEntry getBlogEntryById(String id) {

        return blogEntryDao.findOne(id);
    }

    @Override
    public List<BlogEntry> getBlogEntries(int count) {

        // blogentryDao.count

        return blogEntryDao.findAll(new PageRequest(0, count)).getContent();

    }

    // TODO : Improve
    @Override
    public List<BlogEntry> getBlogEntriesbyCategory(Category category) {

        if(category == null)
            category = Category.ALL;

        List<BlogEntry> blogEntryList = new ArrayList<BlogEntry>();

        for(BlogEntry candidate : blogEntryDao.findAll())
        {
            if(category.equals(Category.ALL) || candidate.getCategoryIdList().contains((long)category.ordinal()))
            {
                initalizeBlogComments(candidate);
                blogEntryList.add(candidate);
            }
        }

        return blogEntryList;
    }

    @Override
    public List<Comment> getCommentsByBlogId(String blogId) {

        List<Comment> commentList = new ArrayList<Comment>();

        for(Comment candidate : commentDao.findAll())
            if(candidate.getBlogId().equals(blogId) && candidate.getApproved())
                commentList.add(candidate);

        return commentList;
    }

    @Override
    public void saveComment(Comment comment) {

        if(comment.getBlogId() == null)
            throw new RuntimeException("Assoc blog id is null");

        commentDao.save(comment);
    }

    private void initalizeBlogComments(BlogEntry blogEntry)
    {
        blogEntry.setCommentList(getCommentsByBlogId(blogEntry.getId()));
    }
}
