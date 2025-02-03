create table pets(
    id bigint not null auto_increment,
    nome varchar(250) not null,
    especie varchar(100) not null,
    raca varchar(100) not null,
    data_aniversario date not null,
    veterinario_id bigint not null,
    tutor_id bigint not null,

    primary key(id),
    constraint fk_pets_veterinario_id foreign key(veterinario_id) references veterinarios(id),
    constraint fk_pets_tutor_id foreign key(tutor_id) references tutores(id)
);