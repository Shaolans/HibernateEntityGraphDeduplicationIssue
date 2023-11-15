package com.example.demo.duplicated.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthorInit {
    private final AuthorRepository authorRepository;

    @PostConstruct
    private void create() {
        var author = new Author();
        author.setName("Isaac Asimov");
        var book1 = new Book();
        book1.setTitle("Foundation");
        book1.setAuthor(author);
        var book2 = new Book();
        book2.setTitle("Second Foundation");
        book2.setAuthor(author);
        var book3 = new Book();
        book3.setTitle("I, Robot");
        book3.setAuthor(author);
        author.setBooks(List.of(book1, book2, book3 ));
        var agent = new Agent();
        agent.setName("John Doe");
        author.setAgent(agent);
        authorRepository.save(author);
        author = new Author();
        author.setName("J.R.R. Tolkien");
        book1 = new Book();
        book1.setTitle("The Hobbit");
        book1.setAuthor(author);
        book2 = new Book();
        book2.setTitle("LOTR");
        book2.setAuthor(author);
        author.setBooks(List.of(book1, book2));
        agent = new Agent();
        agent.setName("John Done");
        author.setAgent(agent);
        authorRepository.save(author);
        author = new Author();
        author.setName("Unknown");
        agent = new Agent();
        agent.setName("Agent 3");
        author.setAgent(agent);
        authorRepository.save(author);
    }
}
