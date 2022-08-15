package kz.halykacademy.bookstore.service.api;

import kz.halykacademy.bookstore.dto.BookDto;
import kz.halykacademy.bookstore.entity.Book;
import kz.halykacademy.bookstore.service.base.BaseService;

import java.util.List;

public interface BookService extends BaseService<Book,Long> {
    Book addBook(BookDto bookDto);
    Book getById(Long id);
    void deleteById(Long id);
    List<Book> findBooksByName(String name);
}
