package com.example.bookstore.controllers;

import com.example.bookstore.models.Book;
import com.example.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> books = bookService.getBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable String id) {
        Optional<Book> book = bookService.getBook(id);
        return book.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Optional<Book> existingBook = bookService.findByTitle(book.getTitle());
        if(existingBook.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        Book newBook = bookService.addBook(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @PutMapping("/books")
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        Book newBook = bookService.updateBook(book);
        return new ResponseEntity<>(newBook, HttpStatus.OK);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable String id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
