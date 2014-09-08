package com.cemgunduz.pw.service;

import com.cemgunduz.pw.model.BlogThumbnail;

import java.util.List;

/**
 * Created by cgunduz on 8/22/14.
 */
public interface BlogThumbnailService {

    /**
     * Returns all registered thumbnails
     *
     * @return
     */
    List<BlogThumbnail> getThumbnails();

    /**
     * Returns {total} number of registered thumbnails sorted according to entry date
     *
     * @param total
     * @return
     */
    List<BlogThumbnail> getRecentThumbnails(int total);
}
