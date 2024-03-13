alter user orcl quota unlimited on users;


GRANT UNLIMITED TABLESPACE TO orcl

(schema ORCL) 

CREATE SEQUENCE ENTITY_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;


CREATE TABLE users (
    id NUMBER(10, 0) NOT NULL,
    nome VARCHAR2(255 CHAR),
    apelido VARCHAR2(255 CHAR),
    birth_date DATE,
    CONSTRAINT users_pk PRIMARY KEY (id)
);


CREATE TABLE user_technologies (
    user_id NUMBER(10, 0) NOT NULL,
    technology VARCHAR2(32 CHAR) NOT NULL,
    CONSTRAINT fk_user
        FOREIGN KEY (user_id)
        REFERENCES users (id),
    CONSTRAINT user_technologies_uk UNIQUE (user_id, technology)
);

CREATE INDEX idx_user_technologies_user_id ON user_technologies (user_id);
