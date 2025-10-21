package com.ku_26.bookstore.controller; // Use your actual package name

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        // This just returns the name of the HTML file to be displayed.
        return "login";
    }
}
