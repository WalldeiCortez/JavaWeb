package br.com.apllivros.controller.livro;

import br.com.apllivros.dao.LivroDAO;
import br.com.apllivros.model.Livro;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LivroAlterar", urlPatterns = {"/LivroAlterar"})
public class LivroAlterar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            LivroDAO dao = new LivroDAO();
            Livro livro = dao.buscarPorId(id);
            request.setAttribute("livro", livro);
            request.getRequestDispatcher("/livroAlterar.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("mensagem", "Erro ao buscar livro: " + ex.getMessage());
            request.getRequestDispatcher("/livroListar.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            Livro livro = new Livro();
            livro.setId(Integer.parseInt(request.getParameter("id")));
            livro.setNomeLivro(request.getParameter("nomeLivro"));
            livro.setIsbn(request.getParameter("isbn"));
            livro.setAutor(request.getParameter("autor"));
            livro.setDataPublicacao(Date.valueOf(request.getParameter("dataPublicacao")));
            livro.setValorLivro(Double.parseDouble(
                    request.getParameter("valorLivro").replace(",", ".")));
            LivroDAO dao = new LivroDAO();
            dao.alterar(livro);
            response.sendRedirect(request.getContextPath() + "/LivroListar");
        } catch (Exception ex) {
            request.setAttribute("mensagem", "Erro ao alterar livro: " + ex.getMessage());
            request.getRequestDispatcher("/livroAlterar.jsp").forward(request, response);
        }
    }
}
