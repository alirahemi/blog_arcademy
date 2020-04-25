package ir.arcademy.blog.controller;

import ir.arcademy.blog.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    private PostsService postsService;

    @Autowired
    public MainController(PostsService postsService){
        this.postsService = postsService;
    }

    @RequestMapping(value = {"", "/index"})
    public String index(Model model){
        model.addAttribute("posts", postsService.findAllPosts());
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/403")
    public String accessDenied(){
        return "403";
    }
}
