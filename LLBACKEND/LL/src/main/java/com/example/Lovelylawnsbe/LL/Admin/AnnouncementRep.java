package com.example.Lovelylawnsbe.LL.Admin;

import com.example.Lovelylawnsbe.LL.Forum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementRep extends JpaRepository<Announcement, Integer> {
}
