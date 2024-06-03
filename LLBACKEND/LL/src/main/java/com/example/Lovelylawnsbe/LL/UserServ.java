package com.example.Lovelylawnsbe.LL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServ {

    @Autowired
    private UserRep userRep;

    public List<User> getAllUsers() {
        return userRep.findAll();
    }

    public Optional<User> getUserById(int id) {
        return userRep.findById(id);
    }

    public User saveOrUpdateUser(User user) {
        return userRep.save(user);
    }

    public void deleteUser(int id) {
        userRep.deleteById(id);
    }

    public User findByUsername(String username) {
        return userRep.findByUsername(username);
    }
}
