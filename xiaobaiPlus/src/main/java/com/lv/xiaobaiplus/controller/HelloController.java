package com.lv.xiaobaiplus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: xiaobaiPlus
 * @ClassName HelloController
 * @description:
 * @author: gxjh
 * @create: 2024-11-24 12:00
 * @Version 1.0
 **/
@Controller
public class HelloController {

    @GetMapping("/login")
    public String hello(Model model) {
        model.addAttribute("message", "Hello, you are authenticated!");
        return "login";
    }

}