package com.example.Lovelylawnsbe.LL;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRep extends JpaRepository<Reply, Integer> {
    List<Reply> findByPost_PostId(int postId);
}

