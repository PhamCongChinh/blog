package maven.java11.java.blog.category.repository;

import maven.java11.java.blog.category.domain.Category;
import org.hibernate.hql.internal.ast.ParameterTranslationsImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import javax.persistence.ParameterMode;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Category, Long> {

//    @Procedure(name = Category.NamedQuery_GetAllCategory)
//    List<Category> getAllProcedure();

    Optional<Category> findByName(String name);
}
