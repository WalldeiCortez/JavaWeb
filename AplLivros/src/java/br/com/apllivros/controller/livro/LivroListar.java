package br.com.apllivros.controller.livro;

import br.com.apllivros.dao.GenericDAO;
import br.com.apllivros.dao.LivroDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LivroListar", urlPatterns = {"/LivroListar"})
public class LivroListar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            GenericDAO dao = new LivroDAO();
            request.setAttribute("livros", dao.listar());
            request.getRequestDispatcher("/cadastros/livro/livro.jsp")
                   .forward(request, response);
        } catch (Exception ex) {
            System.out.println("Problemas no Servlet ao Listar Livros! Erro: " + ex.getMessage());
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
