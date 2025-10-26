create table user
(
    user_id int auto_increment
        primary key,
    name    varchar(32) not null
);

create table task_list
(
    task_list_id int auto_increment
        primary key,
    fk_user_id   int         not null,
    name         varchar(32) not null,
    constraint task_list_user_user_id_fk
        foreign key (fk_user_id) references user (user_id)
);

create table task
(
    task_id      int auto_increment
        primary key,
    name         varchar(255) null,
    description  varchar(255) not null,
    task_list_id int          not null,
    constraint task_task_list_task_list_id_fk
        foreign key (task_list_id) references task_list (task_list_id)
);

