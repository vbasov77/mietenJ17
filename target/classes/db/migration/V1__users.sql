create table users
(
    id       bigint       NOT NULL AUTO_INCREMENT,
    username varchar(100) not null,
    password varchar(100) not null,
    email    varchar(100) unique,
#     roles    varchar(100) not null,
    primary key (id)
);

INSERT INTO users (id, USERNAME, PASSWORD, EMAIL)
VALUES (1, 'user', '$2a$12$ol.w.Zh1hnQcLrIYfpyK.u2QsgmVObC.qu2MEjoCs0k35FJKq86Zu', '0120912@mail.ru');
