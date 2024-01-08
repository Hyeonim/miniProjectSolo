package kroryi.yi_bank.handler.main;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/main/main.do")
public class MainHandler extends HttpServlet {

    public void init(){
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        process(req, res); //
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        process(req, res);
    }

    public void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.setAttribute("page", "home.jsp");
        // 디스패치
        RequestDispatcher re = req.getRequestDispatcher("/main_template.jsp");
        re.forward(req, res);
    }
}
