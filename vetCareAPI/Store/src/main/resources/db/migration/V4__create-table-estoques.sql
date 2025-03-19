create table estoques(
    id bigint not null auto_increment,
    quantidade int not null,
    versao int not null default 0,
    produto_id bigint not null,

    primary key(id),
    constraint fk_estoques_produto_id foreign key(produto_id) references produtos(id)
);