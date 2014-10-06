package com.cemgunduz.pw.service;

import com.cemgunduz.pw.model.BlogEntry;
import com.cemgunduz.pw.model.Comment;
import com.cemgunduz.pw.model.constants.Category;

import java.util.List;

/**
 * Created by cgunduz on 9/9/14.
 */
public interface BlogService {

    /**
     * Self-explanatory, also carries it's BlogThumbnail sub entity
     *
     * @param id
     * @return
     */
    BlogEntry getBlogEntryById(String id);

    /**
     *
     * Returns blog entries
     *
     * With the specified amount, sorted by it's recentness
     *
     * @param count
     * @return
     */
    List<BlogEntry> getBlogEntries(int count);

    /**
     * Returns blog entries belonging to a certain category, also holds the comments
     *
     * Sorted by it's recentness
     *
     * @param category
     * @return
     */
    List<BlogEntry> getBlogEntriesbyCategory(Category category);

    /**
     * Returns the comments associated with a specific blog entry
     *
     * @return
     */
    List<Comment> getCommentsByBlogId(String blogId);

    /**
     * Persists the specified comment for a specific blog entry
     *
     * @param comment
     */
    void saveComment(Comment comment);
}
