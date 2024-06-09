package com.example.Lovelylawnsbe.LL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/index")
public class UserCont {

    @Autowired

    private UserServ userServ;
    @GetMapping
    public String index() {
        return "index";
    }


    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user) {
        userServ.saveOrUpdateUser(user);
        return "redirect:/users/" + user.getUserId();
    private UserServ userService;
    }

    @GetMapping("/")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable(value = "id") int userId, Model model) {
        User user = userService.getUserById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/home")
    public String registerUser(User user) {
        userServ.saveOrUpdateUser(user);
        return "userhome";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        User savedUser = userService.saveOrUpdateUser(user);
        return "redirect:/users/";
    }

    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable(value = "id") int userId, Model model) {
        User user = userService.getUserById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        model.addAttribute("user", user);
        return "update_user";
    }

    @PostMapping("/{id}/edit")
    public String updateUser(@PathVariable(value = "id") int userId, @RequestBody User userDetails) {        User user = userService.getUserById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        user = userService.getUserById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        User updatedUser = userService.saveOrUpdateUser(user);
        return "redirect:/users/" + userId;
    }

    @GetMapping("user/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("user/userhome")
    public String register() {
        return "userhome";
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable(value = "id") int userId) {
        userService.deleteUser(userId);
        return "redirect:/users/";
    }

    @GetMapping("user/logout")
    public String logout() {
        return "logout";
    }

}

