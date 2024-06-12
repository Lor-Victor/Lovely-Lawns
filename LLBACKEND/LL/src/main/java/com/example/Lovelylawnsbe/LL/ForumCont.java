package com.example.Lovelylawnsbe.LL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/forums")
public class ForumCont {

    @Autowired
    private ForumServ forumServ;

    @Autowired
    private PostServ postServ;

    @Autowired
    private UserServ userServ;

    @GetMapping("/")
    public ResponseEntity<List<Forum>> getAllForums() {
        return ResponseEntity.ok(forumServ.getAllForums());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Forum> getForumById(@PathVariable(value = "id") int forumId) {
        Forum forum = forumServ.getForumById(forumId).orElseThrow(() -> new ResourceNotFoundException("Forum not found with id: " + forumId));
        return ResponseEntity.ok().body(forum);
    }

    @PostMapping("/")
    public ResponseEntity<Forum> createForum(@RequestBody Forum forum) {
        Forum savedForum = forumServ.saveOrUpdateForum(forum);
        return ResponseEntity.ok().body(savedForum);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Forum> updateForum(@PathVariable(value = "id") int forumId, @RequestBody Forum forumDetails) {
        Forum forum = forumServ.getForumById(forumId).orElseThrow(() -> new ResourceNotFoundException("Forum not found with id: " + forumId));
        forum.setTitle(forumDetails.getTitle());
        forum.setContent(forumDetails.getContent());
        Forum updatedForum = forumServ.saveOrUpdateForum(forum);
        return ResponseEntity.ok().body(updatedForum);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteForum(@PathVariable(value = "id") int forumId) {
        forumServ.deleteForum(forumId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{forumId}/posts")
    public ResponseEntity<List<Post>> getPostsInForum(@PathVariable(value = "forumId") int forumId) {
        Forum forum = forumServ.getForumById(forumId).orElseThrow(() -> new ResourceNotFoundException("Forum not found with id: " + forumId));
        return ResponseEntity.ok().body(forum.getPosts());
    }

    @PostMapping("/{forumId}/posts")
    public ResponseEntity<Post> createPostInForum(@PathVariable(value = "forumId") int forumId, @RequestBody Post post) {
        Forum forum = forumServ.getForumById(forumId).orElseThrow(() -> new ResourceNotFoundException("Forum not found with id: " + forumId));
        User user = userServ.findByUsername(post.getUser().getUsername());
        post.setUser(user);
        post.setForum(forum);
        Post savedPost = postServ.saveOrUpdatePost(post);
        return ResponseEntity.ok().body(savedPost);
    }

    @PutMapping("/{forumId}/posts/{postId}")
    public ResponseEntity<Post> updatePostInForum(@PathVariable(value = "forumId") int forumId, @PathVariable(value = "postId") int postId, @RequestBody Post postDetails) {
        Forum forum = forumServ.getForumById(forumId).orElseThrow(() -> new ResourceNotFoundException("Forum not found with id: " + forumId));
        Post post = postServ.getPostById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));
        post.setTitle(postDetails.getTitle());
        post.setContent(postDetails.getContent());
        Post updatedPost = postServ.saveOrUpdatePost(post);
        return ResponseEntity.ok().body(updatedPost);
    }

    @DeleteMapping("/{forumId}/posts/{postId}")
    public ResponseEntity<?> deletePostInForum(@PathVariable(value = "forumId") int forumId, @PathVariable(value = "postId") int postId) {
        Forum forum = forumServ.getForumById(forumId).orElseThrow(() -> new ResourceNotFoundException("Forum not found with id: " + forumId));
        Post post = postServ.getPostById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));
        postServ.deletePost(postId);
        return ResponseEntity.ok().build();
    }
}



