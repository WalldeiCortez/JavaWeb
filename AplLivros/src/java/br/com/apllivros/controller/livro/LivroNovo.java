package br.com.apllivros.controller.livro;

import br.com.apllivros.model.Livro;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LivroNovo", urlPatterns = {"/LivroNovo"})
public class LivroNovo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            Livro oLivro = new Livro();
            request.setAttribute("livro", oLivro);
            request.getRequestDispatcher("/cadastros/livro/livroCadastrar.jsp")
                   .forward(request, response);
        } catch (Exception ex) {
            System.out.println("Problema na Servlet LivroNovo! Erro: " + ex.getMessage());
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
