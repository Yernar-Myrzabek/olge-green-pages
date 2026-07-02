package org.olge.greenpages.controller;

import org.olge.greenpages.model.Category;
import org.olge.greenpages.repository.CategoryRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins="*")
public class CategoryController {
    private final CategoryRepository categoryRepository;
    public CategoryController(CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }
    @GetMapping
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
}
