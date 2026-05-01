package br.com.apllivros.filter;

import br.com.apllivros.utils.SingleConnection;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class FilterAutenticacao implements Filter {

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
