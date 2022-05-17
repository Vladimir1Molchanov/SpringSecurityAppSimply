package app.mv.project.controllers;

import app.mv.project.Service.UserService;
import app.mv.project.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService us;
    private final String BACKMAIN = "redirect:/user";

    public UserController(UserService us) {
        this.us = us;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("listUsers", us.listUser());
        return "all_users";
    }

    @GetMapping("/{id}")
    public String showUserById(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", us.getUserById(id));
        return "specific_user";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        us.addUser(user);
        return BACKMAIN;
    }

    @GetMapping("{id}/edit")
    public String editUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", us.getUserById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id){
        us.changeUser(id, user);
        return BACKMAIN;
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") int id){
        us.deleteUserById(id);
        return BACKMAIN;
    }
}
