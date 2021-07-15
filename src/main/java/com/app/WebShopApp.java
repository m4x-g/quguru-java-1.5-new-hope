package com.app;

import com.app.model.User;
import com.app.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebShopApp {
    public static void main(String[] args) {
        SpringApplication.run(WebShopApp.class, args);
    }
}
