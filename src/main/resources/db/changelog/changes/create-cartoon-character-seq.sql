--liquibase formatted sql
--changeset <postgres>:<create-cartoon-character-sequence-id>
CREATE SEQUENCE IF NOT EXISTS public.cartoon_character_id_seq INCREMENT 1 START 1 MINVALUE 1;

--rollback DROP SEQUENCE public.cartoon_character_id_seq;
