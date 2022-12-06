package mate.academy.controller;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(urlPatterns = "/index")
public class IndexController extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Cookie cookie = new Cookie("city", "Kyiv");
        cookie.setMaxAge(10550);
        resp.addCookie(cookie);
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }
}