package com.api.book.restbookmanager.dao;

import com.api.book.restbookmanager.entities.Book;


import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book,Integer> {

	

    public Book findById(int id);
    
}
