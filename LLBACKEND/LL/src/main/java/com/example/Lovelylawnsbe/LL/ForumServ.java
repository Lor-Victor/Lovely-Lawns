package com.example.Lovelylawnsbe.LL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ForumServ {

    @Autowired
    private ForumRep forumRep;

    public List<Forum> getAllForums() {
        return forumRep.findAll();
    }

    public Optional<Forum> getForumById(int id) {
        return forumRep.findById(id);
    }

    public Forum saveOrUpdateForum(Forum forum) {
        return forumRep.save(forum);
    }

    public void deleteForum(int id) {
        forumRep.deleteById(id);
    }
}

