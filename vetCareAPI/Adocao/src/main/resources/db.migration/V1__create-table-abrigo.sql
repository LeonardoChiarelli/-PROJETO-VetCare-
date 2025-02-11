create table abrigos(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    cnpj varchar(14) not null unique,
    telefone varchar(18) not null,
    email varchar(100) not null unique,

    primary key(id)
);