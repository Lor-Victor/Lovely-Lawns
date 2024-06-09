package com.example.Lovelylawnsbe.LL;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReplyRep extends JpaRepository<Reply, Integer> {
    List<Reply> findByPost_PostId(int postId);
}

