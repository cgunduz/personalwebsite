package com.cemgunduz.pw.controller;

import com.cemgunduz.pw.model.Constants;
import com.cemgunduz.pw.model.Email;
import com.cemgunduz.pw.service.BlogThumbnailService;
import com.cemgunduz.pw.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomePageController {

    @Autowired
    BlogThumbnailService blogThumbnailService;

    @Autowired
    EmailService emailService;

    @RequestMapping("/blog")
    public String blogCenterInterceptor(ModelMap model)
    {
        return Constants.URI.BLOG;
    }

    @RequestMapping("/blog/{blogId}")
    public String blogPostInterceptor(ModelMap model, @PathVariable(value = "blogId") String blogId)
    {
        if(blogId != null)
            return "blog/blog_1";

        return Constants.URI.BLOG;
    }

	@RequestMapping("/")
	public String homepageInterceptor(ModelMap model) {

        model.addAttribute("blogThumbnailList", blogThumbnailService.getThumbnails());
        model.addAttribute("email", new Email());

		return Constants.URI.HOMEPAGE;
	}

    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
    public ModelAndView sendEmail(@ModelAttribute("email")Email email,
                            ModelMap model)
    {
        emailService.save(email);
        return new ModelAndView(Constants.URI.HOMEPAGE);
    }
}