package maven.java11.java.blog.category.business;

import maven.java11.java.blog.category.domain.Category;
import maven.java11.java.blog.category.domain.CategoryDTO;
import maven.java11.java.blog.category.model.CategoryRequest;
import maven.java11.java.blog.category.model.CategoryResponse;

public class CategoryMapper {

    private static CategoryMapper INSTANCE;

    public static CategoryMapper getINSTANCE() {
        if (INSTANCE == null){
            return new CategoryMapper();
        }
        return INSTANCE;
    }

    public Category requestToEntity(CategoryRequest categoryRequest){
        Category tmp = new Category();
        tmp.setId(categoryRequest.getId());
        tmp.setName(categoryRequest.getName());
        return tmp;
    }
    public CategoryResponse entityToResponse(Category category){
        CategoryResponse tmp = new CategoryResponse();
        tmp.setId(category.getId());
        tmp.setName(category.getName());
        return tmp;
    }
    public CategoryDTO entityToDTO(Category category){
        CategoryDTO tmp = new CategoryDTO();
        tmp.setId(category.getId());
        tmp.setName(category.getName());
        //tmp.getListPost(category);
        return tmp;
    }
}
