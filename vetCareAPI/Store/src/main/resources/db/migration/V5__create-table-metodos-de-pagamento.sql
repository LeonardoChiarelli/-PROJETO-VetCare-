create table metodos_de_pagamento(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    ativo tinyint(1) not null default 1,

    primary key(id)
)