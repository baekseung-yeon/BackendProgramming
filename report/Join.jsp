<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 가입</title>
</head>
<body>
    <h2>회원 가입</h2>
    <form action="" method="post">
        이름: <input type="text" name="name" required><br>
        이메일: <input type="email" name="email" required><br>
        비밀번호: <input type="password" name="password" required><br>
        성별:
        <input type="radio" name="gender" value="남성" required> 남성
        <input type="radio" name="gender" value="여성" required> 여성<br>
        관심 분야:<br>
        <input type="checkbox" name="interests" value="스포츠"> 스포츠
        <input type="checkbox" name="interests" value="음악"> 음악
        <input type="checkbox" name="interests" value="여행"> 여행
        <input type="checkbox" name="interests" value="독서"> 독서<br>
        <input type="submit" value="가입">
    </form>
    
    <%
        request.setCharacterEncoding("UTF-8"); // 한글 인코딩 설정
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String[] interests = request.getParameterValues("interests");
        
        if (name != null && email != null && password != null && gender != null) {
    %>
    
    <h2>가입 정보</h2>
    <p><strong>이름:</strong> <%= name %></p>
    <p><strong>이메일:</strong> <%= email %></p>
    <p><strong>성별:</strong> <%= gender %></p>
    <p><strong>관심 분야:</strong> 
    <%
        if (interests != null) {
            for (String interest : interests) {
    %>
            <%= interest %> &nbsp;
    <%
            }
        } else {
    %>
            선택한 항목 없음
    <%
        }
    %>
    </p>
    
    <%
        }
    %>
</body>
</html>
