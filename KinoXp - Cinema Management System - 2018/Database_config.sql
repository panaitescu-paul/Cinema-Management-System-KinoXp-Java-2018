SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS customers;
CREATE TABLE customers(
  id INT(5) NOT NULL PRIMARY KEY auto_increment,
  name VARCHAR(45) NOT NULL
);

# DROP TABLE IF EXISTS genres;
# CREATE TABLE genres(
#   id INT(5) NOT NULL auto_increment primary key,
#   name VARCHAR(45) NOT NULL
# );

DROP TABLE IF EXISTS movies;
CREATE TABLE movies(
  id INT(5) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(45) NOT NULL,
  genre VARCHAR(40) NOT NULL,
  age_limit INT(2) NOT NULL
);

DROP TABLE IF EXISTS seats;
CREATE TABLE seats(
  id INT(5) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  space VARCHAR(5) NOT NULL, #1.1, 1.2 ...
  room INT(1) NOT NULL,
  booked INT(1) NOT NULL
);

DROP TABLE IF EXISTS reservations;
CREATE TABLE reservations(
  id INT(5) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  movie_id INT(5) NOT NULL,
  seat VARCHAR(3) NOT NULL,
  date DATE NOT NULL,
  customer_name VARCHAR(25) NOT NULL,
  FOREIGN KEY reservations1(movie_id) REFERENCES movies(id),
  FOREIGN KEY reservations2(customer_id) REFERENCES customers(id),
  FOREIGN KEY reservations3(seat_id) REFERENCES seats(id)
);

DROP TABLE IF EXISTS employees;
CREATE TABLE employees(
  id INT(5) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(45) NOT NULL,
  username VARCHAR(45) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL
);

INSERT INTO employees(name, username, password) VALUES ("Main Employee", "kinoxp", "1000:5053073df23f25a73a255732cb33ee47:ad3a2f9c21ae0674281e0e7d6ebdace4ead7e484f0aef8170dda1c2a7df39dec63b362b69f27b18175dfdebfdfe52eb080437c236398fcab006d83360abfdfaf");