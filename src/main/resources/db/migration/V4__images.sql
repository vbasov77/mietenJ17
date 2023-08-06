create table images
(
    id bigint NOT NULL AUTO_INCREMENT,
    obj_id bigint,
    path varchar(255),
    primary key (id)
);