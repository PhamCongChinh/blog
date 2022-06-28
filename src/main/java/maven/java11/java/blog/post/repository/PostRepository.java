package maven.java11.java.blog.post.repository;

import maven.java11.java.blog.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Procedure(procedureName = "SP_GET_ALL_CATEGORY")
    List<Post> getAll();
}
