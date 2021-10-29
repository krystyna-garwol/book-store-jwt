package com.example.bookstore.services;

import com.example.bookstore.models.Book;
import com.example.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBook(String id) {
        return bookRepository.findById(id);
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Book book) {
        Optional<Book> existingBook = bookRepository.findById(book.getId());
        existingBook.get().setTitle(book.getTitle());
        existingBook.get().setAuthor(book.getAuthor());
        existingBook.get().setRating(book.getRating());
        existingBook.get().setReleaseDate(book.getReleaseDate());
        return bookRepository.save(existingBook.get());
    }

    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }

    public Optional<Book> findByTitle(String id) {
        return bookRepository.findById(id);
    }

    public List<Book> findAllByUserId(String userId) {
        return bookRepository.findAllByUserId(userId);
    }
}
