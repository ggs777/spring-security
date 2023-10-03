package org.workspace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.workspace.security.SecurityService;

import java.security.Security;

@Controller
public class UserController {

    @Autowired
    private SecurityService securityService;
    @GetMapping("/")
    public String showLoginPage(){
        return "login";
    }

    public String login(String email, String password){
        var loginResponse = securityService.login(email, password);
        if(loginResponse)
            return "index";
        return "login";
    }
}
