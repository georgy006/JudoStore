ALTER TABLE products
    ADD CONSTRAINT category_id_fk FOREIGN KEY (category_id)
        REFERENCES categories (id);

ALTER TABLE orders
    ADD CONSTRAINT customer_id_fk FOREIGN KEY (customer_id)
        REFERENCES customers (id);

ALTER TABLE orders
    ADD CONSTRAINT status_id_fk FOREIGN KEY (status_id)
        REFERENCES order_statuses (id);

ALTER TABLE order_items
    ADD CONSTRAINT orders_id_fk FOREIGN KEY (orders_id)
        REFERENCES orders (id);

ALTER TABLE order_items
    ADD CONSTRAINT products_id_fk FOREIGN KEY (products_id)
        REFERENCES products (id);
