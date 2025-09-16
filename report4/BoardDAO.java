package cs.dit.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BoardDAO {
    
    private DataSource dataSource;
    
    public BoardDAO() {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/backend");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
    
    // 전체 게시글 수를 반환하는 메소드
    public int getTotalCount() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int totalCount = 0;
        
        try {
            conn = dataSource.getConnection();
            String sql = "SELECT COUNT(*) FROM board";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                totalCount = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return totalCount;
    }
    
    // 페이지네이션을 위한 게시글 목록을 반환하는 메소드
    public ArrayList<BoardDTO> getBoardList(int page, int pageSize) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<BoardDTO> list = new ArrayList<>();
        
        try {
            conn = dataSource.getConnection();
            int offset = (page - 1) * pageSize;
            String sql = "SELECT bcode, subject, content, writer, regdate FROM board " +
                        "ORDER BY bcode DESC LIMIT ?, ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, offset);
            pstmt.setInt(2, pageSize);
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                BoardDTO dto = new BoardDTO();
                dto.setBcode(rs.getInt("bcode"));
                dto.setSubject(rs.getString("subject"));
                dto.setContent(rs.getString("content"));
                dto.setWriter(rs.getString("writer"));
                dto.setRegdate(rs.getDate("regdate"));
                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return list;
    }
    
    // 게시글 상세 조회
    public BoardDTO getBoardDetail(int bcode) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        BoardDTO dto = null;
        
        try {
            conn = dataSource.getConnection();
            String sql = "SELECT bcode, subject, content, writer, regdate FROM board WHERE bcode = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bcode);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                dto = new BoardDTO();
                dto.setBcode(rs.getInt("bcode"));
                dto.setSubject(rs.getString("subject"));
                dto.setContent(rs.getString("content"));
                dto.setWriter(rs.getString("writer"));
                dto.setRegdate(rs.getDate("regdate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return dto;
    }
    
    // 게시글 작성
    public int insertBoard(BoardDTO dto) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        
        try {
            conn = dataSource.getConnection();
            String sql = "INSERT INTO board(subject, content, writer, regdate) VALUES(?, ?, ?, NOW())";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getSubject());
            pstmt.setString(2, dto.getContent());
            pstmt.setString(3, dto.getWriter());
            
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return result;
    }
    
    // 게시글 수정
    public int updateBoard(BoardDTO dto) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        
        try {
            conn = dataSource.getConnection();
            String sql = "UPDATE board SET subject = ?, content = ? WHERE bcode = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getSubject());
            pstmt.setString(2, dto.getContent());
            pstmt.setInt(3, dto.getBcode());
            
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return result;
    }
    
    // 게시글 삭제
    public int deleteBoard(int bcode) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        
        try {
            conn = dataSource.getConnection();
            String sql = "DELETE FROM board WHERE bcode = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bcode);
            
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return result;
    }
}
