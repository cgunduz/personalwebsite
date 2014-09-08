package com.cemgunduz.pw.service.impl;

import com.cemgunduz.pw.model.BlogThumbnail;
import com.cemgunduz.pw.service.BlogThumbnailService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cgunduz on 8/22/14.
 */

@Service
public class BlogThumbnailServiceImpl implements BlogThumbnailService {

    @Override
    public List<BlogThumbnail> getThumbnails() {

        List<BlogThumbnail> blogThumbnailList = new ArrayList<BlogThumbnail>();
        for(int i = 0; i < 6; i++)
            blogThumbnailList.add(BlogThumbnail.DEMO_THUMBNAIL);

        return blogThumbnailList;
    }

    @Override
    public List<BlogThumbnail> getRecentThumbnails(int total) {
        return null;
    }
}
