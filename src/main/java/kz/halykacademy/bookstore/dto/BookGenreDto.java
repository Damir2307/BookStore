package kz.halykacademy.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class BookGenreDto {
    private Long id;
    private Integer price;

    private String name;

}
