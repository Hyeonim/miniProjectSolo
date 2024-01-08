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
import kroryi.yi_bank.service.NoticeService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@WebServlet("/main/getNotice.do")
public class NoticeGetHandler extends HttpServlet {

    private NoticeService noticeService = new NoticeService();
    private Gson gson = new Gson();
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            process(req, res);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            process(req, res);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, SQLException {

        BufferedReader reader = req.getReader();
        StringBuilder jsonInput = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            jsonInput.append(line);
        }

        String noticeNoString = jsonInput.toString().replaceAll("[^0-9]", "");
        int noticeNo = Integer.parseInt(noticeNoString);

        Notice list = noticeService.showNoticeByNo(noticeNo);

//        System.out.println(list);

        String noticeListJson = this.gson.toJson(list);
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        PrintWriter out = res.getWriter();
        out.print(noticeListJson);
        out.flush();

    }

}