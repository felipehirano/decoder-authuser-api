INSERT INTO tb_users (
    user_id, user_name, email, password, full_name,
    user_status, user_type, phone_number, creation_date, last_update_date
)
VALUES (
    '371f5a51-d885-40fa-81a9-9c85985cfb69',
    'johnoliver',
    'john.oliver@example.com',
    'Ab123456#',
    'John Oliver',
    'ACTIVE',
    'USER',
    '11985963214',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);