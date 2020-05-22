package site.licsber.notice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import site.licsber.notice.model.AppConfig;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@WebFilter(filterName = "TokenAuthFilter", urlPatterns = "/api/*")
public class TokenAuthFilter implements Filter {

    private final AppConfig config;

    public TokenAuthFilter(AppConfig config) {
        this.config = config;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader("TOKEN");
        if (token != null && token.equals(config.getToken())) {
            log.info(servletRequest.getRemoteAddr() + " pass token auth.");
            filterChain.doFilter(servletRequest, servletResponse);
        }
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setStatus(401);
        response.setHeader("WWW-Authenticate", "Use Token for auth.");
    }

}
