package kz.halykacademy.bookstore.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.halykacademy.bookstore.dto.BookDto;
import kz.halykacademy.bookstore.entity.Book;
import kz.halykacademy.bookstore.service.api.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
@Api(description = "Книги APIs", tags = "Книги(Books)")
@RequestMapping("/v1/api/book")
public class BookController {
    private final BookService bookService;

    @GetMapping("/get-all")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @ApiOperation(value = "Список всех книг", notes = "Список всех книг")
    public ResponseEntity<List<Book>> getAll() {
        return ResponseEntity.ok(bookService.getAll());
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Добавить книгу", notes = "Добавить книгу")
    public ResponseEntity<Book> addBook(@RequestBody BookDto bookDto) {
        return ResponseEntity.ok(bookService.addBook(bookDto));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @ApiOperation(value = "Искать книгу по айди", notes = "Искать книгу по айди")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Удалить книгу", notes = "Удалить книгу")
    public ResponseEntity<Void> deleteBookById(@PathVariable Long id) {
        bookService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find/{name}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @ApiOperation(value = "Искать книгу по имени", notes = "Искать книгу по имени")
    public ResponseEntity<List<Book>> findBooksByName(@PathVariable String name) {
        return ResponseEntity.ok(bookService.findBooksByName(name));
    }
}
