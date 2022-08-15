package kz.halykacademy.bookstore.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderUpdateDetailsDto {
    private List<Long> bookIdList;
}
