package ru.rotar.spring.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize ("hasAnyRole ('user', 'admin')")
    public String index(Model model) {
        model.addAttribute("people", userService.findAll());
        return "user/index";
    }

    @GetMapping("/address")
    @PreAuthorize ("hasAnyRole ('user', 'admin')")
    public String getAddress(Model model, @RequestParam int id) {
        User address = userService.findOne(id);
        model.addAttribute("address", address);
        return "user/address";
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole ('admin')")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/user";
    }

    @GetMapping("/{id}/edit")
    @PreAuthorize ("hasAnyRole ('admin')")
    public String update(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", userService.findOne(id));
        return "user/new";
    }

    @GetMapping(value = "/new")
    @PreAuthorize ("hasAnyRole ('admin')")
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


