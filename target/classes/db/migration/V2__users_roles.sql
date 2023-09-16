create table users_roles
(
    user_id bigint       not null,
    role    varchar(255) not null,
    primary key (user_id, role),
    foreign key (user_id) references users (id)
);

INSERT INTO users_roles (user_id, role)
VALUES (1, 'USER');


