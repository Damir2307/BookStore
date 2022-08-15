package kz.halykacademy.bookstore.service.api;

import kz.halykacademy.bookstore.dto.PublisherDto;
import kz.halykacademy.bookstore.entity.Publisher;
import kz.halykacademy.bookstore.service.base.BaseService;

import java.util.List;

public interface PublisherService extends BaseService<Publisher,Long> {
    Publisher createPublisher(PublisherDto publisherDto);
    void deleteById(Long id);
    List<Publisher> getByName(String name);
    Publisher getById(Long id);
}
