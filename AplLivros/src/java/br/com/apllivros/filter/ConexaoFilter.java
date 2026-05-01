package br.com.apllivros.filter;

import br.com.apllivros.utils.SingleConnection;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class ConexaoFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            SingleConnection.conectar();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
