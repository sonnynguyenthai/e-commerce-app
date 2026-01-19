-- ===============================
-- INSERT CATEGORY (IDEMPOTENT)
-- ===============================
insert into category (id, name, description)
values
    (nextval('category_seq'), 'Electronics', 'Electronic devices and gadgets')
on conflict (name) do nothing;

insert into category (id, name, description)
values
    (nextval('category_seq'), 'Books', 'Books and learning materials')
on conflict (name) do nothing;

insert into category (id, name, description)
values
    (nextval('category_seq'), 'Clothing', 'Men and women clothing')
on conflict (name) do nothing;

-- ===============================
-- INSERT PRODUCT (SAFE SUBQUERY)
-- ===============================
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


