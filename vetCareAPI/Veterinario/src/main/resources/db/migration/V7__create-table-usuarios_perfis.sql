create table usuarios_perfis(
    usuario_id bigint not null,
    perfil_id bigint not null,

    primary key(usuario_id, perfil_id),

    constraint usuarios_perfis_fk_usuario foreign key(usuario_id) references usuarios(id),
    constraint usuarios_perfis_fk_perfil foreign key(perfil_id) references perfis(id)
);

insert into usuarios_perfis values(1,1);
insert into usuarios_perfis values(2,2);
insert into usuarios_perfis values(3,3);