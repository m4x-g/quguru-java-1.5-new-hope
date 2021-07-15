package com.app.controllers;

import com.app.services.LangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LangController {

    @Autowired
    private LangService langService;

    @GetMapping("/translationExample")
    public String getTranslationExamplePage(Model model, HttpSession session) {

        if (session.getAttribute("langId") == null) {
            session.setAttribute("langId", 1);
        }

        long langId = Long.parseLong(session.getAttribute("langId").toString());

        model.addAttribute("translations", langService.getTranslations(langId, "home page"));
        model.addAttribute("languages", langService.getLanguages());

        return "translation_example";
    }

    @GetMapping("/setLang/{langId}")
    @ResponseBody
    public void setLang(@PathVariable(value = "langId") long langId, HttpSession session) {
        session.setAttribute("langId", langId);
    }
}
