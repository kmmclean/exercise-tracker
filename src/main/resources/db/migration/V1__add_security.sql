create table users
(
    id int not null auto_increment,
    first_name varchar(100) not null,
    last_name varchar(100) not null,
    email varchar(254) not null,
    password varchar(60) not null,
    primary key (id)
);

create table roles
(
    id int not null auto_increment,
    role varchar(100) not null,
    description varchar(max) not null,
    primary key (id)
);

insert into roles (role, description)
values ('ROLE_ADMIN', 'Has access to the administrative section of the application and manages all users.');

insert into roles (role, description)
values ('ROLE_USER', 'Basic account that allows a user to log their daily exercise activity.');

create table user_roles
(
    id int not null auto_increment,
    user_id int not null,
    role_id int not null,
    primary key (id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
)
