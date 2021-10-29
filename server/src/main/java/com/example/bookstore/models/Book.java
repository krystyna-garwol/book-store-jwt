package com.example.bookstore.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document
public class Book {
    @Id
    private String id;
    private String title;
    private String author;
    private int rating;
    private String releaseDate;
    private String userId;
}
