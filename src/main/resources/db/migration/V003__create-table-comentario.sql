CREATE TABLE Comentario (

	id bigint not null identity primary key,
	ordem_servico_id bigint not null,
	descricao varchar(255) not null,
	data_envio datetime not null,

	constraint fk_ordem_servico foreign key (ordem_servico_id) references ordem_servico (id)
)
