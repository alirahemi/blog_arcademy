package ir.arcademy.blog.service;

import ir.arcademy.blog.model.Posts;
import ir.arcademy.blog.model.Users;
import ir.arcademy.blog.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class PostsService {
    private PostsRepository postsRepository;

    @Autowired
    public PostsService(PostsRepository postsRepository){
        this.postsRepository = postsRepository;
    }

    public Posts registerPost(Posts posts) throws IOException {

        String path = ResourceUtils.getFile("classpath:static/img").getAbsolutePath();
        byte[] bytes = posts.getFile().getBytes();
        String name = UUID.randomUUID() + "." + Objects.requireNonNull(posts.getFile().getContentType()).split("/")[1];
        Files.write(Paths.get(path + File.separator + name), bytes);
        posts.setCover(name);

        return this.postsRepository.save(posts);
    }

    public List<Posts> findAllPosts(){
        return this.postsRepository.findAll();
    }

    public void deleteById(Long id) {
        postsRepository.deleteById(id);
    }

    public Posts findById(Long id) {
        return postsRepository.getOne(id);
    }
}
