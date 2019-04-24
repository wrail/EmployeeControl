package com.wrial.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;

@Controller
//@RequestMapping(value = "/test")
public class MyController {

    @RequestMapping(value = "/hello")
    public String hello(Model model) {
        model.addAttribute("name", "wrial");
        return "/success";
    }

    @RequestMapping(value = "/index")
    public String index() {

        return "login";
    }

    @RequestMapping(value = "/404")
    public String NotFound() {
        return "404";
    }
}
