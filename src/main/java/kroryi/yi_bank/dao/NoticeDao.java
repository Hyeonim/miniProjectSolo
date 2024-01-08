package kroryi.yi_bank.dao;
import kroryi.yi_bank.dto.Notice;
import java.sql.SQLException;
import java.util.List;
//이 인터페이스는 NoticeDaoImpl 클래스의 메서드를 정의합니다.
//이 인터페이스를 구현하는 클래스에서 해당 메서드들을 구현해야 합니다.
public interface NoticeDao {
	public List<Notice> selectNoticeByAll() throws SQLException;
	public Notice selectNoticeByNo(Notice notice) throws SQLException;
	public int insertNotice(Notice notice) throws SQLException;
	public int updateNotice(Notice notice) throws SQLException;
	public int deleteNotice(Notice notice) throws SQLException;
}
