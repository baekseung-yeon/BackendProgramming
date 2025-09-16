package cs.dit.board;

import java.sql.Date;

public class BoardDTO {
    private int bcode;
    private String subject;
    private String content;
    private String writer;
    private Date regdate;
    
    // 기본 생성자
    public BoardDTO() {}
    
    // 모든 필드를 포함한 생성자
    public BoardDTO(int bcode, String subject, String content, String writer, Date regdate) {
        this.bcode = bcode;
        this.subject = subject;
        this.content = content;
        this.writer = writer;
        this.regdate = regdate;
    }
    
    // Getter와 Setter 메소드들
    public int getBcode() {
        return bcode;
    }
    
    public void setBcode(int bcode) {
        this.bcode = bcode;
    }
    
    public String getSubject() {
        return subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getWriter() {
        return writer;
    }
    
    public void setWriter(String writer) {
        this.writer = writer;
    }
    
    public Date getRegdate() {
        return regdate;
    }
    
    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }
    
    @Override
    public String toString() {
        return "BoardDTO{" +
                "bcode=" + bcode +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", regdate=" + regdate +
                '}';
    }
}
