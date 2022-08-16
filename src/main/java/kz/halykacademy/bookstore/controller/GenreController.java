package kz.halykacademy.bookstore.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.halykacademy.bookstore.dto.AuthorDto;
import kz.halykacademy.bookstore.dto.BookGenreDto;
import kz.halykacademy.bookstore.dto.GenreDto;
import kz.halykacademy.bookstore.entity.Genre;
import kz.halykacademy.bookstore.service.api.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
@Api(description = "Жанры APIs", tags = "Жанры(Genres)")
@RequestMapping("/v1/api/genre")
public class GenreController {
    private final GenreService genreService;

    @GetMapping("/get-all")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @ApiOperation(value = "Список жанров", notes = "Список жанров")
    public ResponseEntity<List<Genre>> getAll() {
        return ResponseEntity.ok(genreService.getAll());
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Создать жанр", notes = "Создать жанр")
    public ResponseEntity<Genre> createGenre(@RequestBody GenreDto genreDto) {
        return ResponseEntity.ok(genreService.addGenre(genreDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Удалить жанр", notes = "Удалить жанр")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/find-authors-by-genre")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @ApiOperation(value = "Поиск авторов по жанрам", notes = "Поиск авторов по жанрам")
    public ResponseEntity<List<AuthorDto>> findAuthorsByGenre(@RequestBody List<String> genres) {
        return ResponseEntity.ok(genreService.findAuthorsByGenre(genres));
    }

    @PostMapping("/find-books-by-genre")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @ApiOperation(value = "Поиск книг по жанрам", notes = "Поиск книг по жанрам")
    public ResponseEntity<List<BookGenreDto>> findBooksByGenre(@RequestBody List<String> genres) {
        return ResponseEntity.ok(genreService.findBooksByGenre(genres));
    }

}
