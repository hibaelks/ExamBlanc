CREATE TABLE members(
    id int PRIMARY KEY AUTO_INCREMENT,
    nom varchar(50),
    prenom varchar(50),
    email varchar(100),
    phone varchar(50));
CREATE TABLE incidents(
    ref varchar(50) PRIMARY KEY,
    time timestamp DEFAULT CURRENT_TIMESTAMP,
    status boolean,
    member_id int);
ALTER TABLE incidents ADD CONSTRAINT FOREIGN KEY(member_id) REFERENCES members(id);