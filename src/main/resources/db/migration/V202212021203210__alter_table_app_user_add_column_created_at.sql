alter table app_user
    add created_at timestamp default NOW() not null;
