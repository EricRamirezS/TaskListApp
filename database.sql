CREATE DATABASE "tasksDB";

\c tasksDB

CREATE TABLE IF NOT EXISTS public.tasks
(
    id BIGSERIAL NOT NULL ,
    description character varying(60) COLLATE pg_catalog."default" NOT NULL,
    created_at timestamp with time zone NOT NULL DEFAULT now(),
    active boolean NOT NULL,
    CONSTRAINT tasks_pkey PRIMARY KEY (id)
);
