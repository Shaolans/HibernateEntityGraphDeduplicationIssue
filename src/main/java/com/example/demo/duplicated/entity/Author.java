package com.example.demo.duplicated.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table(name = "author")
@Entity
@Setter
@Getter
public class Author {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "author")
    private List<Book> books;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "AGENT_ID")
    private Agent agent;
}
