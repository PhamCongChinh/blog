package maven.java11.java.blog.post.controller;

import maven.java11.java.blog.post.domain.Post;
import maven.java11.java.blog.post.model.PostRequest;
import maven.java11.java.blog.post.model.PostRes;
import maven.java11.java.blog.post.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final RestTemplate restTemplate;
    private final PostService postService;
    private static final Logger logger = LoggerFactory.getLogger(Post.class);
    public PostController(RestTemplate restTemplate, PostService postService) {
        this.restTemplate = restTemplate;
        this.postService = postService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAll() throws InterruptedException {
        List<PostRes> response = postService.getAll();
        logger.info("Tat ca bai viet: ", response.size());
        if (response.size() > 0){
            CompletableFuture<Post> result = findPost(1);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@Validated @RequestBody PostRequest input) throws InterruptedException{
        postService.create(input);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable long id){
        PostRes response = postService.getOne(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Async
    public CompletableFuture<Post> findPost(long id)throws InterruptedException{
        logger.info("Bat dong bo:  " + id);
        long start = System.currentTimeMillis();
        logger.info(Thread.currentThread().getName());
        String url = String.format("http://localhost:8081/posts/%s", id);
        Post results = restTemplate.getForObject(url, Post.class);
        Thread.sleep(3000L);
        return CompletableFuture.completedFuture(results);
    }
}
