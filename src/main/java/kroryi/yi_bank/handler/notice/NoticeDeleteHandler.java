package kroryi.yi_bank.handler.notice;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kroryi.yi_bank.dao.NoticeDao;
import kroryi.yi_bank.dao.impl.NoticeDaoImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/main/deleteNotice.do")
public class NoticeDeleteHandler extends HttpServlet {

    private NoticeDao noticeDao = new NoticeDaoImpl();
    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            process(req, res);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, SQLException {
        // Retrieve notice number from request parameters
        String noticeNoStr = req.getParameter("noticeNo");

        // Check if the noticeNo parameter is not null and is a valid integer
        if (noticeNoStr != null && noticeNoStr.matches("\\d+")) {
            int noticeNo = Integer.parseInt(noticeNoStr);

            // Call the deleteNotice method with the retrieved notice number
            int result = noticeDao.deleteNotice(noticeNo);

            // Results determine the response
            res.setContentType("application/json");
            res.setCharacterEncoding("utf-8");
            PrintWriter out = res.getWriter();

            if (result > 0) {
                out.print(gson.toJson("success"));
            } else {
                out.print(gson.toJson("failure"));
            }

            out.flush();
        } else {
            // Handle the case where noticeNo parameter is missing or not a valid integer
            res.setContentType("application/json");
            res.setCharacterEncoding("utf-8");
            PrintWriter out = res.getWriter();
            out.print(gson.toJson("invalid noticeNo parameter"));
            out.flush();
        }
    }

}
