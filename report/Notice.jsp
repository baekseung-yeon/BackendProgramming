<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판 글쓰기</title>
</head>
<body>
    <h2>게시판 글쓰기</h2>
    <form action="" method="post">
        제목: <input type="text" name="title" required><br>
        작성자: <input type="text" name="author" required><br>
        내용: <br><textarea name="content" rows="5" cols="40" required></textarea><br>
        <input type="submit" value="등록">
    </form>
    
    <%
        // 한글 인코딩 처리 (POST 요청일 때 필요)
        request.setCharacterEncoding("UTF-8");

        // 입력된 값 가져오기
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String content = request.getParameter("content");
        
        if (title != null && author != null && content != null) {
    %>
    
    <h2>입력된 게시글</h2>
    <p><strong>제목:</strong> <%= title %></p>
    <p><strong>작성자:</strong> <%= author %></p>
    <p><strong>내용:</strong></p>
    <p><%= content.replaceAll("\n", "<br>") %></p>
    
    <%
        }
    %>
</body>
</html>
