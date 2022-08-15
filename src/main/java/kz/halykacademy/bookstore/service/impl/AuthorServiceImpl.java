package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.AuthorDto;
import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.repository.AuthorRepository;
import kz.halykacademy.bookstore.service.api.AuthorService;
import kz.halykacademy.bookstore.service.base.BaseServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorServiceImpl extends BaseServiceImpl<Author, Long, AuthorRepository> implements AuthorService {
    @Override
    public Author getById(Long id) {
        return findOrThrowNotFound(id);
    }

    @Override
    public Author createAuthor(AuthorDto authorDto) {
        return save(Author.builder()
                .name(authorDto.getName())
                .surname(authorDto.getSurname())
                .fatherName(authorDto.getFatherName())
                .build());
    }

    @Override
    public void deleteAuthor(Long id) {
        getRepository().deleteById(id);
    }

    @Override
    public List<Author> getListOfAuthorsByName(String name) {
        return getRepository().findAuthorsWithPartOfName(name);
    }
}
