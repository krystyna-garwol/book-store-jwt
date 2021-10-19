package com.example.bookstore.controllers;

import com.example.bookstore.models.Book;
import com.example.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/")
    public ResponseEntity<ArrayList<Book>> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBook(@PathVariable String id) {
        return bookService.getBook(id);
    }

    @PostMapping("/")
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PutMapping("/")
    public ResponseEntity<?> updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable String id) {
        return bookService.deleteBook(id);
    }
}
