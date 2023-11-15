CREATE TABLE agent
(
    id INTEGER PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE author
(
    id INTEGER PRIMARY KEY,
    name VARCHAR(255),
    agent_id INTEGER not null,
    FOREIGN KEY (agent_id) REFERENCES agent(id)
);

CREATE TABLE book
(
    id INTEGER PRIMARY KEY,
    title VARCHAR(255),
    book_author_id INTEGER not null,
    FOREIGN KEY (book_author_id) REFERENCES author(id)
);

