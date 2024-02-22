package com.api.book.restbookmanager.services;

import java.util.List;
// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.book.restbookmanager.dao.BookRepository;
import com.api.book.restbookmanager.entities.Book;

@Component
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    // public static List<Book> bookList = new ArrayList<>();

    // static{

    //     bookList.add(new Book(121,"Programming in C","Denis "));
    //     bookList.add(new Book(131,"Atomic habits","James clear"));
    //     bookList.add(new Book(125,"The 5AM club","Robin Sharma"));
    //     bookList.add(new Book(130,"A Mong who sold his ferrari","Robin Sharma"));
    //     System.out.println("inside static block at the end");
    // }

    // get all books
    public List<Book> getAllBooks(){
        List<Book> bookList = null;
        bookList = (List<Book>)this.bookRepository.findAll(); 
        return bookList;
    }

    // get book by id 
    public Book getBookById( int id){

       Book book =null;

       try{
            book =   this.bookRepository.findById(id);
        }
        catch(Exception e){
            e.printStackTrace();
            return book;
        }
    return book;
        
    }
//  add book 
    public Book addBook(Book book){
        return bookRepository.save(book);   
    }

    // delete book by book id
    public List<Book> deleteBooks(int bookID){
        List<Book> bookList = null;
        bookRepository.deleteById(bookID);
        bookList = (List<Book>)bookRepository.findAll();
        return bookList;
    }

    // update book by id
    public Book  updatBook(Book book, int bookId){

        book.setId(bookId);
        bookRepository.save(book);
        return book;

    }
    
}
