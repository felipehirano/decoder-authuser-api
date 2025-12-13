CREATE TABLE tb_users (
    user_id UUID PRIMARY KEY,
    user_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    full_name VARCHAR(100),
    user_status VARCHAR(20),
    user_type VARCHAR(20),
    phone_number VARCHAR(20),
    creation_date TIMESTAMP,
    last_update_date TIMESTAMP,
    image_url VARCHAR(255)   
);