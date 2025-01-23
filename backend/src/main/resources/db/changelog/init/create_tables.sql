CREATE TABLE customers
(
    id bigserial CONSTRAINT customers_PK PRIMARY KEY,
    name varchar(256) NOT NULL,
    email varchar(256) NOT NULL,
    password varchar(256) NOT NULL,
    city varchar(256) NOT NULL,
    phone_number varchar(256) NOT NULL
);
CREATE TABLE orders
(
    id bigserial CONSTRAINT orders_PK PRIMARY KEY,
    orders_date timestamp NOT NULL,
    total_price decimal NOT NULL,
    status_id bigint NOT NULL,
    customer_id bigint NOT NULL
);
CREATE TABLE order_items
(
    id bigserial CONSTRAINT order_items_PK PRIMARY KEY,
    orders_id bigint NOT NULL,
    products_id bigint NOT NULL,
    quantity bigint NOT NULL
);
CREATE TABLE products
(
    id bigserial CONSTRAINT products_PK PRIMARY KEY,
    product_name varchar(256) NOT NULL,
    price decimal NOT NULL,
    stock_quantity bigint NOT NULL,
    category_id bigint NOT NULL
);
CREATE TABLE categories
(
    id bigserial CONSTRAINT categories_PK PRIMARY KEY,
    name varchar(256) NOT NULL
);
CREATE TABLE order_statuses
(
    id bigserial CONSTRAINT statuses_PK PRIMARY KEY,
    name varchar(256) NOT NULL
);
CREATE TABLE refresh_token
(
    id bigserial CONSTRAINT refresh_token_PK PRIMARY KEY,
    customer_id bigint NOT NULL,
    token varchar(512) NOT NULL
);

CREATE TABLE blacklisted_tokens
(
    id bigserial CONSTRAINT blacklisted_tokens_PK PRIMARY KEY,
    token varchar(512) NOT NULL UNIQUE,
    expiry_date timestamp NOT NULL,
    customer_id bigint NOT NULL
);
CREATE TABLE cart
(
    id bigserial CONSTRAINT сart_PK PRIMARY KEY,
    customer_id bigint NOT NULL
);
CREATE TABLE products_cart
(
    id bigserial CONSTRAINT products_cart_PK PRIMARY KEY,
    cart_id bigint NOT NULL,
    product_id bigint NOT NULL,
    quantity bigint NOT NULL
);