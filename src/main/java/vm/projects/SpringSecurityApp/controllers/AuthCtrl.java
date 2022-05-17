package vm.projects.SpringSecurityApp.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import vm.projects.SpringSecurityApp.model.Role;
import vm.projects.SpringSecurityApp.model.User;
import vm.projects.SpringSecurityApp.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
public class AuthCtrl {

    public AuthCtrl(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;
    private Authentication authentication;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/reg")
    public String newUserForm(User user) {
        return "user-create";
    }

    @PostMapping("/reg")
    public String newUser(User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("ADMIN"));
        user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:/login";
    }
}
