package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.PublisherDto;
import kz.halykacademy.bookstore.entity.Publisher;
import kz.halykacademy.bookstore.repository.PublisherRepository;
import kz.halykacademy.bookstore.service.api.PublisherService;
import kz.halykacademy.bookstore.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherServiceImpl extends BaseServiceImpl<Publisher,Long, PublisherRepository> implements PublisherService {
    @Override
    public Publisher createPublisher(PublisherDto publisherDto) {
        Publisher publisher = save(Publisher.builder()
                .name(publisherDto.getName())
                .build()
        );
        return publisher;
    }

    @Override
    public void deleteById(Long id) {
        getRepository().deleteById(id);
    }

    @Override
    public List<Publisher> getByName(String name) {
        return getRepository().findPublishersWithPartOfName(name);
    }

    @Override
    public Publisher getById(Long id) {
        return findOrThrowNotFound(id);
    }
}
