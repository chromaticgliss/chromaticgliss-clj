CREATE OR REPLACE FUNCTION update_updated_at_column()
       RETURNS TRIGGER AS '
       BEGIN
	NEW.updated_at = NOW();
	RETURN NEW;
       END;
       ' LANGUAGE 'plpgsql';

CREATE TABLE users (
       id    serial PRIMARY KEY,
       name  varchar(60) NOT NULL CHECK (name <> ''),
       email varchar(60) NOT NULL UNIQUE,
       auth_level varchar(12) NOT NULL DEFAULT 'user'
       password_digest varchar(162) NOT NULL
       created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
       updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
       active boolean NOT NULL DEFAULT FALSE
);

UPDATE users SET password_digest = 'invalid';

CREATE TRIGGER update_updated_at_users
       BEFORE UPDATE ON users FOR EACH ROW EXECUTE
       PROCEDURE update_updated_at_column();

CREATE TABLE auth_tokens (
    id          VARCHAR(44) PRIMARY KEY,
    user_id     INTEGER REFERENCES users(id) ON DELETE CASCADE,
    created_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX ON auth_tokens (id, created_at DESC);

CREATE TABLE posts (
       id    	   serial PRIMARY KEY,
       title 	   varchar(60) NOT NULL CHECK (title <> ''),
       slug  	   varchar(60) NOT NULL UNIQUE,
       user_id 	   integer REFERENCES users(id) ON DELETE CASCADE,
       body  	   text  NOT NULL,
       created_at  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
       updated_at  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
);

CREATE TRIGGER update_updated_at_posts
       BEFORE UPDATE ON users FOR EACH ROW EXECUTE
       PROCEDURE update_updated_at_column();
