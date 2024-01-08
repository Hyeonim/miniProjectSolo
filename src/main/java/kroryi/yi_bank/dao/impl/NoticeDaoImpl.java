package kroryi.yi_bank.dao.impl;

//import com.yi.dao.NoticeDao;
//import com.yi.dto.Notice;

import kroryi.yi_bank.dao.NoticeDao;
import kroryi.yi_bank.dto.Notice;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


//NoticeDao 인터페이스의 구현체로, 데이터베이스와 상호 작용하기 위한 기능을 제공합니다.
//selectNoticeByAll, selectNoticeByNo, insertNotice, updateNotice, deleteNotice 와
//같은 메서드를 통해 공지사항 정보를 가져오거나 데이터베이스에 추가, 수정, 삭제할 수 있습니다.

public class NoticeDaoImpl implements NoticeDao {
	private static final NoticeDaoImpl instance = new NoticeDaoImpl();
	public NoticeDaoImpl() {};
	String jdbcDriver = "jdbc:apache:commons:dbcp:bank";
	public static NoticeDaoImpl getInstance() {
		return instance;
	}

	@Override
	public List<Notice> selectNoticeByAll() throws SQLException {
		List<Notice> list = new ArrayList<>();
		String sql = "select * from notice order by no desc";
		try(Connection con = DriverManager.getConnection(jdbcDriver);
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			while(rs.next()) {
				list.add(getNotice(rs));
			}
		}
		return list;
	}

	private Notice getNotice(ResultSet rs) throws SQLException {
		int no = rs.getInt("no");
		String title = rs.getString("subject");
		String name = rs.getString("writer");
		Date writeDate = rs.getTimestamp("write_date");
		String content = rs.getString("content");
		return new Notice(no, title, name, writeDate, content);
	}



	@Override
	public Notice selectNoticeByNo(Notice notice) throws SQLException {
		String sql = "select * from notice where no = ?";
		try(Connection con = DriverManager.getConnection(jdbcDriver);
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, notice.getNo());
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					return getNotice(rs);
				}
			}
		}
		return null;
	}

	@Override
	public int insertNotice(Notice notice) throws SQLException {
		int res = -1;
		String sql = "insert into notice(subject,writer,write_date,content) values(?,?,?,?)";
		try(Connection con = DriverManager.getConnection(jdbcDriver);
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, notice.getSubject());
			pstmt.setString(2, notice.getWriter());
			pstmt.setTimestamp(3, new Timestamp(notice.getWriteDate().getTime()));
			pstmt.setString(4, notice.getContent());
			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public int updateNotice(Notice notice) throws SQLException {
		int res = -1;
		String sql = "update notice set subject=?, writer = ?, content = ? where no= ?";
		try(Connection con = DriverManager.getConnection(jdbcDriver);
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, notice.getSubject());
			pstmt.setString(2, notice.getWriter());
			pstmt.setString(3, notice.getContent());
			pstmt.setInt(4, notice.getNo());
			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public int deleteNotice(Notice notice) throws SQLException {
		int res = -1;
		String sql = "delete from notice where no = ?";
		try(Connection con = DriverManager.getConnection(jdbcDriver);
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, notice.getNo());
			res = pstmt.executeUpdate();
		}
		return res;
	}

}
