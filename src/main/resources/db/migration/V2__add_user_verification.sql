create table verification_tokens
(
    id int not null auto_increment,
    user_id int not null,
    token uuid not null,
    expires timestamp not null,
    created_date timestamp not null,
    created_by int not null,
    last_modified_date timestamp not null,
    last_modified_by int not null,
    primary key (id),
    foreign key (user_id) references users (id)
)