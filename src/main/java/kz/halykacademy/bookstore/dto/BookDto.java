package kz.halykacademy.bookstore.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BookDto {
    private Integer price;

    private String name;

    private Date publishDate;

    private Integer numberOfPages;

    private Long publisherId;

    private List<Long> listAuthorsId;

    private List<Long> listGenreId;

}
