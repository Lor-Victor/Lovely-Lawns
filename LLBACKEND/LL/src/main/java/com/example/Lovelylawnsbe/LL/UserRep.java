package com.example.Lovelylawnsbe.LL;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRep extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
