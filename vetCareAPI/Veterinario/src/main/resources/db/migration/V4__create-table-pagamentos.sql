create table pagamentos(
    id bigint not null auto_increment,
    valor decimal not null,
    metodo varchar(100),
    status varchar(50),
    data date not null,
    consulta_id bigint not null,

    primary key(id),
    constraint fk_pagamentos_consulta_id foreign key(consulta_id) references consultas(id)
);