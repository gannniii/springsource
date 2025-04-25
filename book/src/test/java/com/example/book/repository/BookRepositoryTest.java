package com.example.book.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.book.entity.Book;
import com.example.book.repositroy.BookRepository;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testInsert() {

        IntStream.rangeClosed(1, 20).forEach(i -> {
            Book book = Book.builder()
                    .title("book title" + i)
                    .author("author" + i)
                    .price(10000 + i)
                    .build();

            bookRepository.save(book);
        });
    }

    @Test
    public void testList() {
        bookRepository.findAll().forEach(book -> System.out.println(book));
    }

    @Test
    public void testGet() {
        System.out.println(bookRepository.findById(5L).get());
    }

    @Test
    public void testUpdate() {
        // 가격 수정
        Book book = bookRepository.findById(5L).get();
        book.setPrice(20000);
        bookRepository.save(book);
    }

    @Test
    public void testRemove() {
        bookRepository.deleteById(20L);
    }
}
