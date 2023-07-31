package com.example.demo.Controller;

import com.example.demo.Dto.BookDto;
import com.example.demo.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<BookDto> findBooks() {
        return bookService.findAll();
    }

    @PostMapping("/add")
    public BookDto addBook(@RequestBody BookDto bookDto) {
        return bookService.addBookDto(bookDto);
    }
    @GetMapping("/{id}")
    public BookDto findById (@PathVariable Long id){
        return bookService.findById(id);
    }
    @PutMapping("/{id}")
    public BookDto updateById(@PathVariable Long id, @RequestBody BookDto bookDto){
        return bookService.updateById(id,bookDto);
    }
    @DeleteMapping("/{id}")
    public BookDto deleteById(@PathVariable Long id){
        return bookService.deleteById(id);
    }



}
