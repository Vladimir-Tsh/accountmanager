DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS account_operations;

CREATE TABLE accounts (
  id INT AUTO_INCREMENT PRIMARY KEY,
  account VARCHAR(32) NOT NULL,
  state VARCHAR(32) DEFAULT 'OPEN',
  balance FLOAT DEFAULT 0.00
);

CREATE TABLE account_operations (
  id INT AUTO_INCREMENT PRIMARY KEY,
  accountid INT NOT NULL,
  opdate DATE NOT NULL,
  optype VARCHAR(32) NOT NULL,
  opsum DOUBLE NOT NULL,
  pid INT NOT NULL,
  opstate VARCHAR(32) NOT NULL,
  foreign key (accountid) references accounts(id)
);

INSERT INTO accounts (account) VALUES
  ('40410581000000000001');

--INSERT INTO account_operations VALUES (
--  1, 1, '2020-06-16', 'DEBET', 0.01, 1
--);