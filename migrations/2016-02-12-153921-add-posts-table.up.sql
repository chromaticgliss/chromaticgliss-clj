CREATE TABLE posts (
       id    	   serial PRIMARY KEY,
       title 	   varchar(60) NOT NULL CHECK (title <> ''),
       slug  	   varchar(60) NOT NULL UNIQUE,
       user_id 	   integer REFERENCES users(id) ON DELETE CASCADE,
       body  	   text  NOT NULL,
       created_at  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
       updated_at  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TRIGGER update_updated_at_posts
       BEFORE UPDATE ON users FOR EACH ROW EXECUTE
       PROCEDURE update_updated_at_column();
