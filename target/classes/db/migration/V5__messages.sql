create table messages
(
    id           bigint NOT NULL AUTO_INCREMENT,
    from_user_id bigint not null,
    to_user_id   bigint not null,
    obj_id       bigint not null,
    body         text,
    status       int    not null default 0,
    created_at     datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    primary key (id)

)