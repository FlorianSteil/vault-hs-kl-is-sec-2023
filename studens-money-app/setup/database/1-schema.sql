CREATE TABLE IF NOT EXISTS bankid (
   id          serial        PRIMARY KEY,
   studentid   serial        Not NULL,
   iban        VARCHAR(255)  NOT NULL
);

CREATE TABLE IF NOT EXISTS studens (
   id          serial        PRIMARY KEY,
   first_name  VARCHAR(50)   NOT NULL,
   last_name   VARCHAR(50)   NOT NULL,
   email       VARCHAR(255)  NOT NULL,
   phone       VARCHAR(15)   NOT NULL
);
