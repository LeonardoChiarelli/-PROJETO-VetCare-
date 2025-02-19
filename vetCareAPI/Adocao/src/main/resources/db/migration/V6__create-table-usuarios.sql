create table usuarios(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(150) not null unique,
    senha varchar(100) not null,
    perfil_id bigint not null default 0,

    primary key(id),
    constraint fk_usuarios_perfil_id foreign key(perfil_id) references perfis(id)
);

insert into usuarios values(1, 'ONG', 'ong@gmail.com', '123456', 1);
insert into usuarios values(2, 'Tutor', 'tutor@gmail.com', '123456', 2);