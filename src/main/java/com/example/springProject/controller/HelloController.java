package com.example.springProject.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springProject.Entity.User;
import com.example.springProject.repo.userRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class HelloController {

    @Autowired
    private userRepository userRepo;

    @GetMapping("/")
    public String home(Model m){

        List<User> list = userRepo.findAll();
        m.addAttribute("allusers", list);
        return "index";
    }

    @GetMapping("/load_form")
    public String loadForm(){
        return "add";
    }

    @GetMapping("/update_form/{id}")
    public String updateForm(@PathVariable(value = "id") Long id, Model m){

        Optional<User> users = userRepo.findById(id);
        User us = users.get();
        m.addAttribute("users", us);
        return "update";
    }

    @PostMapping("/save_user")
    public String saveUser(@ModelAttribute User user, HttpSession session){
        userRepo.save(user);

        return "redirect:/load_form";
    }

    @PostMapping("/update_users")
    public String updateUser(@ModelAttribute User user, HttpSession session){
        userRepo.save(user);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id, HttpSession session){
        userRepo.deleteById(id);
        return "redirect:/";
    }
}
