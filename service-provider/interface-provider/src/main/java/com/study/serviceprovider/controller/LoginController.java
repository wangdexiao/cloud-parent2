package com.study.serviceprovider.controller;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Controller
public class LoginController {

    @Data
    @Setter
    @Getter
    @AllArgsConstructor
    @ToString
    public static class  User implements Serializable {
        private String username;
        private String password;


    }

    static Map<String, User> users = new ConcurrentHashMap<>();
    static {
        users.put("admin", new User("admin", "admin"));
        users.put("wadexi", new User("wadexi", "wadexi"));
    }

    @GetMapping(path = {"/loginpage"})
    public String loginpage(Model model,HttpSession session){
        if(session.getAttribute("userBean") != null){
            User userBean = (User)session.getAttribute("userBean");

//            attributes.addAttribute("userBean", userBean);
            return "redirect:/home";
        }
        return "index";
    }


    @PostMapping("/login")
    public String login(HttpSession session,/*RedirectAttributes attributes,*/ String username, String password){

        User user = users.get(username);
        if(user != null && user.getPassword().equals(password)){
            session.setAttribute("userBean" ,user);
//            attributes.addAttribute("userBean", user);
        }else {
            return "redirect:/loginpage";
        }

        return "redirect:/home";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session, /*RedirectAttributes attributes,*/ String username, String password){

       session.setAttribute("userBean",null);

        return "redirect:/loginpage";
    }

    @GetMapping(path = {"","/","/home"})
    public String login(HttpSession session,Model model){
        User userBean = (User)session.getAttribute("userBean");
        log.info("userBean:" + userBean);
        model.addAttribute("userBean",userBean);
        return "home";
    }
}
