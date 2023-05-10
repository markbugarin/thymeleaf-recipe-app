package com.champstart.recipeapp.user.controller;

import com.champstart.recipeapp.user.dto.LoginFormDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller

public class LoginController {

    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("loginForm", LoginFormDto.builder().build());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@Valid @ModelAttribute("loginForm") LoginFormDto loginForm, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("loginForm", loginForm);
            return "login";
        }
//        recipeService.saveRecipe(recipeDto); Create login service for LoginFormDto
        return "redirect:/recipes";
    }
}