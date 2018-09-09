create table usuario(
    id bigint auto_increment,
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
    PRIMARY KEY (id),
    FOREIGN KEY (modeloId) REFERENCES modelo(id),
    FOREIGN KEY (tamanhoId) REFERENCES tamanho(id),
    FOREIGN KEY (lojaId) REFERENCES loja(id)
);

insert into promocao (valorUnidade, valorPacote, promoLink) values (1.99, 20.99, 'https://google.com');
