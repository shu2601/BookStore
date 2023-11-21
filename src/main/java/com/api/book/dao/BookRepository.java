package com.api.book.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.api.book.model.Book;


public interface BookRepository extends CrudRepository<Book, Integer>{
	public Book findById(int id);

	public List<Book> findAll();

	public void save(int bookId);

}
