package com.example.Lovelylawnsbe.LL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/replies")
public class ReplyCont {

    @Autowired
    private ReplyServ replyService;

    @GetMapping("/")
    public String getAllReplies(Model model) {
        List<Reply> replies = replyService.getAllReplies();
        model.addAttribute("replies", replies);
        return "replies";
    }

    @GetMapping("/{id}")
    public String getReplyById(@PathVariable(value = "id") int replyId, Model model) {
        Reply reply = replyService.getReplyById(replyId)
                .orElseThrow(() -> new ResourceNotFoundException("Reply not found with id: " + replyId));
        model.addAttribute("reply", reply);
        return "reply";
    }

    @GetMapping("/new")
    public String showCreateReplyForm(Model model) {
        model.addAttribute("reply", new Reply());
        return "create_reply";
    }

    @PostMapping("/new")
    public String createReply(@ModelAttribute Reply reply) {
        replyService.saveOrUpdateReply(reply);
        return "redirect:/replies/";
    }

    @GetMapping("/{id}/edit")
    public String showUpdateReplyForm(@PathVariable(value = "id") int replyId, Model model) {
        Reply reply = replyService.getReplyById(replyId)
                .orElseThrow(() -> new ResourceNotFoundException("Reply not found with id: " + replyId));
        model.addAttribute("reply", reply);
        return "update_reply";
    }

    @PostMapping("/{id}/edit")
    public String updateReply(@PathVariable(value = "id") int replyId, @ModelAttribute Reply replyDetails) {
        Reply reply = replyService.getReplyById(replyId)
                .orElseThrow(() -> new ResourceNotFoundException("Reply not found with id: " + replyId));
        reply.setContent(replyDetails.getContent());
        replyService.saveOrUpdateReply(reply);
        return "redirect:/replies/";
    }

    @PostMapping("/{id}/delete")
    public String deleteReply(@PathVariable(value = "id") int replyId) {
        replyService.deleteReply(replyId);
        return "redirect:/replies/";
    }

    @GetMapping("/post/{postId}/replies")
    public String getRepliesByPost(@PathVariable(value = "postId") int postId, Model model) {
        List<Reply> replies = replyService.getRepliesByPost(postId);
        model.addAttribute("replies", replies);
        return "post_replies";
    }
}



