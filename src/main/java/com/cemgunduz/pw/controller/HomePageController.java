package com.cemgunduz.pw.controller;

import com.cemgunduz.pw.model.BlogEntry;
import com.cemgunduz.pw.model.Comment;
import com.cemgunduz.pw.model.constants.Category;
import com.cemgunduz.pw.model.constants.Constants;
import com.cemgunduz.pw.model.Email;
import com.cemgunduz.pw.service.BlogService;
import com.cemgunduz.pw.service.BlogThumbnailService;
import com.cemgunduz.pw.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class HomePageController {

    @Autowired
    BlogThumbnailService blogThumbnailService;

    @Autowired
    EmailService emailService;

    @Autowired
    BlogService blogService;

    private static final int ENTRIES_PER_PAGE = 3;
    private static final int TOTAL_DISPLAYED_BLOGS_ON_SIDEBAR = 6;

    @RequestMapping("/blog")
    public String blogCenterInterceptor(ModelMap model, @RequestParam(defaultValue = "0") Integer categoryOrdinal,
        @RequestParam(defaultValue = "1") Integer currentPage)
    {
        List<BlogEntry> blogEntryList = blogService.getBlogEntriesbyCategory(Category.values()[categoryOrdinal]);
        List<BlogEntry> latestBlogEntryList = blogService.getBlogEntries(TOTAL_DISPLAYED_BLOGS_ON_SIDEBAR);

        if((currentPage.intValue() - 1) * ENTRIES_PER_PAGE > blogEntryList.size()) currentPage = 1;
        int blogEntryIndex = ENTRIES_PER_PAGE * (currentPage - 1);

        model.addAttribute("categories", Category.values());
        model.addAttribute("categoryOrdinal", categoryOrdinal);

        model.addAttribute("totalPages" , blogEntryList.size()/ENTRIES_PER_PAGE + 1);
        model.addAttribute("currentPage", currentPage);

        if(blogEntryList.size() == 0)
            model.addAttribute("blogEntryList", new ArrayList<BlogEntry>());
        else
            model.addAttribute("blogEntryList",blogEntryList.subList(blogEntryIndex,
                blogEntryList.size() <= blogEntryIndex + ENTRIES_PER_PAGE ?
                        blogEntryList.size() : blogEntryIndex + ENTRIES_PER_PAGE));

        model.addAttribute("latestBlogEntryList", latestBlogEntryList);

        model.addAttribute("email", new Email());

        return Constants.URI.BLOG_MAIN;
    }

    @RequestMapping("/blog/entry/{blogId}")
    public String blogPostInterceptor(ModelMap model, @PathVariable(value = "blogId") String blogId)
    {
        if(blogId == null)
            return Constants.URI.BLOG_MAIN;

        BlogEntry blogEntry = blogService.getBlogEntryById(blogId);
        List<BlogEntry> latestBlogEntryList = blogService.getBlogEntries(TOTAL_DISPLAYED_BLOGS_ON_SIDEBAR);

        List<Comment> commentList = blogService.getCommentsByBlogId(blogId);

        model.addAttribute("categories", Category.values());
        model.addAttribute("categoryOrdinal", 0);

        model.addAttribute("blogEntry", blogEntry);
        model.addAttribute("commentList", commentList);
        model.addAttribute("latestBlogEntryList", latestBlogEntryList);

        model.addAttribute("email", new Email());

        model.addAttribute("blogId", blogId);

        // Init the comment
        Comment comment = new Comment();
        comment.setBlogId(blogId);
        comment.setApproved(false);
        model.addAttribute("comment", comment);

        return Constants.URI.BLOG_SPECIFIC;
    }

	@RequestMapping("/")
	public String homepageInterceptor(ModelMap model) {

        model.addAttribute("blogThumbnailList", blogThumbnailService.getRecentThumbnails(6));
        model.addAttribute("categories", Category.values());

        // TODO : Is this even needed ?
        model.addAttribute("email", new Email());

		return Constants.URI.HOMEPAGE;
	}

    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    public ModelAndView sendEmail(ModelMap model, @RequestBody Email email)
    {
        email.setDate(new Date());
        emailService.save(email);
        return new ModelAndView(Constants.URI.HOMEPAGE);
    }

    @RequestMapping(value = "/sendComment", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    public ModelAndView sendCommment(ModelMap model, @RequestBody Comment comment)
    {
        comment.setDate(new Date());
        blogService.saveComment(comment);
        return new ModelAndView(Constants.URI.BLOG_SPECIFIC);
    }
}