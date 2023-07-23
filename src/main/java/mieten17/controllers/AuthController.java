package mieten17.controllers;


import mieten17.models.Role;
import mieten17.models.RoleUser;
import mieten17.models.User;
import mieten17.repositories.RoleRepository;
import mieten17.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

@Controller
public class AuthController {

    private RoleUser roleUser;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "auth/logout";
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if (userFromDb != null) {
            model.put("message", "Пользователь или email уже существуют!");
            return "auth/registration";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        TreeSet<Role> role = new TreeSet<>();
        role.add(Role.valueOf("USER"));
        user.setRoles(role);
        userRepository.save(user);
        return "redirect:/login";
    }


}
