package com.example.Lovelylawnsbe.LL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PostServ {

    @Autowired
    private PostRep postRep;

    public List<Post> getAllPosts() {
        return postRep.findAll();
    }

    public Optional<Post> getPostById(int id) {
        return postRep.findById(id);
    }

    public Post saveOrUpdatePost(Post post) {
        return postRep.save(post);
    }

    public void deletePost(int id) {
        postRep.deleteById(id);
    }
}
