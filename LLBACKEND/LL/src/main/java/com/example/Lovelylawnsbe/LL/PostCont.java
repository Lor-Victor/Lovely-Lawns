package com.example.Lovelylawnsbe.LL;

import com.mashape.unirest.http.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostCont {

    @Autowired
    private ReplyServ replyService;

    @Autowired
    private PostServ postService;

    @Autowired
    private UserServ userService;

//    @GetMapping("/")
//    public String getAllPosts(Model model) {
//        List<Post> posts = postService.getAllPosts();
//        model.addAttribute("posts", posts);
//        return "posts";
//    }
    @GetMapping
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public String getPostById(@PathVariable(value = "id") int postId, Model model) {
        Post post = postService.getPostById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));
        model.addAttribute("post", post);
        return "post";
    }

    @GetMapping("/new")
    public String showCreatePostForm(Model model) {
        model.addAttribute("post", new Post());
        return "create_post";
    }

    @PostMapping("/new")
    public String createPost(@ModelAttribute("post") Post post, Model model) {
        User user = userService.findByUsername(post.getUser().getUsername());
        post.setUser(user);
        Post savedPost = postService.saveOrUpdatePost(post);
        String title = post.getTitle();
        return "redirect:/api/perenual/getPlantInfoByCommonName/" + title;
    }

    @GetMapping("/{id}/edit")
    public String showUpdatePostForm(@PathVariable(value = "id") int postId, Model model) {
        Post post = postService.getPostById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));
        model.addAttribute("post", post);
        return "update_post";
    }

    @PostMapping("/{id}/edit")
    public String updatePost(@PathVariable(value = "id") int postId, @RequestBody Post postDetails) {
        Post post = postService.getPostById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));
        post.setTitle(postDetails.getTitle());
        post.setContent(postDetails.getContent());
        Post updatedPost = postService.saveOrUpdatePost(post);
        return "posts";
    }

    @PostMapping("/{postId}/save/{userId}")
    public String savePost(@PathVariable(value = "userId") int userId, @PathVariable(value = "postId") int postId) {
        User user = userService.getUserById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        Post post = postService.getPostById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));
        user.getSavedPosts().add(post);
        userService.saveOrUpdateUser(user);
        return "redirect:/posts/" + postId;
    }

    @PostMapping("/{postId}/unsave/{userId}")
    public String unsavePost(@PathVariable(value = "userId") int userId, @PathVariable(value = "postId") int postId) {
        User user = userService.getUserById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        Post post = postService.getPostById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));
        user.getSavedPosts().remove(post);
        userService.saveOrUpdateUser(user);
        return "redirect:/posts/" + postId;
    }


}



