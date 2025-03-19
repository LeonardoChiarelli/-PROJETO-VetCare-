create table perfis(

    id bigint not null auto_increment,
    nome varchar(50) not null unique,

    primary key(id)
);

insert into perfis values(1, "ROLE_ADMIN");
insert into perfis values(2, "ROLE_COMPRADOR");