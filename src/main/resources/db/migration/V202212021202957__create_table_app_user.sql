create table app_user
(
    user_id    bigserial
        constraint app_user_pk
            primary key,
    first_name varchar,
    last_name  varchar,
    phone      varchar
);

create unique index app_user_user_id_uindex
    on app_user (user_id);
