create table account_building
(
    id uuid not null,
    building_address         varchar(255) not null,
    building_address_details varchar(255),
    building_name            varchar(255) not null,
    parking_space_count      integer      not null,
    primary key (id)
);


create table account_car
(
    id uuid not null,
    car_nickname varchar(255) not null,
    car_number   varchar(255) not null,
    car_type     varchar(255) not null,
    created_at   datetime(6)  not null,
    created_by   binary(255)  not null,
    deleted_at   datetime(6)  not null,
    deleted_by   binary(255)  not null,
    car_owner_id uuid not null,
    primary key (id)
);


create table account_living_user
(
    id uuid not null,
    created_at       datetime(6) not null,
    created_by       binary(255) not null,
    deleted_at       datetime(6),
    deleted_by       binary(255),
    living_user_role integer     not null,
    building_id_id uuid not null,
    user_id uuid not null,
    primary key (id)
);


create table account_user
(
    id uuid not null,
    user_id       varchar(255) not null,
    user_name     varchar(255) not null,
    user_nickname varchar(255) not null,
    user_password varchar(255) not null,
    user_state    varchar(255) not null,
    primary key (id)
);


create table account_user_mutable_roles
(
    account_user_id uuid not null,
    mutable_roles integer
);


create table parking_info
(
    id uuid not null,
    car_in_date  date        not null,
    car_in_time  time        not null,
    car_out_date date        not null,
    car_out_time time        not null,
    created_at   datetime(6) not null,
    building_id uuid not null,
    car_info uuid not null,
    created_by uuid not null,
    primary key (id)
);


-- FK Setting
alter table account_user
    add constraint UK_oi67uyc3ncs55upo8ktu6791d unique (user_id);

alter table account_car
    add constraint FKcpe4mgcccf3sfmyj0jv52u9md
        foreign key (car_owner_id)
            references account_user (id);

alter table account_living_user
    add constraint FKjyqmmymukwyw1a2tbhn8qexrj
        foreign key (building_id_id)
            references account_building (id);

alter table account_living_user
    add constraint FKrmb3q80j8q9cpqtffi4va6pxg
        foreign key (user_id)
            references account_user (id);

alter table account_user_mutable_roles
    add constraint FK485wy608lib9niuivc97ek71c
        foreign key (account_user_id)
            references account_user (id);

alter table parking_info
    add constraint FKr7dh8lymipe62x8m0ubkaayd
        foreign key (building_id)
            references account_building (id);

alter table parking_info
    add constraint FKopw56nuqoccygksp2c22ibhg9
        foreign key (car_info)
            references account_car (id);

alter table parking_info
    add constraint FKbnj6nv989f53uliwxlrjvojxw
        foreign key (created_by)
            references account_user (id);