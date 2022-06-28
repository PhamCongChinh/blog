package maven.java11.java.blog.post.service.impl;

import maven.java11.java.blog.post.business.PostBusiness;
import maven.java11.java.blog.post.domain.Post;
import maven.java11.java.blog.post.model.PostRequest;
import maven.java11.java.blog.post.model.PostRes;
import maven.java11.java.blog.post.repository.PostRepository;
import maven.java11.java.blog.post.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<PostRes> getAll() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(post -> PostBusiness.getInstance().toResponse(post)).collect(Collectors.toList());
    }

    @Override
    public PostRes getOne(long id) {
        Post post = postRepository.getById(id);
        return PostBusiness.getInstance().toResponse(post);
    }

    @Override
    public Post create(PostRequest input) {
        Post post = PostBusiness.getInstance().toEntity(input);
        return postRepository.save(post);
    }
}
