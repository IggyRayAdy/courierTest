create sequence hibernate_sequence start 6 increment 1;

create table ord
(
    id         int8         not null,
    date       date,
    infomation varchar(255) not null,
    active     boolean,
    done       boolean,
    primary key (id)
);

create table task
(
    id       int8 not null,
    date     date,
    time     time,
    finish   boolean,
    order_id int8,
    primary key (id)
);



alter table if exists task
    add constraint FKe3yhbl84dee9kt74j1a46ba3n
        foreign key (order_id) references ord