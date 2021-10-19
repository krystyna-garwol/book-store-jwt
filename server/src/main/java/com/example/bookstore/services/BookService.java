package com.example.bookstore.services;

import com.example.bookstore.models.Book;
import com.example.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public ResponseEntity<ArrayList<Book>>getBooks() {
        ArrayList<Book> books = (ArrayList<Book>) bookRepository.findAll();
        Collections.sort(books);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    public ResponseEntity<?> getBook(String id) {
        Optional<Book> book = bookRepository.findById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    public ResponseEntity<?> addBook(Book book) {
        Book oldBook = bookRepository.findByTitle(book.getTitle());
        if(oldBook != null) return new ResponseEntity<>(HttpStatus.CONFLICT);

        bookRepository.save(book);
        ArrayList<Book> books = (ArrayList<Book>) bookRepository.findAll();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    public ResponseEntity<?> updateBook(Book book) {
        Optional<Book> oldBook = bookRepository.findById(book.getId());
        oldBook.get().setTitle(book.getTitle());
        oldBook.get().setAuthor(book.getAuthor());
        oldBook.get().setRating(book.getRating());
        oldBook.get().setReleaseDate(book.getReleaseDate());
        bookRepository.save(oldBook.get());
        ArrayList<Book> books = (ArrayList<Book>) bookRepository.findAll();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteBook(String id) {
        bookRepository.deleteById(id);
        ArrayList<Book> books = (ArrayList<Book>) bookRepository.findAll();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
