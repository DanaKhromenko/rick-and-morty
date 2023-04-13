--liquibase formatted sql
--changeset <postgres>:<create-cartoon-character-table>
CREATE TABLE IF NOT EXISTS public.cartoon_character
(
    id bigint NOT NULL,
    name character varying(256) NOT NULL,
    status character varying(256) NOT NULL,
    gender character varying(256) NOT NULL,
    species character varying(256) NOT NULL,
    CONSTRAINT cartoon_character_pk PRIMARY KEY (id)
);

--rollback DROP TABLE cartoon_character;
