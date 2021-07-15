package com.app.controllers;

import com.app.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CatalogController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/catalog")
    public String getCatalog(Model model){
        model.addAttribute("items", adminService.getCatalogItems());
        return "catalog";
    }

    @GetMapping("/catalog/{id}")
    public String getCatalogById(@PathVariable(value = "id") long id, Model model){
        model.addAttribute("items", adminService.getCatalogItemsById(id));
        return "catalog";
    }
}
