package com.example.Lovelylawnsbe.LL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReplyServ {

    @Autowired
    private ReplyRep replyRep;

    public List<Reply> getAllReplies() {
        return replyRep.findAll();
    }

    public Optional<Reply> getReplyById(int id) {
        return replyRep.findById(id);
    }

    public Reply saveOrUpdateReply(Reply reply) {
        return replyRep.save(reply);
    }

    public void deleteReply(int id) {
        replyRep.deleteById(id);
    }

    public List<Reply> getRepliesByPost(int postId) {
        return replyRep.findByPost_PostId(postId);
    }
}


