CREATE TABLE book
(
    id               SERIAL8             NOT NULL,
    title            VARCHAR(255)       NOT NULL,
    isbn             VARCHAR(13) UNIQUE NOT NULL,
    publication_date TIMESTAMP(6),

    CONSTRAINT pk_book PRIMARY KEY (id)
)