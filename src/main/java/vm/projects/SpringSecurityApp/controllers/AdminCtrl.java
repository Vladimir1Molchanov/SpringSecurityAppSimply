package vm.projects.SpringSecurityApp.controllers;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vm.projects.SpringSecurityApp.model.Role;
import vm.projects.SpringSecurityApp.model.User;
import vm.projects.SpringSecurityApp.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminCtrl {

    public AdminCtrl(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;

    @GetMapping
    public String findAll(Model model) {
        StringBuilder userName = new StringBuilder(SecurityContextHolder.getContext().getAuthentication().getName());
        StringBuilder roles = new StringBuilder(" with roles: ");
        for (GrantedAuthority role : SecurityContextHolder.getContext().getAuthentication().getAuthorities()){
            roles.append(" ").append(role.getAuthority());
        }
        model.addAttribute("userName", userName);
        model.addAttribute("roles", roles);

        model.addAttribute("users", userService.findAll());
        return "user-list";
    }

    @PostMapping("/user-delete")
    public String deleteUser(User user) {
        userService.deleteById(user.getId());
        return "redirect:/admin";
    }

    @PostMapping("/user-update")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam("roles") Long[] roles) {
        userService.updateUser(user, roles);
        return "redirect:/admin";
    }

}
