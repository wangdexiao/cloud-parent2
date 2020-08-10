package com.study.ssoserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogInController {

    @GetMapping("/loginpage")
    public String loginpage(Model model){
        model.addAttribute("title", "统一登录中心");
        return "loginpage";
    }
}
