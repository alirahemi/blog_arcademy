package ir.arcademy.blog.service;

import ir.arcademy.blog.model.Category;
import ir.arcademy.blog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category){
        return this.categoryRepository.save(category);
    }

    public List<Category> findAllCategories(){
        return this.categoryRepository.findAll();
    }

}
