package com.cemgunduz.controller;

import com.cemgunduz.model.Constants;
import com.cemgunduz.service.BlogThumbnailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePageController {

    @Autowired
    BlogThumbnailService blogThumbnailService;

    @RequestMapping("/blog")
    public String blogCenterInterceptor(ModelMap model)
    {
        return Constants.URI.BLOG;
    }

	@RequestMapping("/")
	public String homepageInterceptor(ModelMap model) {

        model.addAttribute("blogThumbnailList", blogThumbnailService.getThumbnails());
		return Constants.URI.HOMEPAGE;
	}
}