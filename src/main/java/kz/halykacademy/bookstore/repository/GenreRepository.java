package kz.halykacademy.bookstore.repository;

import kz.halykacademy.bookstore.dto.AuthorInterfaceDto;
import kz.halykacademy.bookstore.dto.BookInterfaceDto;
import kz.halykacademy.bookstore.entity.Genre;
import kz.halykacademy.bookstore.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository  extends BaseRepository<Genre,Long> {

    @Query(value = "SELECT surname,name,fatherland from Author as a join author_genre as ag on a.id=ag.author_id where ag.genre_id in (:genres)",nativeQuery = true)
    List<AuthorInterfaceDto> findAuthorsByGenres(@Param("genres") List<Long> genres);

    @Query(value = "SELECT g.id from Genre as g where g.name in (:genres)",nativeQuery = true)
    List<Long> findGenresId(@Param("genres") List<String> genres);

    @Query(value = "SELECT id,price,name from Book as b join book_genre as bg on b.id=bg.book_id where bg.genre_id in (:genres)",nativeQuery = true)
    List<BookInterfaceDto> findBooksByGenres(@Param("genres") List<Long> genres);

}
