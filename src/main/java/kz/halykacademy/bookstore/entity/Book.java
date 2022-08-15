package kz.halykacademy.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

//
//@ApiModel(description = "Вопросы")
@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@Table(name = "book")
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(generator = "book_seq", strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer price;

    @Column
    private String name;

    @Column(name = "publish_date")
    private Date publishDate;


    @Column(name = "number_of_pages")
    private Integer numberOfPages;

    @Column(name = "publisher_id")
    private Long publisherId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "publisher_id", insertable = false, updatable = false)
    private Publisher publisher;

    @JsonIgnore
    @ManyToMany(mappedBy = "bookList")
    private List<Author> authors;


    @ManyToMany(mappedBy = "genreBookList")
    private List<Genre> genres;

    @JsonIgnore
    @ManyToMany(mappedBy = "bookList")
    private List<Orders> orders;



}
