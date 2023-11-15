package com.example.demo.duplicated.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Table(name = "agent")
@Entity
@Data
public class Agent {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
}

