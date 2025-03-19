create table produtos(
    id bigint not null auto_increment,
    nome varchar(100) not null,
    descricao varchar(255) not null,
    categoria varchar(50) not null,
    preco decimal(10,2) not null,
    ativo tinyint(1) not null default 1,

    primary key(id)
);