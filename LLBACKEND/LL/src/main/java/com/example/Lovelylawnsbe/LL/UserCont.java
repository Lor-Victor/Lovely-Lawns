package com.example.Lovelylawnsbe.LL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    }

//    @GetMapping("/update/{id}")
//    public String showUpdateForm(@PathVariable int id, Model model) {
//        User user = userServ.getUserById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
//        model.addAttribute("user", user);
//        return "userUpdate";
//    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") int userId) {
        User user = userServ.getUserById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        return ResponseEntity.ok().body(user);
    }


    @PostMapping("/home")
    public String registerUser(User user) {
        userServ.saveOrUpdateUser(user);
        return "userhome";
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") int userId, @RequestBody User userDetails) {
        User user = userServ.getUserById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        User updatedUser = userServ.saveOrUpdateUser(user);
        return ResponseEntity.ok().body(updatedUser);
    }





    @GetMapping("user/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("user/userhome")
    public String register() {
        return "userhome";
    }

    @GetMapping("user/logout")
    public String logout() {
        return "logout";
    }


}