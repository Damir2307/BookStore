package kz.halykacademy.bookstore.service.api;

import kz.halykacademy.bookstore.dto.OrderDto;
import kz.halykacademy.bookstore.dto.OrderUpdateDetailsDto;
import kz.halykacademy.bookstore.entity.Orders;
import kz.halykacademy.bookstore.service.base.BaseService;

public interface OrderService extends BaseService<Orders,Long> {
    Orders create(OrderDto orderDto);
    Orders updateDetails(Long id,OrderUpdateDetailsDto orderUpdateDetailsDto);
    Orders updateStatus(Long id,Orders.Status status);
}
