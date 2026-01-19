-- ===============================
-- CATEGORY TABLE
-- ===============================
create table if not exists category
(
    id          bigint primary key,
    name        varchar(255) not null,
    description varchar(255),
    constraint uk_category_name unique (name)
);

-- ===============================
-- PRODUCT TABLE
-- ===============================
create table if not exists product
(
    id                 bigint primary key,
    name               varchar(255),
    description        varchar(255),
    available_quantity double precision not null,
    price              numeric(38, 2),
    category_id        bigint,
    constraint fk_product_category
        foreign key (category_id)
            references category (id)
);

-- ===============================
-- SEQUENCES
-- ===============================
create sequence if not exists category_seq start with 1 increment by 50;
create sequence if not exists product_seq start with 1 increment by 50;

-- ===============================
-- SEQUENCES
-- ===============================
create sequence if not exists category_seq start with 1 increment by 50;
create sequence if not exists product_seq start with 1 increment by 50;

