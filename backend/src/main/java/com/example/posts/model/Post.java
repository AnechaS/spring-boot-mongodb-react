package com.example.posts.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("posts")
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    public String id;

    public String title;
    public String body;

    public Post(String title, String body) {
       this.title = title;
       this.body = body;
    }
}
