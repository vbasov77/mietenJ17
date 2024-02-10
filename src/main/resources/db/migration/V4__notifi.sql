create table notifications
(
    id     bigint NOT NULL AUTO_INCREMENT,
    user_id bigint NOT NULL,
    primary key (id),
    foreign key (user_id) references users (id)
);
