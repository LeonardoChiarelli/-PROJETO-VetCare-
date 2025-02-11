create table pets(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    data_nascimento date not null,
    idade int,
    especie varchar(50) not null,
    porte varchar(20) not null,
    peso double(5,2) not null,
    cor varchar(50) not null,
    descricao varchar(255) not null,
    status varchar(20) not null,
    abrigo_id bigint not null,

    primary key(id),
    constraint fk_pets_abrigo_id foreign key(abrigo_id) references abrigos(id)
);