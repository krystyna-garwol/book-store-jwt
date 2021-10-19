package com.example.bookstore.repositories;

import com.example.bookstore.models.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    Book findByTitle(String title);

}
