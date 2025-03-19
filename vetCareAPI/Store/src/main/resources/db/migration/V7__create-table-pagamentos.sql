create table pagamentos(

    id bigint not null auto_increment,
    valor decimal(19,2) not null,
    titular_cartao varchar(150) not null,
    numero_cartao varchar(18) not null,
    expiracao_cartao varchar(5) not null,
    codigo_seguranca varchar(3) not null,
    status varchar(50) not null,
    data datetime not null,
    pedido_id bigint not null,
    metodo_de_pagamento_id bigint not null,

    primary key(id),
    constraint fk_pagamentos_pedido_id foreign key(pedido_id) references pedidos(id),
    constraint fk_pagamentos_metodo_de_pagamento_id foreign key(metodo_de_pagamento_id) references metodos_de_pagamento(id)
);