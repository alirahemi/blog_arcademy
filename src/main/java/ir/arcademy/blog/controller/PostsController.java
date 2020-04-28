package ir.arcademy.blog.controller;

import ir.arcademy.blog.model.Posts;
import ir.arcademy.blog.service.CategoryService;
import ir.arcademy.blog.service.PostsService;
import ir.arcademy.blog.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostsController {

    private PostsService postsService;
    private CategoryService categoryService;
    private UsersService usersService;


    @Autowired
    public PostsController(PostsService postsService, CategoryService categoryService, UsersService usersService){
        this.postsService = postsService;
        this.categoryService = categoryService;
        this.usersService = usersService;

    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String posts(Model model) {
        model.addAttribute("posts", postsService.findAllPosts());
        return "posts/posts";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage(Model model) {
        model.addAttribute("posts", new Posts());
        model.addAttribute("categories", categoryService.findAllCategories());
        return "posts/registerPosts";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute(name = "posts") @Valid Posts posts,
                           BindingResult bindingResult,Model model, Principal principal) throws IOException, InvocationTargetException, IllegalAccessException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.findAllCategories());
            return "posts/registerPosts";
        }

        posts.setUsers(usersService.findByEmail(principal.getName()));
        postsService.registerPost(posts);
        return "redirect:/posts";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editPage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("posts", postsService.findById(id));
        model.addAttribute("categories", categoryService.findAllCategories());
        return "posts/registerPosts";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        postsService.deleteById(id);
        return "redirect:/posts";
    }

    @RequestMapping(value = "/rest/getPosts", method = RequestMethod.GET)
    public @ResponseBody
    List<Posts> getPosts() {
        return postsService.findAllPosts();
    }

    @RequestMapping(value = "/rest/register", method = RequestMethod.POST)
    public @ResponseBody
    Posts registerPost(@RequestBody Posts posts) throws IOException, InvocationTargetException, IllegalAccessException {
        return postsService.registerPost(posts);
    }
}
