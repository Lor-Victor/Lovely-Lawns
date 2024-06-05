package com.example.Lovelylawnsbe.LL.Admin;

import com.example.Lovelylawnsbe.LL.ResourceNotFoundException;
import com.example.Lovelylawnsbe.LL.User;
import com.example.Lovelylawnsbe.LL.UserServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class AdminController {
    @Autowired
    private UserServ userServ;


    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/all-users")
    public String getAllUsers(Model model) {
        List<User> users = userServ.getAllUsers();
        model.addAttribute("users", users);
        return "user-list";
    }
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") int userId) {
        User user = userServ.getUserById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        return ResponseEntity.ok().body(user);
    }
    @GetMapping("/delete/{userId}")
    public String deleteUser(@PathVariable int userId) {
        userServ.deleteUser(userId);
        return "redirect:/users/all";
    }
}
