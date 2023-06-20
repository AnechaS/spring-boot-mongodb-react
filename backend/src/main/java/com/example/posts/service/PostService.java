package com.example.posts.service;

import com.example.posts.exception.ResourceNotFound;
import com.example.posts.model.Post;
import com.example.posts.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Post> find() {
        return postRepository.findAll();
    }

    public Post findById(String id) {
        return postRepository.findById(id).orElseThrow(() -> new ResourceNotFound());
    }

    public Post create(Post data) {
        return postRepository.insert(data);
    }

    public Post update(String id, Post data) {
        Query query = new Query(Criteria.where("id").is(id));
        FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true);
        Update update = new Update();
        if (data.title != null) update.set("title", data.title);
        if (data.body != null) update.set("body", data.body);

        Post post = mongoTemplate.findAndModify(query, update, options, Post.class);
        if (post == null) throw new ResourceNotFound();

        return post;
    }

    public void delete(String id) {
        Post post = findById(id);
        postRepository.delete(post);
    }
}
