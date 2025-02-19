create table tutores(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    cpf varchar(14) not null unique,
    telefone varchar(18) not null,
    email varchar(100) not null,
    logradouro varchar(100) not null,
    bairro varchar(100) not null,
    cep varchar(9) not null,
    complemento varchar(100),
    numero varchar(20),
    uf char(2) not null,
    cidade varchar(100) not null,
    perfil_id bigint not null default 2,

    primary key(id),
    constraint fk_tutores_perfil_id foreign key(perfil_id) references perfis(id)
);