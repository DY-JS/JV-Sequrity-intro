package mate.academy.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mate.academy.exception.AuthenticationException;
import mate.academy.model.User;
import mate.academy.service.AuthenticationService;
import mate.academy.service.AuthenticationServiceImpl;


@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {
    private AuthenticationService authenticationService = new AuthenticationServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            User user = authenticationService.login(username, password);//получили залогиненного юзера
            HttpSession session = req.getSession();//объект сессии из объекта запроса
            session.setAttribute("user_id", user.getId());//записываем в сессию
            resp.sendRedirect(req.getContextPath() + "/index");
        } catch (AuthenticationException e) {//если не получили залогиненного юзера -> на login.jsp
            req.setAttribute("errorMsg", e.getMessage()); //Username or password is incorrect
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        }
    }
}
