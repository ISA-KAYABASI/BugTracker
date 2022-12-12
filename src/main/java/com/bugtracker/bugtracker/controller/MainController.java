package com.bugtracker.bugtracker.controller;

import com.bugtracker.bugtracker.service.BugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private BugService bugService;

    @GetMapping("/login")
    public String login(){

        return "login";
    }

    @GetMapping("/")
    public String viewHomePage(Model model){
        model.addAttribute("listBugs", bugService.getAllBug());
        return "indexBug";
    }

}