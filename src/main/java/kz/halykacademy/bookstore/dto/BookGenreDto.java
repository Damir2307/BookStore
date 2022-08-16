package kz.halykacademy.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookGenreDto {
    private Long id;
    private Integer price;

    private String name;

}
