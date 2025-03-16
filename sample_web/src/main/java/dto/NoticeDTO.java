package dto;

import java.sql.Timestamp;

public class NoticeDTO {
	int nidx, nvew;
	String subject,writer,passwd,texts,filenm,nfile;
	Timestamp ndate;
	
	public int getNidx() {
		return nidx;
	}
	public void setNidx(int nidx) {
		this.nidx = nidx;
	}
	public int getNvew() {
		return nvew;
	}
	public void setNvew(int nvew) {
		this.nvew = nvew;
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
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getTexts() {
		return texts;
	}
	public void setTexts(String texts) {
		this.texts = texts;
	}
	public String getFilenm() {
		return filenm;
	}
	public void setFilenm(String filenm) {
		this.filenm = filenm;
	}
	public String getNfile() {
		return nfile;
	}
	public void setNfile(String nfile) {
		this.nfile = nfile;
	}
	public Timestamp getNdate() {
		return ndate;
	}
	public void setNdate(Timestamp ndate) {
		this.ndate = ndate;
	}
}
