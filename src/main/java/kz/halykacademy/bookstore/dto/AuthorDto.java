package kz.halykacademy.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class AuthorDto {

    private String surname;

    private String name;

    private String fatherName;
}
