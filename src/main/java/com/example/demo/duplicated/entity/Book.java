package com.example.demo.duplicated.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Table(name = "book")
@Entity
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue
    private Integer id;

    private String title;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "BOOK_AUTHOR_ID")
    private Author author;
}
