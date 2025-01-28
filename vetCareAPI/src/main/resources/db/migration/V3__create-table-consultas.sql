create table consultas(
    id bigint not null auto_increment,
    pet_id bigint not null,
    veterinario_id bigint not null
    status varchar(100) not null,
    detalhes varchar(250),
    dataHora datetime not null,

    primary key(id),
    constraint fk_consultas_pet_id foreign key(pet_id) references pets(id),
    constraint fk_consultas_veterinario_id foreign key(veterinario_id) references veterinarios(id)
);