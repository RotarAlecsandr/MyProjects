package ru.rotar.spring.controller;

import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.rotar.spring.model.User;
import ru.rotar.spring.service.UserService;

@org.springframework.stereotype.Controller
@RequestMapping("/user")
@AllArgsConstructor
public class Controller {

    private final UserService userService;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", userService.findAll());
        return "user/index";
    }

    @GetMapping("/address")
    public String getAddress(Model model, @RequestParam int id) {
        User address = userService.findOne(id);
        model.addAttribute("address", address);
        return "user/address";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/user";
    }

    @GetMapping("/{id}/edit")
    public String update(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", userService.findOne(id));
        return "user/new";
    }

    @GetMapping(value = "/new")
    public String userCreate(Model model, User user) {
        model.addAttribute("person", user);
        return "user/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person")  User user){
        userService.save(user);
        return "redirect:/user";
    }
}


