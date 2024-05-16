CREATE TABLE author
(
    id         SERIAL       NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,

    CONSTRAINT pk_author PRIMARY KEY (id)
)