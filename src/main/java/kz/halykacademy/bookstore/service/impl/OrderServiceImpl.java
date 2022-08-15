package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.OrderDto;
import kz.halykacademy.bookstore.dto.OrderUpdateDetailsDto;
import kz.halykacademy.bookstore.entity.Book;
import kz.halykacademy.bookstore.entity.Orders;
import kz.halykacademy.bookstore.entity.User;
import kz.halykacademy.bookstore.repository.OrderRepository;
import kz.halykacademy.bookstore.service.api.BookService;
import kz.halykacademy.bookstore.service.api.OrderService;
import kz.halykacademy.bookstore.service.api.UserService;
import kz.halykacademy.bookstore.service.base.BaseServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl extends BaseServiceImpl<Orders,Long, OrderRepository> implements OrderService {
    private final BookService bookService;
    private final UserService userService;
    @Override
    public Orders create(OrderDto orderDto) {
        Orders orders = Orders.builder()
                .date(orderDto.getDate())
                .userId(orderDto.getUserId())
                .status(Orders.Status.CREATED)
                .build();
        List<Book> bookList = new ArrayList<>();
        int price = 0;
        for (Long i : orderDto.getBookIdList()){
            Book book =bookService.findOrThrowNotFound(i);
            bookList.add(book);
            price+=book.getPrice();
        }
        if (price>10000){
            throw new IllegalArgumentException("Цена заказа превышает 10000тг");
        }
        User user = userService.findOrThrowNotFound(orderDto.getUserId());
        if (user.getIsBlocked()){
            throw new IllegalArgumentException("Пользователь заблокирован");
        }
        orders.setBookList(bookList);
        orders.setPrice(price);
        save(orders);
        return orders;
    }

    @Override
    public Orders updateDetails(Long id,OrderUpdateDetailsDto orderUpdateDetailsDto) {
        Orders orders = findOrThrowNotFound(id);
        List<Book> bookList = new ArrayList<>();
        int price = 0;
        for (Long i : orderUpdateDetailsDto.getBookIdList()){
            Book book =bookService.findOrThrowNotFound(i);
            bookList.add(book);
            price+=book.getPrice();
        }
        if (price>10000){
            throw new IllegalArgumentException("Цена заказа превышает 10000тг");
        }
        orders.setPrice(price);
        orders.setBookList(bookList);
        save(orders);
        return orders;
    }

    @Override
    public Orders updateStatus(Long id, Orders.Status status) {
        Orders orders = findOrThrowNotFound(id);
        orders.setStatus(status);
        save(orders);
        return orders;
    }

}
