package kz.halykacademy.bookstore.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDto {
    private Date date;
    private Long userId;
    private List<Long> bookIdList;
}
