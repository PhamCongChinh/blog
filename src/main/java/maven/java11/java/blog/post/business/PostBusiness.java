package maven.java11.java.blog.post.business;

import maven.java11.java.blog.post.domain.Post;
import maven.java11.java.blog.post.model.PostRequest;
import maven.java11.java.blog.post.model.PostRes;

public class PostBusiness {
    private static PostBusiness INSTANCE;
    public static PostBusiness getInstance(){
        if (INSTANCE == null){
            return new PostBusiness();
        }
        return INSTANCE;
    }
    public PostRes toResponse(Post post){
        PostRes tmp = new PostRes();
        tmp.setId(post.getId());
        tmp.setId_category(post.getId_category());
        tmp.setName(post.getName());
        return tmp;
    }
    public Post toEntity(PostRequest postRequest){
        Post tmp = new Post();
        tmp.setName(postRequest.getName());
        return tmp;
    }
}
