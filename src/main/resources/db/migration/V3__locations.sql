create table objects
(
    id        bigint NOT NULL AUTO_INCREMENT,
    user_id   bigint not null,
    address   varchar(500),
    published int    not null default 0,
    primary key (id)
);

create table locations
(
    id         bigint NOT NULL AUTO_INCREMENT,
    country_id bigint not null,
    locality   varchar(80),
    primary key (id)

);

insert into locations(id, country_id, locality)
values (1, 1, 'Санкт-Петербург'),
       (2, 2, 'Москва'),
       (3, 3, 'Новгород'),
       (4, 5, 'Новосибирск');

create table addresses
(
    id       bigint       NOT NULL AUTO_INCREMENT,
    obj_id   bigint       not null,
    coutry   bigint       not null,
    locality bigint       not null,
    address  varchar(255) not null,
    primary key (id)
);

create table coordinates
(
    id          bigint NOT NULL AUTO_INCREMENT,
    obj_id      bigint not null,
    coordinates varchar(255),
    primary key (id),
    foreign key (obj_id) references objects (id)
);

create table countries
(
    id      bigint       NOT NULL AUTO_INCREMENT,
    country varchar(255) not null,
    primary key (id)
);

insert into countries(id, country)
values (1, 'Россия');

create table details
(
    id          bigint NOT NULL AUTO_INCREMENT,
    obj_id      bigint,
    title       varchar(255),
    floor       int,
    rooms       int    not null,
    balcony     varchar(255),
    area        float,
    price       int    not null default 0,
    capacity    int    not null default 1,
    count_rooms int    not null,
    internet    varchar(255),
    service     varchar(255),
    comfort     varchar(255),
    parking     varchar(255),
    text_obj    text,
    primary key (id)
);

