create table usuarios(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    senha varchar(255) not null,
    id_perfil bigint not null,

    primary key(id),
    constraint fk_usuarios_id_perfil foreign key(id_perfil) references perfis(id)
);

insert into usuarios values(1, "ADMIN", "admin@gmail.com", "123456", 1);
insert into usuarios values(2, "COMPRADOR", "comprador@gmail.com", "123456", 2);