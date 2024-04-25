package com.example.FakeApiClient.Controllers;

import com.example.FakeApiClient.Models.Category;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products/categories")
public class CategoryController {
    @GetMapping("/all")
    private String getAllCategories() {
        return "All categories";
    }

    @GetMapping("/{categoryID}")
    private String getCategoryById(@PathVariable("categoryID") Long categoryID) {
        return "Category by ID " + categoryID;
    }
}
