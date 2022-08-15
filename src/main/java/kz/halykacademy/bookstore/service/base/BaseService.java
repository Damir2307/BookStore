package kz.halykacademy.bookstore.service.base;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseService<T, ID extends Serializable> extends Serializable{
    T save(T value);

    T edit(T value);

    void delete(T value);

    Optional<T> find(ID id);

    T findOrThrowNotFound(ID id);

    List<T> getAll();

    RuntimeException notFoundException(ID id);
}
