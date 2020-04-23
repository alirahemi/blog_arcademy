package ir.arcademy.blog.controller;

import ir.arcademy.blog.model.Posts;
import ir.arcademy.blog.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostsController {

    private PostsService postsService;

    @Autowired
    public PostsController(PostsService postsService){
        this.postsService = postsService;
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public List<Posts> getPosts(){
       return this.postsService.findAllPosts();
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.POST)
    public Posts createPost(@RequestBody Posts posts){
       return this.postsService.createPost(posts);
    }
}
