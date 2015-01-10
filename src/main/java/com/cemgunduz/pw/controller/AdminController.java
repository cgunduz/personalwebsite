package com.cemgunduz.pw.controller;

import com.cemgunduz.pw.model.BlogEntry;
import com.cemgunduz.pw.model.constants.Category;
import com.cemgunduz.pw.model.constants.Constants;
import com.cemgunduz.pw.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * Created by cgunduz on 10/7/14.
 */

@Controller
public class AdminController {

    @Autowired
    BlogService blogService;

    @RequestMapping("/admin")
    public ModelAndView prepareHome(ModelMap model)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("blogEntries", blogService.getBlogEntries(100));
        modelAndView.setViewName(Constants.URI.Admin.HOME);

        return modelAndView;
    }

    @RequestMapping("/admin/blogOperations")
    public ModelAndView prepareBlogAddPage(ModelMap model)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("blogEntry", new BlogEntry());
        modelAndView.addObject("context", "add");

        // List of all possible categories
        modelAndView.addObject("categoryMap", prepareCategoryCheckbox());

        // For the checkbox components to be bound
        modelAndView.addObject("blogCategoryList", new ArrayList<String>());

        modelAndView.setViewName(Constants.URI.Admin.BLOG_OPERATIONS);

        return modelAndView;
    }

    @RequestMapping("/admin/blogOperations/{blogId}" )
    public ModelAndView prepareBlogUpdatePage(ModelMap model, @PathVariable(value = "blogId") String blogId)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("blogEntry", blogService.getBlogEntryById(blogId));
        modelAndView.addObject("context", "update");
        modelAndView.addObject("categoryMap", prepareCategoryCheckbox());
        modelAndView.addObject("blogCategoryList", new ArrayList<String>());
        modelAndView.setViewName(Constants.URI.Admin.BLOG_OPERATIONS);

        return modelAndView;
    }

    @RequestMapping(value = "/admin/blogOperations/persist", method = RequestMethod.POST)
    public ModelAndView saveOrUpdateBlog(ModelMap model, @ModelAttribute BlogEntry blogEntry)
    {
        // Persist then return home
        blogService.saveBlog(blogEntry);

        return prepareHome(model);
    }

    private Map<String, String> prepareCategoryCheckbox()
    {
        Map<String, String> categoryMap = new HashMap<String, String>();
        for(Category cat : Category.values())
        {
            categoryMap.put(cat.toString(), cat.toString());
        }

        return categoryMap;
    }
}
