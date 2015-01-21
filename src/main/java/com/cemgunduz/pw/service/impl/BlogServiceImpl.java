package com.cemgunduz.pw.service.impl;

import com.cemgunduz.pw.dao.BlogEntryDao;
import com.cemgunduz.pw.dao.CommentDao;
import com.cemgunduz.pw.model.BlogEntry;
import com.cemgunduz.pw.model.Comment;
import com.cemgunduz.pw.model.constants.Category;
import com.cemgunduz.pw.model.constants.Constants;
import com.cemgunduz.pw.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

        PageRequest pageRequest = new PageRequest(0, count,
                new Sort(Sort.Direction.DESC, "date"));

        return blogEntryDao.findAll(pageRequest).getContent();

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

    @Override
    public void saveBlog(BlogEntry blogEntry) {

        // Sort out relevant category ids
        for(Category category : Category.values())
        {
            if(blogEntry.getCategoryNameList().contains(category.toString()))
            {
                Integer ordinal = category.ordinal();
                blogEntry.getCategoryIdList().add(ordinal.longValue());
            }
        }

        // Set today as the date of the entry
        Date now = new Date();
        blogEntry.setDate(now);

        // Set default author if not provided
        if(blogEntry.getAuthor() == null) blogEntry.setAuthor(Constants.AUTHOR.NAME);

        // Set default author link if not provided
        if(blogEntry.getAuthorLink() == null) blogEntry.setAuthorLink(Constants.AUTHOR.LINK);

        blogEntryDao.save(blogEntry);
    }

    private void initalizeBlogComments(BlogEntry blogEntry)
    {
        blogEntry.setCommentList(getCommentsByBlogId(blogEntry.getId()));
    }
}
