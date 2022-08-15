package kz.halykacademy.bookstore.service.base;

import kz.halykacademy.bookstore.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import kz.halykacademy.bookstore.repository.base.BaseRepository;

public abstract class BaseServiceImpl<T, ID extends Serializable, R extends BaseRepository<T, ID>> implements BaseService<T, ID> {

    private R repository;

    @Autowired
    public void setRepository(R repository) {
        this.repository = repository;
    }

    public R getRepository() {
        return repository;
    }


    @Transactional
    public T save(T value) {
        return this.repository.saveAndFlush(value);
    }

    @Transactional
    public T edit(T value) {
        return null;
    }

    @Transactional
    public void delete(T value) {

    }

    @Transactional
    public Optional<T> find(ID id) {
        return Optional.empty();
    }

    @Override
    public T findOrThrowNotFound(ID id) {
        return this.repository.findById(id).orElseThrow(() -> notFoundException(id));
    }

    @Transactional
    public List<T> getAll() {
        return this.repository.findAll();
    }

    @Override
    public NotFoundException notFoundException(ID id) {
        return new NotFoundException("Id: "+id);
    }
}
