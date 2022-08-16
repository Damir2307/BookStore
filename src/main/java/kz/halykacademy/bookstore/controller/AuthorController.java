package kz.halykacademy.bookstore.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.halykacademy.bookstore.dto.AuthorDto;
import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.service.api.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "Авторы APIs", tags = "Авторы(Authors)")
@RestController
@RequestMapping("/v1/api/author")
@AllArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @ApiOperation(value = "Весь список авторов", notes = "Весь список авторов")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/get-all")
    public ResponseEntity<List<Author>> getAll() {
        return ResponseEntity.ok(authorService.getAll());
    }

    @ApiOperation(value = "Искать автора по айди", notes = "Искать автора по айди")
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.getById(id));
    }

    @ApiOperation(value = "Искать автора по имени", notes = "Искать автора по имени")
    @GetMapping("/find/{name}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<Author>> getAuthorsByName(@PathVariable String name) {
        return ResponseEntity.ok(authorService.getListOfAuthorsByName(name));
    }

    @ApiOperation(value = "Создать автора", notes = "Создать автора")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<Author> createAuthor(@RequestBody AuthorDto authorDto) {
        return ResponseEntity.ok(authorService.createAuthor(authorDto));
    }

    @ApiOperation(value = "Удалить автора", notes = "Удалить автора")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.ok().build();
    }


}
