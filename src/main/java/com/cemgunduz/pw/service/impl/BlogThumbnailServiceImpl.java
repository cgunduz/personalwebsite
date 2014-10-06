package com.cemgunduz.pw.service.impl;

import com.cemgunduz.pw.model.BlogEntry;
import com.cemgunduz.pw.model.BlogThumbnail;
import com.cemgunduz.pw.model.constants.Constants;
import com.cemgunduz.pw.service.BlogService;
import com.cemgunduz.pw.service.BlogThumbnailService;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cgunduz on 8/22/14.
 */

@Service
public class BlogThumbnailServiceImpl implements BlogThumbnailService {

    @Autowired
    BlogService blogService;

    @Override
    public List<BlogThumbnail> getThumbnails() {

        List<BlogThumbnail> blogThumbnailList = new ArrayList<BlogThumbnail>();
        for(int i = 0; i < 6; i++)
            blogThumbnailList.add(BlogThumbnail.DEMO_THUMBNAIL);

        return blogThumbnailList;
    }

    @Override
    public List<BlogThumbnail> getRecentThumbnails(int total) {

        return convertBlogEntryToThumbnail(blogService.getBlogEntries(6));
    }

    private List<BlogThumbnail> convertBlogEntryToThumbnail(List<BlogEntry> blogEntryList)
    {
        List<BlogThumbnail> result = new ArrayList<BlogThumbnail>();

        for(BlogEntry blogEntry : blogEntryList)
        {
            BlogThumbnail blogThumbnail = new BlogThumbnail();
            blogThumbnail.setTitle(blogEntry.getTitle());
            blogThumbnail.setDate(blogEntry.getDate());
            blogThumbnail.setImageLocation(blogEntry.getImageLocation());
            blogThumbnail.setSummary(Jsoup.clean(blogEntry.getHtmlContent(), Whitelist.none()));
            blogThumbnail.setNavigationAddress(Constants.URI.BLOG_SPECIFIC.concat("/").concat(blogEntry.getId()));

            result.add(blogThumbnail);
        }

        return result;
    }
}
