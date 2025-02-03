create table usuarios(
    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    senha varchar(100) not null,
    perfil_id bigint not null,

    primary key(id),
    constraint fk_usuarios_perfil_id foreign key(perfil_id) references perfis(id)
);

insert into usuarios values(1, 'Administrador', 'admin@gmail.com', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.', 1);
insert into usuarios values(2, 'Tutor', 'tutor@gmail.com', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.', 2);
insert into usuarios values(3, 'Veterinário', 'vet@gmail.com', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.', 3);