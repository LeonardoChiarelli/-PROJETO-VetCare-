create table adotantes(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    cpf varchar(14) not null unique,
    telefone varchar(18) not null,
    email varchar(100) not null,

    primary key(id)
);