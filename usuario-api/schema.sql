drop table if exists cliente;
create table cliente (
    id bigint auto_increment primary key,
    cpf varchar(14),
    nome varchar(255),
    email varchar(255),
    telefone varchar(20),
    logradouro varchar(100),
    bairro varchar(100),
    cidade varchar(50),
    uf char(2),
    cep char(9)
);


drop table if exists categoria_produto;
drop table if exists categoria;

create table categoria (
id bigint auto_increment primary key,
nome varchar(100)
);


drop table if exists produto;

create table produto (
    id bigint auto_increment primary key,
    nome varchar(100),
    preco decimal(10,2),
    descricao varchar(1000)
);

create table categoria_produto (
    id bigint auto_increment primary key,
    categoria_id bigint not null,
    produto_id bigint not null,

    foreign key (categoria_id) references categoria (id),
    foreign key (produto_id) references produto (id)
)