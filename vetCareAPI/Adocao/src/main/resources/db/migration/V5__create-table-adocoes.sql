create table adocoes(

    id bigint not null auto_increment,
    data_hora datetime not null,
    adotante_id bigint not null,
    pet_id bigint not null,
    abrigo_id bigint not null,
    status_adocao varchar(50) not null,
    justificativa varchar(255),

    primary key(id),
    constraint fk_adoces_adotante_id foreign key(adotante_id) references tutores(id),
    constraint fk_adoces_pet_id foreign key(pet_id) references pets(id),
    constraint fk_adoces_abrigo_id foreign key(abrigo_id) references abrigos(id)
);