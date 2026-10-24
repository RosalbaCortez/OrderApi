CREATE TABLE IF NOT EXISTS orders (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      client_id BIGINT NOT NULL,
                                      client_name VARCHAR(255) NOT NULL,
    total DECIMAL(15, 2) NOT NULL,
    order_date DATETIME NOT NULL
    ) ENGINE=InnoDB
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_unicode_ci;


CREATE TABLE IF NOT EXISTS order_details (
                                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                             order_id BIGINT NOT NULL,
                                             product_id BIGINT NOT NULL,
                                             product_name VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(15, 2) NOT NULL,
    sub_total DECIMAL(15, 2) NOT NULL,

    CONSTRAINT fk_order_details_order
    FOREIGN KEY (order_id)
    REFERENCES orders(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    ) ENGINE=InnoDB
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_unicode_ci;