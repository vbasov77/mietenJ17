create table messages
(
    id           bigint NOT NULL AUTO_INCREMENT,
    from_user_id bigint not null,
    to_user_id   bigint not null,
    obj_id       bigint not null,
    body         text,
    status       int    not null default 0,
    creat_at     datetime,
    primary key (id)

)