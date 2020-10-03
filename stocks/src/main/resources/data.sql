DROP TABLE IF EXISTS stock;

CREATE TABLE stock (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    stock_name VARCHAR(4) NOT NULL,
    price NUMBER NOT NULL,
    available_amount NUMBER NOT NULL,
    description VARCHAR(255) NOT NULL
);
