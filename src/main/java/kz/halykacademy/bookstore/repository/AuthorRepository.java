package kz.halykacademy.bookstore.repository;

import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.entity.Publisher;
import kz.halykacademy.bookstore.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends BaseRepository<Author,Long>{
    @Query("SELECT a from Author a where a.name LIKE CONCAT('%',:name,'%')")
    List<Author> findAuthorsWithPartOfName(@Param("name") String name);


}
