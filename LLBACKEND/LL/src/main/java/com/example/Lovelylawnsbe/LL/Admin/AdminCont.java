package com.example.Lovelylawnsbe.LL.Admin;

import com.example.Lovelylawnsbe.LL.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home")
public class AdminCont{
    @Autowired
    private UserServ userServ;
    @Autowired
    private PostServ postServ;
    @Autowired
    private ForumServ forumServ;

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/all-users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userServ.getAllUsers());
        return "user-list";
    }
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") int userId) {
        User user = userServ.getUserById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        return ResponseEntity.ok().body(user);
    }
    @GetMapping("/delete/user/{userId}")
    public String deleteUser(@PathVariable int userId) {
        userServ.deleteUser(userId);
        return "redirect:/home/all-users";
    }
    @GetMapping("/getpost")
    public String getPostById(@RequestParam("postId") int postId, Model model) {
        Post post = postServ.getPostById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));
        model.addAttribute("post", post);
        return "post-detail";
    }
    @GetMapping("/all-posts")
    public String getAllPosts(Model model) {
        model.addAttribute("posts", postServ.getAllPosts());
        return "post-list";
    }
    @GetMapping("/delete/{postId}")
    public String deletePost(@PathVariable int postId) {
        postServ.deletePost(postId);
        return "redirect:/home/all";
    }
    @GetMapping("/all-forums")
    public String getAllForums(Model model) {
        model.addAttribute("forums", forumServ.getAllForums());
        return "forum-list";
    }
    @GetMapping("/delete/forum/{forumId}")
    public String deleteForum(@PathVariable int forumId) {
        forumServ.deleteForum(forumId);
        return "redirect:/home/all-forums";
    }
}
