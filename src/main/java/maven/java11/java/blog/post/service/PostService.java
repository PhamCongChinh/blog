package maven.java11.java.blog.post.service;

import maven.java11.java.blog.post.domain.Post;
import maven.java11.java.blog.post.model.PostRequest;
import maven.java11.java.blog.post.model.PostRes;

import java.util.List;

public interface PostService {
    List<PostRes> getAll();
    PostRes getOne(long id);
    Post create(PostRequest input);
}
