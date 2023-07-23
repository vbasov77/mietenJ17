create table users
(
    id       bigint       NOT NULL AUTO_INCREMENT,
    username varchar(255) not null,
    password varchar(255) not null,
    email    varchar(255) unique,
    primary key (id)
);

# create table users_roles
# (
#     user_id bigint not null,
#     role varchar(255) not null,
#     primary key (user_id, role),
#     foreign key (user_id) references users (id)
# );

# INSERT INTO users (USERNAME, PASSWORD, EMAIL)
# VALUES ('user', '$2a$12$fE.OceeKqcFsvMvtfuJvo.zdQjj0SKIkDrrejMsCacd7dGPD25.nK', '0120912@mail.ru');
#
# insert into users_roles(user_id, role_id)
# VALUES (1, 1),
#        (1, 2);
