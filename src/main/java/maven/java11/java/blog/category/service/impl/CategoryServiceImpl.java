package maven.java11.java.blog.category.service.impl;

import lombok.val;
import maven.java11.java.blog.category.business.CategoryMapper;
import maven.java11.java.blog.category.domain.Category;
import maven.java11.java.blog.category.domain.CategoryDTO;
import maven.java11.java.blog.category.model.CategoryRequest;
import maven.java11.java.blog.category.model.CategoryResponse;
import maven.java11.java.blog.category.repository.CategoryRepository;
import maven.java11.java.blog.category.service.CategoryService;
import maven.java11.java.blog.post.domain.Post;
import maven.java11.java.blog.post.model.PostRes;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @PersistenceContext
    private final EntityManager em;

    public CategoryServiceImpl(CategoryRepository categoryRepository, EntityManager em) {
        this.categoryRepository = categoryRepository;
        this.em = em;
    }


    @Override
    public List<CategoryResponse> getAll() {
        //List<Category> categories = categoryRepository.getAllProcedure();
        List<Category> categories = getCategories();
        return categories.stream().map(category -> CategoryMapper.getINSTANCE().entityToResponse(category)).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getOne(long id) {
        Category category = getCategory(id);
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());

        val posts = getPostsById(id);
        categoryDTO.setPosts(posts);
        return categoryDTO;
    }

    @Override
    public Category create(CategoryRequest categoryRequest) {
        Optional<Category> category = categoryRepository.findByName(categoryRequest.getName());
        if (category.isPresent()){
            System.out.println("Exists");
        }
        Category categoryEntity = CategoryMapper.getINSTANCE().requestToEntity(categoryRequest);
        return categoryRepository.save(categoryEntity);
    }

    @SuppressWarnings("unchecked")
    public List<Category> getCategories(){
        StoredProcedureQuery query = em.createStoredProcedureQuery("SP_GET_ALL_CATEGORY", Category.class)
                .registerStoredProcedureParameter("P_CURSOR", void.class, ParameterMode.REF_CURSOR);
        query.execute();
        return (List<Category>) query.getResultList();
        //return (List<Category>) query.getOutputParameterValue("P_CURSOR");
    }

    public Category getCategory(long id){
        StoredProcedureQuery query = em.createStoredProcedureQuery("SP_GET_CATEGORY_BY_ID", Category.class)
                .registerStoredProcedureParameter("P_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("P_CURSOR", void.class, ParameterMode.REF_CURSOR);
        query.setParameter("P_ID", id);
        query.execute();
        return (Category) query.getSingleResult();
    }

    @SuppressWarnings("unchecked")
    public List<Post> getPostsById(long id){
        StoredProcedureQuery query = em.createStoredProcedureQuery("SP_GET_POSTS_BY_CATEGORY", Post.class)
                .registerStoredProcedureParameter("P_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("P_CURSOR", void.class, ParameterMode.REF_CURSOR);
        query.setParameter("P_ID", id);
        query.execute();
        return (List<Post>) query.getResultList();
    }
}
