package com.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HelloController {

    @GetMapping("/hello/{userName}") //http://localhost/hello/Maxx
    public String getHelloPageWithParameter(@PathVariable(value = "userName") String userName, Model model){
        model.addAttribute("name", userName);
        return "hello"; //name of the HTML file
    }

    @GetMapping("/hello") //http://localhost/hello
    public String getHelloPageWithoutParameter(Model model){
        model.addAttribute("name", "hardcode");
        return "hello"; //name of the HTML file
    }

    @GetMapping("/hello_two") //http://localhost/hello_two
    public String getHelloPage(){
        return "hello_two"; //name of the HTML file
    }

    @GetMapping("/goodbye") //http://localhost/goodbye
    public String getGoodbyePage(){
        return "goodbye";
    }
}
