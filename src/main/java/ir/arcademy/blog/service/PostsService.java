package ir.arcademy.blog.service;

import ir.arcademy.blog.model.Posts;
import ir.arcademy.blog.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostsService {
    private PostsRepository postsRepository;

    @Autowired
    public PostsService(PostsRepository postsRepository){
        this.postsRepository = postsRepository;
    }

    public Posts createPost(Posts posts){
        return this.postsRepository.save(posts);
    }

    public List<Posts> findAllPosts(){
        return this.postsRepository.findAll();
    }

}
