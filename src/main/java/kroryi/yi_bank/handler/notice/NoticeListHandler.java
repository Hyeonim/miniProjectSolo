package kroryi.yi_bank.handler.notice;

import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kroryi.yi_bank.dto.Notice;
import kroryi.yi_bank.service.NoticeService;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;


//NoticeListHandler 클래스: 이 클래스는 웹 어플리케이션에서 공지사항 리스트를 가져오기 위한 서블릿입니다.
//NoticeService를 이용하여 공지사항 리스트를 JSON 형태로 반환합니다.


@WebServlet("/main/notice.do")
public class NoticeListHandler extends HttpServlet {

    // DB ????
    private NoticeService noticeService = new NoticeService();

    // Gson 쓰기위함
    private Gson gson = new Gson(); // json 으로 바꿔는 놈


    public void init() {
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            process(req, res); //
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
        List<Notice> list = noticeService.showNoticeByAll();  // db 불러오기

        System.out.println(list);
//        System.out.println("DB연결됨.");

//        req.setAttribute("page", "notice.jsp");
//        // 디스패치
//        RequestDispatcher re = req.getRequestDispatcher("/main_template.jsp");
//        re.forward(req, res);

        String noticeListJson = this.gson.toJson(list);   // tojson 을 써서 json으로 바꿔줌
        System.out.println(noticeListJson);
        PrintWriter out = res.getWriter(); // response 객체에서 getWriter 메소드를 호출해서 PrintWrite 를 얻어 냄
        res.setContentType("application/json"); // 이거 중요함.  json 데이터를 보내고 받기 때문에
        res.setCharacterEncoding("utf-8");
        out.print(noticeListJson); // 출력 데이터는 임시 버퍼에 저장 됨
        out.flush(); // 브라우저에서 받음, 버퍼에 있는 데이터를 실제 출력 스트림으로 밀어내어 전송하게 됨


    }
}
