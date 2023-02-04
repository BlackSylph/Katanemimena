package com.katanemimena.project.controller;

import com.katanemimena.project.entity.TransferStatement;
import com.katanemimena.project.repository.TransferStatementRepository;
import com.katanemimena.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
public class HomeController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("msgs", userRepository.findAll());
        return "index";
    }
}