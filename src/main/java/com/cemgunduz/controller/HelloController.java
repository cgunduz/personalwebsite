package com.cemgunduz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;

@Controller
public class HelloController {

    @PostConstruct
    public void init()
    {
        System.out.println("initialized");
    }

    @RequestMapping("/blog")
    public String deneme(ModelMap model)
    {
        return "blog/blog";
    }

	@RequestMapping("/")
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "index";
	}

    @RequestMapping("/assets/*")
    public String deneme2(ModelMap model) {

        return "";
    }
}