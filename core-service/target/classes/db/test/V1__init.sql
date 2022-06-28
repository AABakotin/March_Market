create table categories (
                            id          bigserial primary key,
                            title       varchar(255),
                            created_at  timestamp default current_timestamp,
                            updated_at  timestamp default current_timestamp
);

create table products (
                          id          bigserial primary key,
                          title       varchar(255),
                          price       numeric(8, 2),
                          category_id bigint references categories (id),
                          created_at  timestamp default current_timestamp,
                          updated_at  timestamp default current_timestamp
);

insert into categories (title) values
                                   ('Еда'),
                                   ('Электроника');

insert into products (title, price, category_id) values
                                                     ('Хлеб', 32.00, 1),
                                                     ('Молоко', 120.00, 1),
                                                     ('Масло', 320.00, 1),
                                                     ('Сыр', 500.00, 1),
                                                     ('Свинина', 450.00, 1),
                                                     ('Вафельная трубочка', 50.00, 1),
                                                     ('Апельсины', 125.00, 1),
                                                     ('Бананы', 101.00, 1),
                                                     ('Огурцы', 80.00, 1),
                                                     ('Креветки', 800.00, 1),
                                                     ('Помидоры', 100.00, 1),
                                                     ('Ветчина', 500.00, 1),
                                                     ('Сок', 150.00, 1),
                                                     ('Торт', 650.00, 1),
                                                     ('Кекс', 35.00, 1),
                                                     ('Курица', 350.00, 1),
                                                     ('Кефир', 90.00, 1);


create table orders
(
    id              bigserial primary key,
    username         varchar (255),
    total_price     numeric(8, 2),
    address         varchar (255),
    phone_number    varchar (20),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

create table orders_items
(
    id                      bigserial primary key,
    order_id                bigint references orders (id),
    product_id              bigint references products (id),
    price_per_product       numeric(8, 2),
    quantity                int,
    price                   numeric(8, 2),
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);