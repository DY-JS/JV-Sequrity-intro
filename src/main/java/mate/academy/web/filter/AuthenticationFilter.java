package mate.academy.web.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

//не позволит перейти на индекс. Если не пройдёт аутентификация перекинет на стр. логина
public class AuthenticationFilter implements Filter {
    private Set<String> allowedUrls = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        allowedUrls.add("/login");
        allowedUrls.add("/registration");
        allowedUrls.add("help");
        allowedUrls.add("/contacts");
    }  //это пути, доступные неаутентификованным юзерам

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;//кастировали servletRequest и присвоили req
        HttpServletResponse resp = (HttpServletResponse) servletResponse;//кастировали servletResponse и присвоили resp
        HttpSession session = req.getSession();

        Long userId = (Long) session.getAttribute("user_id"); //получили атрибут user_id, кот был создан в LoginController
        //if (userId == null && req.getServletPath().equals("/login")) { //если в сессию не записан этот атрибут user_id(нет юзера)
        if (userId == null && allowedUrls.contains(req.getServletPath())) {
            filterChain.doFilter(req, resp); //req.getServletPath() - это путь к странице назначения из запроса
            return;
        }
        if (userId == null) { //если в сессию не записан этот атрибут user_id(нет юзера)
            resp.sendRedirect("/login");
            return;
        }

        filterChain.doFilter(req, resp);
    }
}
