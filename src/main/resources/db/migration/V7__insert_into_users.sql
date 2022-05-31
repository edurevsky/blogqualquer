INSERT INTO users ( id, username, password, complete_name )
VALUES (
    1,
    'admin@admin.com',
    '$2a$12$Us4boHigGJW8OYUD8aC5MuBnkij1Ak0qdc9XtypnEqYjbfkfbsOyK',
    'admin'
);

INSERT INTO roles_users ( role_id, user_id )
VALUES ( 1, 1 );