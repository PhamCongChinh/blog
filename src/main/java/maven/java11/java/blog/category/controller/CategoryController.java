package maven.java11.java.blog.category.controller;

import maven.java11.java.blog.category.domain.CategoryDTO;
import maven.java11.java.blog.category.model.CategoryRequest;
import maven.java11.java.blog.category.model.CategoryResponse;
import maven.java11.java.blog.category.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@Transactional(readOnly = true)
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public ResponseEntity<List<CategoryResponse>> getAll(){
        List<CategoryResponse> categories = categoryService.getAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody CategoryRequest input){
        categoryService.create(input);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getOne(@PathVariable long id){
        CategoryDTO categoryDTO = categoryService.getOne(id);
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

}
