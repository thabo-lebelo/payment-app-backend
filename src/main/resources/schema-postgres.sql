DROP TABLE IF EXISTS payment;

CREATE TABLE payment (
    id bigint NOT NULL,
    bank_name character varying(255),
    account_holder character varying(255),
    account_number character varying(255),
    amount numeric(19,2) NOT NULL
);