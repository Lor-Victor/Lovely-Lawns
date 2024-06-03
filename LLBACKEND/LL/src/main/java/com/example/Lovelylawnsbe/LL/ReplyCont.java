package com.example.Lovelylawnsbe.LL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/replies")
public class ReplyCont {

    @Autowired
    private ReplyServ replyServ;

    @GetMapping("/")
    public ResponseEntity<List<Reply>> getAllReplies() {
        List<Reply> replies = replyServ.getAllReplies();
        return ResponseEntity.ok().body(replies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reply> getReplyById(@PathVariable(value = "id") int replyId) {
        Reply reply = replyServ.getReplyById(replyId).orElseThrow(() -> new ResourceNotFoundException("Reply not found with id: " + replyId));
        return ResponseEntity.ok().body(reply);
    }

    @PostMapping("/")
    public ResponseEntity<Reply> createReply(@RequestBody Reply reply) {
        Reply savedReply = replyServ.saveOrUpdateReply(reply);
        return ResponseEntity.ok().body(savedReply);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reply> updateReply(@PathVariable(value = "id") int replyId, @RequestBody Reply replyDetails) {
        Reply reply = replyServ.getReplyById(replyId).orElseThrow(() -> new ResourceNotFoundException("Reply not found with id: " + replyId));
        reply.setContent(replyDetails.getContent());
        Reply updatedReply = replyServ.saveOrUpdateReply(reply);
        return ResponseEntity.ok().body(updatedReply);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReply(@PathVariable(value = "id") int replyId) {
        replyServ.deleteReply(replyId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/post/{postId}/replies")
    public ResponseEntity<List<Reply>> getRepliesByPost(@PathVariable(value = "postId") int postId) {
        List<Reply> replies = replyServ.getRepliesByPost(postId);
        return ResponseEntity.ok().body(replies);
    }

}


