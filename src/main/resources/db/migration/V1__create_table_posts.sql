CREATE TABLE posts (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    content TEXT NOT NULL,
    release_date DATE NOT NULL,
    update_date DATE NOT NULL
);