package com.api.book.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.book.dao.BookRepository;
import com.api.book.model.Book;

@Component
public class BookService {

//	private static List<Book> list = new ArrayList<>();
//
//	static {
//
//		list.add(new Book(11, "java book", "abcd"));
//		list.add(new Book(12, "Python book", "xyz"));
//		list.add(new Book(13, "C++ book", "pqr"));
//	}

	@Autowired
	private BookRepository bookRepo;

	public List<Book> getAllBook() {
		List<Book> book = (List<Book>) bookRepo.findAll();
		return book;
	}

	// return single book by Id
	public Book getBookById(int id) {

		Book book = null;
		try {
			// book = list.stream().filter(e -> e.getId() == id).findFirst().get();

			book = this.bookRepo.findById(id);
			return book;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;

	}

	// create book
	public Book addBook(Book b) {
		// list.add(b);

		Book result = this.bookRepo.save(b);
		return result;
	}

	// delete book by id
	public void deleteBook(int bid) {

		// list = list.stream().filter(book -> book.getId() !=
		// bid).collect(Collectors.toList());

		bookRepo.deleteById(bid);

	}

	// update book

	public void updateBook(Book book, int bookId) {

		/*
		 * list.stream().map(b -> {
		 * 
		 * if (b.getId() == bookId) {
		 * 
		 * b.setName(book.getName()); b.setAuthor(book.getAuthor());
		 * 
		 * } return b;
		 * 
		 * }).collect(Collectors.toList());
		 */

		book.setId(bookId);
		bookRepo.save(book);

	}

}
