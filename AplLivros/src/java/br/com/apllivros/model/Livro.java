package br.com.apllivros.model;

import java.sql.Date;

public class Livro {

    private int    id;
    private String nomeLivro;
    private String isbn;
    private String autor;
    private Date   dataPublicacao;
    private double valorLivro;

    public Livro() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNomeLivro() { return nomeLivro; }
    public void setNomeLivro(String nomeLivro) { this.nomeLivro = nomeLivro; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public Date getDataPublicacao() { return dataPublicacao; }
    public void setDataPublicacao(Date dataPublicacao) { this.dataPublicacao = dataPublicacao; }

    public double getValorLivro() { return valorLivro; }
    public void setValorLivro(double valorLivro) { this.valorLivro = valorLivro; }
}
