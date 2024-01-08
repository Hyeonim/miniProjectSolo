package kroryi.yi_bank.handler.notice;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kroryi.yi_bank.dao.NoticeDao;
import kroryi.yi_bank.dao.impl.NoticeDaoImpl;
import kroryi.yi_bank.dto.Notice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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


        BufferedReader reader = req.getReader();
        StringBuilder jsonInput = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            jsonInput.append(line);
        }

//        System.out.println(jsonInput);
        String noticeNoString = jsonInput.toString().replaceAll("[^0-9]", "");
        int noticeNo = Integer.parseInt(noticeNoString);

        System.out.println(noticeNo);

            // 가져온 공지 번호로 deleteNotice 메서드 호출
            int result = noticeDao.deleteNotice(noticeNo);

            // 결과에 따라 응답
            res.setContentType("application/json");
            res.setCharacterEncoding("utf-8");
            PrintWriter out = res.getWriter();

            if (result > 0) {
                out.print(gson.toJson("success"));
            } else {
                out.print(gson.toJson("failure"));
            }

            out.flush();
        }

}
