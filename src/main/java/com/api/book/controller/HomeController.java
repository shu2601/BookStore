package com.api.book.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.api.book.model.Book;
import com.api.book.service.BookService;

@RestController
public class HomeController {

	@Autowired
	private BookService bookService;

	// Get all books
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getBooks() {

		List<Book> list = this.bookService.getAllBook();
		if (list.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(list));

	}

	// Get single book
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBook(@PathVariable("id") int id) {

		Book book = bookService.getBookById(id);

		if (book == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		}

		return ResponseEntity.of(Optional.of(book));
	}

	// create new book
	@PostMapping("/books")
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		try {

			Book b = this.bookService.addBook(book);
			System.out.println(b);
			return ResponseEntity.of(Optional.of(b));
		} catch (Exception e) {

			e.printStackTrace();
			{
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

			}
		}

	}

	// delete book by id
	@DeleteMapping("/books/{bookId}")
	public ResponseEntity<Void> deleteBook(@PathVariable("bookId") int bookId) {
		
		try {
			this.bookService.deleteBook(bookId);

			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
	e.printStackTrace();
	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// update book handler
	@PutMapping("/books/{bookId}")
	public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("bookId") int bookId) {

		try {
			this.bookService.updateBook(book, bookId);
			return ResponseEntity.ok().body(book);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		

	}

}
