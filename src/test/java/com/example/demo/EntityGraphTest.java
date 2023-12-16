package com.example.demo;

import com.example.demo.duplicated.entity.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EntityGraphTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void findAuthorsLeftJoinFetchNoEntityGraph() {
        var authors = authorRepository.findAuthorsLeftJoinFetchNoEntityGraph();
        assertThat(authors).hasSize(6);
    }

    @Test
    void findAuthorsLeftJoinFetchWithEntityGraph() {
        var authors = authorRepository.findAuthorsLeftJoinWithEntityGraph();
        assertThat(authors).hasSize(3);
        // Explanation
        // Deduplication occur because EntityGraph + Fetch induced by it activate deduplication
    }

    @Test
    void findAuthorsLeftJoinWithoutEntityGraph() {
        var authors = authorRepository.findAuthorsLeftJoinWithoutEntityGraph();
        assertThat(authors).hasSize(6);
        // Explanation
        // Classic join induce duplicate
    }

    @Test
    void findAuthorsLeftJoinWithEntityGraph() {
        var authors = authorRepository.findAuthorsLeftJoinWithEntityGraph();
        assertThat(authors).hasSize(3);
        // Explanation
        // Classic join induce duplicate
    }

    @Test
    void findAuthorsWithJoinNotFetchedAndEntityGraph() {
        var authors = authorRepository.findAuthorsWithJoinNotFetchedAndEntityGraph();
        assertThat(authors).hasSize(5);
        // WHY 5 ?????
        // Explanation
        // ResultSet does return an array of all selected entities when the array.length = 1 it return the entity
        // However when select multiples entities it returns the array and this array has its own reference even if already exist
        // When using IdentitySet to filter duplicate it does not work
        // Node: How does Hibernate knows which entity are in selection ? it will check in the query IF in it is a fetch
        // Example:
        //     FROM Author a                     -> NOT A FETCH -> IS SELECT
        //     INNER JOIN  a.agent               -> NOT A FETCH -> IS SELECT
        //     LEFT OUTER JOIN FETCH a.books     -> A FETCH     -> IS NOT SELECT
        // This will then return the array which cannot be deduplicated due to the reference, if selection was only of
        // Author a, the object return will not be an array but the entity in which THERE ARE REAL DUPLICATE
    }

    @Test
    void findAuthorsWithJoinNotFetchedAndEntityGraphAndSelect() {
        var authors = authorRepository.findAuthorsWithJoinNotFetchedAndEntityGraphAndSelect();
        assertThat(authors).hasSize(2);
        // Fixed with select !
    }
}
