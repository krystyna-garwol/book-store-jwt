package com.example.bookstore.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Book implements Comparable<Book> {
    @Id
    private String id;
    private String title;
    private String author;
    private int rating;
    private String releaseDate;

    @Override
    public int compareTo(Book book) {
        if(this.getRating() == book.getRating()) {
            return 0;
        } else if(this.getRating() > book.getRating()) {
            return -1;
        }
        return 1;
    }
}
