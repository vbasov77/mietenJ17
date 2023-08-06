create table rules
(
    id        bigint NOT NULL AUTO_INCREMENT,
    obj_id    bigint,
    children  varchar(10),
    animals   varchar(10),
    smoking   varchar(10),
    party     varchar(10),
    documents varchar(10),
    monthly   varchar(10),
    primary key (id)


);