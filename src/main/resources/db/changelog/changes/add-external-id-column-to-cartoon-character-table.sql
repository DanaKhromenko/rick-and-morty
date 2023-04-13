--liquibase formatted sql
--changeset <postgres>:<add-external-id-column-to-cartoon-character-table>

ALTER TABLE public.cartoon_character ADD external_id bigint;

--rollback ALTER TABLE DROP COLUMN external_id;
