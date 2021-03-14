CREATE TABLE ordem_servico (

	id bigint not null identity primary key,
	cliente_id bigint not null,
	descricao varchar(255) not null,
	preco decimal(10,2) not null,
	status varchar(20) not null,
	data_abertura datetime not null,
	data_finalizacao datetime,

	constraint fk_cliente foreign key (cliente_id) references Cliente (id)
)