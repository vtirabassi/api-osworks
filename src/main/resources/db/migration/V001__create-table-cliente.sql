CREATE TABLE Cliente (
	id bigint not null identity primary key,
	nome varchar(255) not null,
	email varchar(255) not null,
	fone varchar(255) not null
)