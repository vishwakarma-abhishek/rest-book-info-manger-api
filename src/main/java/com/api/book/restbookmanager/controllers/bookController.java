package com.api.book.restbookmanager.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.restbookmanager.entities.Book;
import com.api.book.restbookmanager.services.BookService;

@RestController
public class bookController {

    @Autowired
    private BookService bookService;

    // get all books handler
    @GetMapping(value = "/books/")
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> list = bookService.getAllBooks();
        if (list.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(list));
    }

    // get book by id handler
    @GetMapping("/books/{id}")
    public ResponseEntity<?> getBook(@PathVariable("id") int id) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(book));
    }

    // add book handler
    @PostMapping("/books/")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book b = null;
        try {
            b = bookService.addBook(book);
            System.out.println(book);
            return new ResponseEntity(b,HttpStatus.CREATED);
            // ResponseEntity<Book> response = new ResponseEntity<>(Book b, HttpStatus.OK);
            // return response;
            // return ResponseEntity.status(HttpStatus.CREATED).of(Optional.of(b)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // delete book handler
    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<List<Book>> deleteBooks(@PathVariable("bookId") int bookId) {
        List<Book> bookList = null;
        try {
            bookList = bookService.deleteBooks(bookId);
            ResponseEntity.BodyBuilder builder = ResponseEntity.status(204);
            builder.body(bookList);
            ResponseEntity<List<Book>> response = builder.build();
            return response;
        } catch (Exception e) {
          
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // update book handler
    @PutMapping("/books/{bookId}")
    public ResponseEntity<Book> updateBooks(@RequestBody Book book, @PathVariable("bookId") int bookId) {

        try {

            this.bookService.updatBook(book, bookId);
            return ResponseEntity.ok().body(book);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();

        }
    }

    // about handler
    @GetMapping(value = "/books/about")
    @ResponseBody
    public String about() {
        return "this is a about PAGE";
    }

    @GetMapping("/hello")
    ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello World!");
    }
}
