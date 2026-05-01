-- ============================================================
-- Projeto: AplLivros
-- Banco de Dados: bdlivros
-- ============================================================

-- Executar no pgAdmin com o banco bdlivros selecionado:

create table livro (
    id            serial primary key,
    nomelivo      varchar(200) not null,
    isbn          varchar(20)  unique not null,
    autor         varchar(150) not null,
    datapublicacao date         not null,
    valorlivro    decimal(10,2) not null
);

insert into livro (nomelivo, isbn, autor, datapublicacao, valorlivro)
values ('Clean Code', '9780132350884', 'Robert C. Martin', '2008-08-11', 89.90);

insert into livro (nomelivo, isbn, autor, datapublicacao, valorlivro)
values ('O Programador Pragmatico', '9780201616224', 'Andrew Hunt', '1999-10-30', 75.50);

insert into livro (nomelivo, isbn, autor, datapublicacao, valorlivro)
values ('Design Patterns', '9780201633610', 'Erich Gamma', '1994-10-31', 110.00);

select * from livro;
