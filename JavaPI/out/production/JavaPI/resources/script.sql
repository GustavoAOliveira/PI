create database projeto;

create table funcionarios(
id int not null auto_increment primary key,
nome varchar(40),
rgf varchar(9),
cpf varchar(11),
rg varchar(9)
)

create table fornecedores(
id int not null auto_increment primary key,
nome varchar(40),
ie varchar(13),
endereco varchar(90),
cnpj varchar(15),
cep varchar(10)
)