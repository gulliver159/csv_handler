DROP TABLE IF EXISTS results;

CREATE TABLE results (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  content VARCHAR(250) NOT NULL
);