package kz.halykacademy.bookstore.service.api;

import kz.halykacademy.bookstore.dto.AuthorDto;
import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.service.base.BaseService;

import java.util.List;

public interface AuthorService extends BaseService<Author,Long> {
    Author getById(Long id);
    Author createAuthor(AuthorDto authorDto);
    void deleteAuthor(Long id);
    List<Author> getListOfAuthorsByName(String name);
}
