create table product
(
    id bigint primary key,
    name varchar(255) not null,
    description varchar(255),
    available_quantity double precision not null,
    price numeric(38,2),
    category_id bigint,
    constraint fk_product_category
        foreign key (category_id) references category(id)
);

insert into product (id, name, description, available_quantity, price, category_id)
values
    (
        nextval('product_seq'),
        'iPhone 15',
        'Apple smartphone',
        50,
        1499.99,
        (select id from category where name = 'Electronics')
    ),
    (
        nextval('product_seq'),
        'MacBook Pro',
        'Apple laptop',
        20,
        2999.00,
        (select id from category where name = 'Electronics')
    ),
    (
        nextval('product_seq'),
        'Spring Boot in Action',
        'Java Spring Boot book',
        100,
        59.99,
        (select id from category where name = 'Books')
    ),
    (
        nextval('product_seq'),
        'T-Shirt',
        'Cotton T-shirt size M',
        200,
        19.99,
        (select id from category where name = 'Clothing')
    );

