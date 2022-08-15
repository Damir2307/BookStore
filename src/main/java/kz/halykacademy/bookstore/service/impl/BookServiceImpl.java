package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.BookDto;
import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.entity.Book;
import kz.halykacademy.bookstore.entity.Genre;
import kz.halykacademy.bookstore.repository.AuthorRepository;
import kz.halykacademy.bookstore.repository.BookRepository;
import kz.halykacademy.bookstore.repository.GenreRepository;
import kz.halykacademy.bookstore.service.api.AuthorService;
import kz.halykacademy.bookstore.service.api.BookService;
import kz.halykacademy.bookstore.service.api.GenreService;
import kz.halykacademy.bookstore.service.base.BaseServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl extends BaseServiceImpl<Book,Long, BookRepository> implements BookService {
    private final AuthorService authorService;
    private final AuthorRepository authorRepository;
    private final GenreService genreService;
    private final GenreRepository genreRepository;
    @Override
    public Book addBook(BookDto bookDto) {
        List<Long> authorsId = bookDto.getListAuthorsId();
        Book book = Book.builder()
                .name(bookDto.getName())
                .price(bookDto.getPrice())
                .publishDate(bookDto.getPublishDate())
                .numberOfPages(bookDto.getNumberOfPages())
                .publisherId(bookDto.getPublisherId())
                .build();
        save(book);

        for (Long a:authorsId){
            Author author = authorService.findOrThrowNotFound(a);
            List<Book> bookList = author.getBookList();
            bookList.add(book);
            author.setBookList(bookList);
            authorRepository.save(author);
        }

        for (Long a:bookDto.getListGenreId()){
            Genre genre = genreService.findOrThrowNotFound(a);
            List<Book> bookList = genre.getGenreBookList();
            List<Author> authorList=genre.getGenreAuthorList();
            bookList.add(book);
            for (Long authorId:authorsId) {
                boolean check = true;
                for (Author author:authorList){
                    if (author.getId()==authorId)
                        check=false;
                }
                if (check)
                    authorList.add(authorService.findOrThrowNotFound(authorId));
            }
            genre.setGenreAuthorList(authorList);
            genre.setGenreBookList(bookList);
            genreRepository.save(genre);
        }
        return book;
    }

    @Override
    public Book getById(Long id) {
        return findOrThrowNotFound(id);
    }

    @Override
    public void deleteById(Long id) {
        getRepository().deleteById(id);
    }

    @Override
    public List<Book> findBooksByName(String name) {
        return getRepository().findBooksWithPartOfName(name);
    }
}
