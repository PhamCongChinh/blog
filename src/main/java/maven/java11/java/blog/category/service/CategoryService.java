package maven.java11.java.blog.category.service;

import maven.java11.java.blog.category.domain.Category;
import maven.java11.java.blog.category.domain.CategoryDTO;
import maven.java11.java.blog.category.model.CategoryRequest;
import maven.java11.java.blog.category.model.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAll();
    CategoryDTO getOne(long id);
    Category create(CategoryRequest categoryRequest);
}
