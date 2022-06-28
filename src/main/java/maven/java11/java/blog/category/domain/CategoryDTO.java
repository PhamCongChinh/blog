package maven.java11.java.blog.category.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import maven.java11.java.blog.post.domain.Post;
import maven.java11.java.blog.post.model.PostRes;

import java.util.List;

@Getter
@Setter
public class CategoryDTO {
    private long id;
    private String name;
    private List<Post> posts;
}
