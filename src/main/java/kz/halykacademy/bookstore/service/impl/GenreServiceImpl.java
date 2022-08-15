package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.*;
import kz.halykacademy.bookstore.entity.Genre;
import kz.halykacademy.bookstore.repository.GenreRepository;
import kz.halykacademy.bookstore.service.api.GenreService;
import kz.halykacademy.bookstore.service.base.BaseServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GenreServiceImpl extends BaseServiceImpl<Genre,Long, GenreRepository> implements GenreService {
    @Override
    public Genre addGenre(GenreDto genreDto) {
        Genre genre = Genre.builder()
                .name(genreDto.getName())
                .build();
        getRepository().save(genre);
        return genre;
    }

    @Override
    public void deleteGenre(Long id) {
        getRepository().deleteById(id);
    }

    @Override
    public List<AuthorDto> findAuthorsByGenre(List<String> genres) {
        List<Long> list = getRepository().findGenresId(genres);
        List<AuthorInterfaceDto> a = getRepository().findAuthorsByGenres(list);
        List<AuthorDto> authorList = new ArrayList<>();
        for (AuthorInterfaceDto i:a){
            AuthorDto authorGenreDto=new AuthorDto(i.getSurname(),i.getName(),i.getFatherland());
            authorList.add(authorGenreDto);
        }
        return authorList;
    }

    @Override
    public List<BookGenreDto> findBooksByGenre(List<String> genres) {
        List<Long> list = getRepository().findGenresId(genres);
        List<BookInterfaceDto> a = getRepository().findBooksByGenres(list);
        List<BookGenreDto> bookDtoArrayList = new ArrayList<>();
        for (BookInterfaceDto i:a){
            BookGenreDto bookGenreDto=new BookGenreDto(i.getId(),i.getPrice(),i.getName());
            bookDtoArrayList.add(bookGenreDto);
        }
        return bookDtoArrayList;
    }
}
