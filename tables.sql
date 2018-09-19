create table usuario(
    id bigint AUTO_INCREMENT,
    nome varchar(255),
    email varchar(255),
    telefone varchar(255),
    senha varchar(255),
    PRIMARY KEY (id)
);

create table bebe(
    id bigint auto_increment,
    nome varchar(255),
    dataNascimento datetime,
    peso float,
    usuarioId bigint,
    PRIMARY KEY (id),
    FOREIGN KEY (usuarioId) REFERENCES usuario(id)
);

create table marca(
    id bigint auto_increment,
    nome varchar(255),
    PRIMARY KEY (id)
);

create table usuario_marca(
    usuarioId bigint,
    marcaId bigint,
    FOREIGN KEY (usuarioId) REFERENCES usuario(id),
    FOREIGN KEY (marcaId) REFERENCES marca(id)
);

create table modelo(
    id bigint auto_increment,
    nome varchar(255),
    imageLink varchar(500),
    marcaId bigint,
    PRIMARY KEY (id),
    FOREIGN KEY (marcaId) REFERENCES marca(id)
);

create table tamanho(
    id bigint auto_increment,
    nome varchar(255),
    PRIMARY KEY (id)
);

create table usuario_tamanho(
    usuarioId bigint,
    tamanhoId bigint,
    FOREIGN KEY (usuarioId) REFERENCES usuario(id),
    FOREIGN KEY (tamanhoId) REFERENCES tamanho(id)
);

create table loja(
    id bigint auto_increment,
    nome varchar(255),
    imageLink varchar(500),
    PRIMARY KEY (id)
);

create table usuario_loja(
    usuarioId bigint,
    lojaId bigint,
    FOREIGN KEY (usuarioId) REFERENCES usuario(id),
    FOREIGN KEY (lojaId) REFERENCES loja(id)
);

create table promocao(
    id bigint auto_increment,
    valorUnidade float,
    valorPacote float,
    promoLink varchar(500),
    modeloId bigint,
    tamanhoId bigint,
    lojaId bigint,
    lastUpdate datetime,
    PRIMARY KEY (id),
    FOREIGN KEY (modeloId) REFERENCES modelo(id),
    FOREIGN KEY (tamanhoId) REFERENCES tamanho(id),
    FOREIGN KEY (lojaId) REFERENCES loja(id)
);

insert into promocao (valorUnidade, valorPacote, promoLink) values (1.99, 20.99, 'https://google.com');

INSERT INTO usuario (email, nome, senha, telefone, cpf) values ('teste2@teste.com', 'Joao', '123', '229999-0000', '223.123.123-33');
INSERT INTO usuario (email, nome, senha, telefone, cpf) values ('teste3@teste.com', 'Janete', '123', '339999-0000', '333.123.123-33');
INSERT INTO usuario (email, nome, senha, telefone, cpf) values ('teste4@teste.com', 'Carlos', '123', '449999-0000', '444.123.123-33');
INSERT INTO usuario (email, nome, senha, telefone, cpf) values ('teste5@teste.com', 'Thiago', '123', '559999-0000', '555.123.123-33');

UPDATE loja set nome = 'Americanas', image_link = 'https://americanas.com' where id = 2;

INSERT INTO loja (nome, image_link) values ('Casas Bahia', 'casasbahia.com.br');
INSERT INTO loja (nome, image_link) values ('Amazon', 'amazon.com');
INSERT INTO loja (nome, image_link) values ('Cultura', 'cultura.com.br');

select * from usuario u join usuario_loja ul where u.id = ul.usuario_id and loja_id in (5, 4);

select DISTINCT u.id, u.email, u.nome from usuario u join usuario_loja ul where u.id = ul.usuario_id and loja_id in (5, 4);

INSERT into usuario_loja values (5, 4);
INSERT into usuario_loja values (1, 5);
INSERT into usuario_loja values (5, 1);
INSERT into usuario_loja values (5, 6);

insert into tamanho (nome) values ('M');
insert into tamanho (nome) values ('G');
insert into tamanho (nome) values ('PP');

select * from usuario u join usuario_tamanho ul where u.id = ul.usuario_id and tamanho_id in (5, 4);

select DISTINCT u.id, u.email, u.nome from usuario u join usuario_tamanho ut where u.id = ut.usuario_id and tamanho_id in (1, 3);

INSERT into usuario_tamanho values (2, 1);
INSERT into usuario_tamanho values (3, 3);
INSERT into usuario_tamanho values (3, 4);
INSERT into usuario_tamanho values (4, 2);
INSERT into usuario_tamanho values (5, 2);
INSERT into usuario_tamanho values (5, 5);

INSERT into modelo (image_link, nome, marca_id) values ('testeimagem.com.br', 'modelo teste', 1);

INSERT into promocao (promo_link, valor_pacote, valor_unidade, loja_id, modelo_id, tamanho_id) VALUES ('teste.com.br', 90.9, 9.9, 4, 2, 1);
INSERT into promocao (promo_link, valor_pacote, valor_unidade, loja_id, modelo_id, tamanho_id) VALUES ('teste2.com.br', 88.3, 3.7, 5, 3, 3);




select DISTINCT u.id, u.email, u.nome 
from usuario u join usuario_loja ul join usuario_tamanho ut join usuario_marca um
where u.id = ul.usuario_id and u.id = ut.usuario_id and u.id = um.usuario_id
and ul.loja_id in (5, 4) and ut.tamanho_id in (1, 3) and um.marca_id in (2);


select DISTINCT u.id, u.email, u.nome 
from usuario u join usuario_loja ul join usuario_tamanho ut join usuario_marca um
where u.id = ul.usuario_id and u.id = ut.usuario_id and u.id = um.usuario_id
and ul.loja_id in (1, 4, 5) and ut.tamanho_id in (1 ,3) and um.marca_id in (1, 2, 3);
