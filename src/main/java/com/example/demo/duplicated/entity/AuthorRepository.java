package com.example.demo.duplicated.entity;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    @Query("FROM Author a LEFT JOIN FETCH a.books")
    List<Author> findAuthorsLeftJoinFetchNoEntityGraph();

    @EntityGraph(attributePaths = "books")
    @Query("FROM Author a LEFT JOIN a.books")
    List<Author> findAuthorsLeftJoinWithEntityGraph();

    @Query("FROM Author a LEFT JOIN a.books")
    List<Author> findAuthorsLeftJoinWithoutEntityGraph();

    @EntityGraph(attributePaths = "books")
    @Query("FROM Author a LEFT JOIN a.books INNER JOIN a.agent WHERE a.agent.name like '%John%'")
    List<Author> findAuthorsWithJoinNotFetchedAndEntityGraph();

    @EntityGraph(attributePaths = "books")
    @Query("SELECT a FROM Author a LEFT JOIN a.books INNER JOIN a.agent")
    List<Author> findAuthorsWithJoinNotFetchedAndEntityGraphAndSelect();
}
