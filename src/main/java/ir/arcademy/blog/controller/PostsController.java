package ir.arcademy.blog.controller;

import ir.arcademy.blog.model.Posts;
import ir.arcademy.blog.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostsController {

    private PostsService postsService;

    @Autowired
    public PostsController(PostsService postsService){
        this.postsService = postsService;
    }

    @GetMapping
    public String showRegisterPosts(){
        return "posts/registerPosts";
    }

    @PostMapping()
    public String registerPosts(@ModelAttribute Posts posts) throws IOException {

        postsService.registerPost(posts);
        return "posts/posts";
    }

    @RequestMapping(value = {"/rest"}, method = RequestMethod.GET)
    public @ResponseBody List<Posts> getPosts(){
       return this.postsService.findAllPosts();
    }

    @RequestMapping(value = {"/rest"}, method = RequestMethod.POST)
    public @ResponseBody Posts createPost(@RequestBody Posts posts) throws IOException {
       return this.postsService.registerPost(posts);
    }
}
