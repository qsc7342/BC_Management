package com.bcm.bcmanager.service.book;

import com.bcm.bcmanager.domain.book.Book;
import com.bcm.bcmanager.repository.book.BookRepository;
import com.bcm.bcmanager.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository repo;

    public Book getBook(String bid) {
        return repo.getById(Long.parseLong(bid));
    }

    public List<Book> getBookList() {
        return repo.findAll();
    }

    public void saveBook(Book book) {
        book.setCredt(DateUtil.now());
        repo.save(book);
    }

    @Transactional
    public void confirmBook(Book book) {
        Book dbData = repo.getById(book.getId());

        dbData.setConfirmyn("Y");
    }

    @Transactional
    public void completeBook(Book book) {
        Book dbData = repo.getById(book.getId());

        dbData.setCompleteyn("Y");
    }

    @Transactional
    public void cancelBook(Book book) {
        Book dbData = repo.getById(book.getId());

        dbData.setCancleyn("Y");
    }
}
