create table users
(
    id int not null auto_increment,
    first_name varchar(100) not null,
    last_name varchar(100) not null,
    email varchar(254) not null,
    password varchar(60) not null,
    account_expiration timestamp null,
    password_expiration timestamp null,
    verified bit not null,
    active bit not null,
    created_date timestamp not null,
    created_by int not null,
    last_modified_date timestamp not null,
    last_modified_by int not null,
    primary key (id)
);

insert into users
(
    id,
    first_name,
    last_name,
    email,
    password,
    account_expiration,
    password_expiration,
    verified,
    active,
    created_date,
    created_by,
    last_modified_date,
    last_modified_by
)
values
(
    1,
    'System',
    'Administrator',
    'system.administrator@example.com',
    '$2a$12$7r9wZfRQUJd5QZqpjlskZ.0ptavAaICMsF9afK8Aja9H3LhOVnyuK',
    dateadd(day, 90, current_timestamp),
    dateadd(day, 60, current_timestamp),
    1,
    1,
    current_timestamp,
    1,
    current_timestamp,
    1);

create table roles
(
    id int not null auto_increment,
    name varchar(100) not null,
    description varchar(max) not null,
    created_date timestamp not null,
    created_by int not null,
    last_modified_date timestamp not null,
    last_modified_by int not null,
    primary key (id)
);

insert into roles (id, name, description, created_date, created_by, last_modified_date, last_modified_by)
values (1, 'ROLE_ADMIN', 'Has access to the administrative section of the application and manages all users.', current_timestamp, 1, current_timestamp, 1);

insert into roles (id, name, description, created_date, created_by, last_modified_date, last_modified_by)
values (2, 'ROLE_USER', 'Basic account that allows a user to log their daily exercise activity.', current_timestamp, 1, current_timestamp, 1);

create table user_roles
(
    id int not null auto_increment,
    user_id int not null,
    role_id int not null,
    primary key (id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

insert into user_roles (id, user_id, role_id)
values (1, 1, 1);