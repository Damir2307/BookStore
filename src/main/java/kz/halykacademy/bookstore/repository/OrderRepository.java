package kz.halykacademy.bookstore.repository;

import kz.halykacademy.bookstore.entity.Orders;
import kz.halykacademy.bookstore.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends BaseRepository<Orders,Long> {
}
