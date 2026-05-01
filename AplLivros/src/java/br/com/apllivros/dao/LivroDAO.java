package br.com.apllivros.dao;

import br.com.apllivros.model.Livro;
import br.com.apllivros.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO implements GenericDAO {

    private Connection conexao;

    public LivroDAO() throws Exception {
        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Livro oLivro = (Livro) objeto;
        Boolean retorno = false;
        if (oLivro.getId() == 0) {
            retorno = this.inserir(oLivro);
        } else {
            retorno = this.alterar(oLivro);
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Livro oLivro = (Livro) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into livro (nomelivo, isbn, autor, datapublicacao, valorlivro) "
                   + "values (?,?,?,?,?)";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oLivro.getNomeLivro());
            stmt.setString(2, oLivro.getIsbn());
            stmt.setString(3, oLivro.getAutor());
            stmt.setDate(4, oLivro.getDataPublicacao());
            stmt.setDouble(5, oLivro.getValorLivro());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            try {
                System.out.println("Problemas ao cadastrar o Livro! Erro: " + ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Boolean alterar(Object objeto) {
        Livro oLivro = (Livro) objeto;
        PreparedStatement stmt = null;
        String sql = "update livro set nomelivo=?, isbn=?, autor=?, datapublicacao=?, valorlivro=? "
                   + "where id=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oLivro.getNomeLivro());
            stmt.setString(2, oLivro.getIsbn());
            stmt.setString(3, oLivro.getAutor());
            stmt.setDate(4, oLivro.getDataPublicacao());
            stmt.setDouble(5, oLivro.getValorLivro());
            stmt.setInt(6, oLivro.getId());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            try {
                System.out.println("Problemas ao alterar o Livro! Erro: " + ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Boolean excluir(int numero) {
        PreparedStatement stmt = null;
        String sql = "delete from livro where id=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, numero);
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            try {
                System.out.println("Problemas ao excluir o Livro! Erro: " + ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    public Livro buscarPorId(int id) throws Exception {
        Livro oLivro = null;
        String sql = "select * from livro where id=?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                oLivro = new Livro();
                oLivro.setId(rs.getInt("id"));
                oLivro.setNomeLivro(rs.getString("nomelivo"));
                oLivro.setIsbn(rs.getString("isbn"));
                oLivro.setAutor(rs.getString("autor"));
                oLivro.setDataPublicacao(rs.getDate("datapublicacao"));
                oLivro.setValorLivro(rs.getDouble("valorlivro"));
            }
        } catch (SQLException ex) {
            throw new Exception("Erro ao buscar livro por ID: " + ex.getMessage(), ex);
        }
        return oLivro;
    }

    @Override
    public Object carregar(int numero) {
        Livro oLivro = null;
        String sql = "select * from livro where id=?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, numero);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                oLivro = new Livro();
                oLivro.setId(rs.getInt("id"));
                oLivro.setNomeLivro(rs.getString("nomelivo"));
                oLivro.setIsbn(rs.getString("isbn"));
                oLivro.setAutor(rs.getString("autor"));
                oLivro.setDataPublicacao(rs.getDate("datapublicacao"));
                oLivro.setValorLivro(rs.getDouble("valorlivro"));
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao carregar livro: " + ex.getMessage());
            ex.printStackTrace();
        }
        return oLivro;
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        String sql = "select * from livro order by id";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Livro oLivro = new Livro();
                oLivro.setId(rs.getInt("id"));
                oLivro.setNomeLivro(rs.getString("nomelivo"));
                oLivro.setIsbn(rs.getString("isbn"));
                oLivro.setAutor(rs.getString("autor"));
                oLivro.setDataPublicacao(rs.getDate("datapublicacao"));
                oLivro.setValorLivro(rs.getDouble("valorlivro"));
                resultado.add(oLivro);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar livros: " + ex.getMessage());
            ex.printStackTrace();
        }
        return resultado;
    }
}
