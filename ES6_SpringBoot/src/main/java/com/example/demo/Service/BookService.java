package com.example.demo.Service;

import com.example.demo.Dto.BookDto;
import com.example.demo.Entity.Book;
import com.example.demo.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDto addBookDto(BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setIsbn(bookDto.getIsbn());
        bookRepository.save(book);
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setIsbn(book.getIsbn());
        return bookDto;

    }

    public List<BookDto> findAll() {
        List<Book> bookList = bookRepository.findAll();
        List<BookDto> bookDtoList = new ArrayList<>();
        for (Book book : bookList) {
            bookDtoList.add(new BookDto(book.getId(), book.getTitle(), book.getAuthor(), book.getIsbn()));
        }
        return bookDtoList;
    }
    public BookDto findById(Long id){
        Optional<Book> optionalBook=bookRepository.findById(id);
        if(optionalBook.isPresent()){
            Book book=optionalBook.get();
            BookDto bookDto=new BookDto(book.getId(),book.getTitle(),book.getAuthor(), book.getIsbn());
            return bookDto;
        }else{
            throw  new  ResponseStatusException(HttpStatus.NOT_FOUND,"Libro non trovato");
        }
    }
    public BookDto updateById(Long id, BookDto bookDto){
        Book book =new Book();
        book.setId(id);
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setIsbn(bookDto.getIsbn());
        bookRepository.save(book);
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setIsbn(book.getIsbn());
        return bookDto;
    }

    public BookDto deleteById(Long id){
        Optional<Book>optionalBook=bookRepository.findById(id);
        if(optionalBook.isPresent()){
             bookRepository.delete(optionalBook.get());
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return null;
    }



}
