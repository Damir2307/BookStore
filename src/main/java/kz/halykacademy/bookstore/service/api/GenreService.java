package kz.halykacademy.bookstore.service.api;

import kz.halykacademy.bookstore.dto.AuthorDto;
import kz.halykacademy.bookstore.dto.BookDto;
import kz.halykacademy.bookstore.dto.BookGenreDto;
import kz.halykacademy.bookstore.dto.GenreDto;
import kz.halykacademy.bookstore.entity.Genre;
import kz.halykacademy.bookstore.service.base.BaseService;

import java.util.List;

public interface GenreService extends BaseService<Genre,Long> {
    Genre addGenre(GenreDto genreDto);
    void deleteGenre(Long id);
    List<AuthorDto> findAuthorsByGenre(List<String> genres);
    List<BookGenreDto> findBooksByGenre(List<String> genres);
}
