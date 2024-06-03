package com.example.Lovelylawnsbe.LL;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostCont {

    @Autowired
    private ReplyServ replyServ;
    @Autowired
    private PostServ postServ;
    @Autowired
    private UserServ userServ;

    @GetMapping("/")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postServ.getAllPosts();
        return ResponseEntity.ok().body(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable(value = "id") int postId) {
        Post post = postServ.getPostById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));
        return ResponseEntity.ok().body(post);
    }

    @PostMapping("/")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        User user = userServ.findByUsername(post.getUser().getUsername());
        post.setUser(user);
        Post savedPost = postServ.saveOrUpdatePost(post);
        return ResponseEntity.ok().body(savedPost);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable(value = "id") int postId, @RequestBody Post postDetails) {
        Post post = postServ.getPostById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));
        post.setTitle(postDetails.getTitle());
        post.setContent(postDetails.getContent());
        Post updatedPost = postServ.saveOrUpdatePost(post);
        return ResponseEntity.ok().body(updatedPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable(value = "id") int postId) {
        postServ.deletePost(postId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{userId}/save/{postId}")
    public ResponseEntity<?> savePost(@PathVariable(value = "userId") int userId, @PathVariable(value = "postId") int postId) {
        User user = userServ.getUserById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        Post post = postServ.getPostById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));
        user.getSavedPosts().add(post);
        userServ.saveOrUpdateUser(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}/unsave/{postId}")
    public ResponseEntity<?> unsavePost(@PathVariable(value = "userId") int userId, @PathVariable(value = "postId") int postId) {
        User user = userServ.getUserById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        Post post = postServ.getPostById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));
        user.getSavedPosts().remove(post);
        userServ.saveOrUpdateUser(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{postId}/replies")
    public ResponseEntity<List<Reply>> getRepliesUnderPost(@PathVariable(value = "postId") int postId) {
        List<Reply> replies = replyServ.getRepliesByPost(postId);
        return ResponseEntity.ok().body(replies);
    }

}
