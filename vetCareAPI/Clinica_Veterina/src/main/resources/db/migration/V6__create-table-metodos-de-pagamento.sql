create table metodos_de_pagamento(
    id bigint not null auto_increment,
    metodo varchar(100) not null unique,

    primary key(id)
);

insert into metodos_de_pagamento values(1, 'CARTAO DE CRÉDITO');
insert into metodos_de_pagamento values(2, 'CARTAO DE DÉBITO');
insert into metodos_de_pagamento values(3, 'BOLETO');
insert into metodos_de_pagamento values(4, 'PIX');