package br.com.apllivros.controller.livro;

import br.com.apllivros.dao.LivroDAO;
import br.com.apllivros.model.Livro;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LivroCadastrar", urlPatterns = {"/LivroCadastrar"})
public class LivroCadastrar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            LivroDAO dao = new LivroDAO();

            int    id       = Integer.parseInt(request.getParameter("id"));
            String nome     = request.getParameter("nomeLivro");
            String isbn     = request.getParameter("isbn");
            String autor    = request.getParameter("autor");
            java.sql.Date dataPublicacao = java.sql.Date.valueOf(request.getParameter("dataPublicacao"));

            String valorStr = request.getParameter("valorLivro");
            valorStr = valorStr.replace("R$", "")
                               .replace(".", "")
                               .replace(",", ".")
                               .trim();
            double valorLivro = Double.parseDouble(valorStr);

            if (nome.isEmpty() || nome.isBlank() || isbn.isEmpty() || autor.isEmpty() || valorLivro <= 0) {
                response.getWriter().write("5");
            } else {
                Livro oLivro = new Livro();
                oLivro.setId(id);
                oLivro.setNomeLivro(nome);
                oLivro.setIsbn(isbn);
                oLivro.setAutor(autor);
                oLivro.setDataPublicacao(dataPublicacao);
                oLivro.setValorLivro(valorLivro);

                if (dao.cadastrar(oLivro)) {
                    response.getWriter().write("1");
                } else {
                    response.getWriter().write("0");
                }
            }
        } catch (Exception ex) {
            System.out.println("Problemas no Servlet ao cadastrar Livro! Erro: " + ex.getMessage());
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
