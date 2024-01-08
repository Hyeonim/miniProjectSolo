package kroryi.yi_bank.dto;

import java.util.Date;



//Notice 클래스: 공지사항에 관한 데이터를 담는 클래스입니다.
//공지사항의 번호(no), 제목(subject), 작성자(writer), 작성일자(writeDate), 내용(content) 등의 속성을 갖고 있습니다.
public class Notice {
	private int no;
	private String subject;
	private String writer;
	private Date writeDate;
	private String content;
	public Notice() {
		
	}
	
	public Notice(int no) {
		this.no = no;
	}
			
	public Notice(String content) {
		this.content = content;
	}

	public Notice(int no, String subject, String writer) {
		this.no = no;
		this.subject = subject;
		this.writer = writer;
	}
	
	
	public Notice(int no, String subject, String writer, String content) {
		this.no = no;
		this.subject = subject;
		this.writer = writer;
		this.content = content;
	}

	public Notice(String subject, String writer, Date writeDate, String content) {
		this.subject = subject;
		this.writer = writer;
		this.writeDate = writeDate;
		this.content = content;
	}

	public Notice(int no, String subject, String writer, Date writeDate, String content) {
		this.no = no;
		this.subject = subject;
		this.writer = writer;
		this.writeDate = writeDate;
		this.content = content;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return String.format("Notice [no=%s, title=%s, name=%s, writeDate=%s, content=%s]", no, subject, writer, writeDate,
				content);
	}
	
}
