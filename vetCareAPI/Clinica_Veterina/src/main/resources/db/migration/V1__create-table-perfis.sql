create table perfis(
    id bigint not null auto_increment,
    nome varchar(100) not null unique,

    primary key(id)
);

insert into perfis values(null, "ROLE_ADMIN");
insert into perfis values(null, "ROLE_TUTOR");
insert into perfis values(null, "ROLE_VET");