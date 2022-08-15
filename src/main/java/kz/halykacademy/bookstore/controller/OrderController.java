package kz.halykacademy.bookstore.controller;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import io.swagger.annotations.Api;
import kz.halykacademy.bookstore.dto.OrderDto;
import kz.halykacademy.bookstore.dto.OrderUpdateDetailsDto;
import kz.halykacademy.bookstore.entity.Orders;
import kz.halykacademy.bookstore.service.api.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
@Api(description="Заказы APIs", tags = "Заказы")
@RequestMapping("/v1/api/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Orders>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAll());
    }

    @PostMapping("/create")
    public ResponseEntity<Orders> createOrder(@RequestBody OrderDto orderDto){
        return ResponseEntity.ok(orderService.create(orderDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrder(@PathVariable Long id){
        return ResponseEntity.ok(orderService.findOrThrowNotFound(id));
    }

    @PutMapping("/update-details/{id}")
    public ResponseEntity<Orders> updateOrderDetails(@PathVariable Long id,@RequestBody OrderUpdateDetailsDto orderDto){
        return ResponseEntity.ok(orderService.updateDetails(id,orderDto));
    }

    @PutMapping("/update-status/{id}")
    public ResponseEntity<Orders> updateOrderStatus(@PathVariable Long id, @RequestParam("status") Orders.Status status){
        return ResponseEntity.ok(orderService.updateStatus(id,status));
    }
}
