package com.bcm.bcmanager.rest.book;

import com.bcm.bcmanager.domain.book.Book;
import com.bcm.bcmanager.service.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/rest/book")
public class BookController {

    private final BookService service;

    @GetMapping
    public ResponseEntity<?> getBookList() {
        return new ResponseEntity<>(service.getBookList(), HttpStatus.OK);
    }

    @GetMapping(value = "/{bid}")
    public ResponseEntity<?> getBook(@PathVariable String bid) {
        return new ResponseEntity<>(service.getBook(bid), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveBook(@RequestBody Book book) {
        service.saveBook(book);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/confirm")
    public ResponseEntity<?> confirmBook(@RequestBody Book book) {
        service.confirmBook(book);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/complete")
    public ResponseEntity<?> completeBook(@RequestBody Book book) {
        service.completeBook(book);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/cancel")
    public ResponseEntity<?> cancelBook(@RequestBody Book book) {
        service.cancelBook(book);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
