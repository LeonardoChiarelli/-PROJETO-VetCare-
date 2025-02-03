create table pagamentos(
    id bigint not null auto_increment,
    valor decimal(19,2) not null,
    titular_cartao varchar(100) not null,
    numero_cartao varchar(19) not null,
    expiracao_cartao varchar(6) not null,
    codigo_de_seguranca varchar(3) not null,
    status_pagamento varchar(50) not null,
    data_pagamento datetime not null,
    consulta_id bigint not null,
    metodo_de_pagamento_id bigint not null,

    primary key(id),
    constraint fk_pagamentos_consulta_id foreign key(consulta_id) references consultas(id),
    constraint fk_pagamentos_metodo_de_pagamento_id foreign key(metodo_de_pagamento_id) references metodos_de_pagamento(id)
);