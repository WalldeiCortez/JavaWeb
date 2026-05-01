package br.com.apllivros.controller.livro;

import br.com.apllivros.dao.GenericDAO;
import br.com.apllivros.dao.LivroDAO;
import br.com.apllivros.model.Livro;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LivroCarregar", urlPatterns = {"/LivroCarregar"})
public class LivroCarregar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            GenericDAO dao = new LivroDAO();
            Livro oLivro = (Livro) dao.carregar(id);
            request.setAttribute("livro", oLivro);
            request.getRequestDispatcher("/cadastros/livro/livroCadastrar.jsp")
                   .forward(request, response);
        } catch (Exception ex) {
            System.out.println("Problema na Servlet LivroCarregar! Erro: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
