create table tutores(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    cpf varchar(11) not null unique,
    telefone varchar(20) not null,
    email varchar(100) not null unique,
    pet_id bigint not null,

    primary key(id),
    constraint fk_tutores_pet_id foreign key(pet_id) references pets(id)
);