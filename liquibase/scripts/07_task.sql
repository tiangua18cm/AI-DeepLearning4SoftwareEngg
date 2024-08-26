-- liquibase formatted sql
-- changeset dabico:8

CREATE TABLE "configuration" (
    "key" text PRIMARY KEY NOT NULL,
    "value" text NOT NULL
);

CREATE TABLE "task" (
    "id" bigint PRIMARY 