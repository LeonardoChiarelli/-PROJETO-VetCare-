create table itens_pedidos(

    id bigint not null auto_increment,
    pedido_id bigint not null,
    produto_id bigint not null,
    quantidade int not null,
    preco_unitario decimal(10,2) not null,

    primary key(id),
    constraint fk_itens_pedidos_pedido_id foreign key(pedido_id) references pedidos(id),
    constraint fk_itens_pedidos_produto_id foreign key(produto_id) references produtos(id)
);