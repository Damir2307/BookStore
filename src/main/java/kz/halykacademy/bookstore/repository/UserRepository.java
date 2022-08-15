package kz.halykacademy.bookstore.repository;

import kz.halykacademy.bookstore.entity.User;
import kz.halykacademy.bookstore.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User,Long> {
    Optional<User> getByUsername(String username);
    Boolean existsByUsername(String username);
}
