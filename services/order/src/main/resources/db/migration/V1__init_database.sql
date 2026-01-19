-- =========================
-- TABLE: customer_order
-- =========================
CREATE TABLE customer_order (
                                id SERIAL PRIMARY KEY,
                                reference VARCHAR(255),
                                total_amount NUMERIC(19,2),
                                payment_method VARCHAR(50),
                                customer_id VARCHAR(255),
                                created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                updated_at TIMESTAMP
);

-- =========================
-- TABLE: order_line
-- =========================
CREATE TABLE order_line (
                            id SERIAL PRIMARY KEY,
                            order_id INTEGER NOT NULL,
                            product_id INTEGER NOT NULL,
                            quantity DOUBLE PRECISION NOT NULL,

                            CONSTRAINT fk_order_line_order
                                FOREIGN KEY (order_id)
                                    REFERENCES customer_order (id)
                                    ON DELETE CASCADE
);