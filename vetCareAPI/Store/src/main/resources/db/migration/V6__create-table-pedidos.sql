create table pedidos(

    id bigint not null auto_increment,
    data_pedido date not null,
    usuario_id bigint not null,

    primary key(id),
    constraint fk_pedidos_usuario_id foreign key(usuario_id) references usuarios(id)
);