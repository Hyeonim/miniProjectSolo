package kroryi.yi_bank.service;

import kroryi.yi_bank.dao.NoticeDao;
import kroryi.yi_bank.dao.impl.NoticeDaoImpl;
import kroryi.yi_bank.dto.Notice;

import java.sql.SQLException;
import java.util.List;


//NoticeService 클래스: NoticeDao를 사용하여 비즈니스 로직을 처리하는 서비스 클래스입니다.
//공지사항에 대한 CRUD(Create, Read, Update, Delete) 동작을 수행합니다.
public class NoticeService {
	private NoticeDao dao;


	public NoticeService() {
		dao = NoticeDaoImpl.getInstance();
	}
	public List<Notice> showNoticeByAll() throws SQLException {
		return dao.selectNoticeByAll();
	}
	public Notice showNoticeByNo(int notice) throws SQLException {
		return dao.selectNoticeByNo(notice);
	}
	public int addNotice(Notice notice) throws SQLException {
		return dao.insertNotice(notice);
	}
	public int modifyNotice(Notice notice) throws SQLException {
		return dao.updateNotice(notice);
	}
	public int removeNotice(Notice notice) throws SQLException {
		return dao.deleteNotice(notice.getNo());
	}


}
