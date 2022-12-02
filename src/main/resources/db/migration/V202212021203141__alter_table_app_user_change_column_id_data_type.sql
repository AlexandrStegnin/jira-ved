alter table app_user
    alter column id drop default;

drop sequence app_user_user_id_seq;
