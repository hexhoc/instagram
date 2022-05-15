drop table if exists users;
create table users
(
    id           bigserial primary key,
    name         varchar(255) unique not null,
    username     varchar(255)        not null,
    lastname     varchar(255)        not null default '',
    email        varchar(150) unique not null,
    bio          text,
    password     varchar(3000)       not null,
    created_date timestamp,
    updated_date timestamp,
    version      int
);

drop table if exists posts;
create table posts
(
    id           bigserial primary key,
    title        varchar(255) not null,
    caption      varchar(255) not null,
    location     varchar(255) not null,
    likes        int4         not null default 0,
    user_id      int8         not null,
    created_date timestamp,
    updated_date timestamp,
    version      int,
    constraint fk_user_id foreign key (user_id) references users (id) ON DELETE SET NULL ON UPDATE NO ACTION
);

drop table if exists image_models;
create table image_models
(
    id          bigserial primary key,
    name        varchar(255) not null,
    image_bytes bytea,
    user_id     int8         not null,
    post_id     int8         not null
);

drop table if exists comments;
create table comments
(
    id           bigserial primary key,
    post_id      int8         not null,
    username     varchar(255) not null,
    user_id      int8         not null,
    message      varchar(500) not null,
    created_date timestamp,
    updated_date timestamp,
    version      int4,
    constraint fk_post_id foreign key (post_id) references posts (id) ON DELETE SET NULL ON UPDATE NO ACTION
);

drop table if exists user_role;
create table user_role
(
    user_id int8 not null
        constraint fk_user_id references users,
    role    int4
);

-- alter table user_role
--     owner to postgres;

drop table if exists post_liked_users;
create table post_liked_users
(
    post_id     int8 not null
        constraint fk_post_id references posts,
    liked_users varchar(255)
);

