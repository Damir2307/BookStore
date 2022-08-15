package kz.halykacademy.bookstore.repository;

import kz.halykacademy.bookstore.entity.Publisher;
import kz.halykacademy.bookstore.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherRepository extends BaseRepository<Publisher,Long> {
    @Query("SELECT p from Publisher p where p.name LIKE CONCAT('%',:name,'%')")
    List<Publisher> findPublishersWithPartOfName(@Param("name") String name);
}
