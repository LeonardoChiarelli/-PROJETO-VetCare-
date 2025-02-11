create table abrigos(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    cnpj varchar(14) not null unique,
    telefone varchar(18) not null,
    email varchar(100) not null unique,
    perfil_id bigint not null default 1,

    primary key(id),
    constraint fk_abrigos_perfil_id foreign key(perfil_id) references perfis(id)
);