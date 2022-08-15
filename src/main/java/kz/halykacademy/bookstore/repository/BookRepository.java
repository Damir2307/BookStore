package kz.halykacademy.bookstore.repository;

import kz.halykacademy.bookstore.entity.Book;
import kz.halykacademy.bookstore.entity.Publisher;
import kz.halykacademy.bookstore.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends BaseRepository<Book,Long> {
    @Query("SELECT b from Book b where b.name LIKE CONCAT('%',:name,'%')")
    List<Book> findBooksWithPartOfName(@Param("name") String name);
}
