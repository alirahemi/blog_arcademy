package ir.arcademy.blog.controller;

import ir.arcademy.blog.model.Category;
import ir.arcademy.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @RequestMapping(value = {"/", ""},method = RequestMethod.GET)
    public List<Category> getCategories(){
        return this.categoryService.findAllCategories();
    }

    @RequestMapping(value = {"/", ""},method = RequestMethod.POST)
    public Category getCategories(@RequestBody Category category){
        return this.categoryService.createCategory(category);
    }
}
