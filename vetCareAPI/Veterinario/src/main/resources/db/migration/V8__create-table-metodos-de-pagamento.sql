create table metodos_de_pagamento(
    id bigint not null auto_increment,
    metodo varchar(100) not null unique,

    primary key(id)
);