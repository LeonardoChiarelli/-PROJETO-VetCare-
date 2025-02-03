create table tutores(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    cpf varchar(11) not null unique,
    telefone varchar(20) not null,
    email varchar(100) not null unique,
    pet_id bigint not null,
    ativo tinyint not null default 0,
    perfil_id bigint not null default 3,

    primary key(id),
    constraint fk_tutores_pet_id foreign key(pet_id) references pets(id),
    constraint fk_tutores_perfil_id foreign key(perfil_id) references perfis(id)
);