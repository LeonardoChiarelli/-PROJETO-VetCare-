create table veterinarios(
    id bigint not null auto_increment,
    nome varchar(250) not null,
    crm varchar(6) not null unique,
    email varchar(100) not null unique,
    telefone varchar(13) not null,
    especialidade varchar(100),
    ativo tinyint(2) not null default 0,
    perfil_id bigint not null default 2,

    primary key(id),
    constraint fk_veterinarios_perfil_id foreign key(perfil_id) references perfis(id)
);