package ir.arcademy.blog.controller;

import ir.arcademy.blog.model.Users;
import ir.arcademy.blog.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {

    private UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService){
        this.usersService = usersService;
    }

    @RequestMapping(value = "" , method = RequestMethod.GET)
    public String users(Model model) {
        model.addAttribute("users", usersService.findAllUsers());
        return "users/users";
    }

    @RequestMapping(value = "/register" , method = RequestMethod.GET)
    public String registerPage(Model model) {
        model.addAttribute("users", new Users());
        return "users/registerUser";
    }

    @RequestMapping(value = "/edit/{id}" , method = RequestMethod.GET)
    public String editUserPage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("users", usersService.findById(id));
        return "users/registerUser";
    }

    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    public String register(@ModelAttribute Users user) throws IOException, InvocationTargetException, IllegalAccessException {
        usersService.registerUser(user);
        return "redirect:/users";
    }

    @RequestMapping(value = "/rest/getUsers" , method = RequestMethod.GET)
    public @ResponseBody
    List<Users> getUsers() {
        return usersService.findAllUsers();
    }

    @RequestMapping(value = "/rest/register", method = RequestMethod.POST)
    public @ResponseBody
    Users registerUser(@RequestBody Users users) throws IOException, InvocationTargetException, IllegalAccessException {
        return usersService.registerUser(users);
    }


}
