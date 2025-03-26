<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>학생 성적 계산</title>
</head>
<body>
    <h2>학생 성적 입력</h2>
    <form action="" method="post">
        국어 점수: <input type="number" name="korean" required><br>
        영어 점수: <input type="number" name="english" required><br>
        수학 점수: <input type="number" name="math" required><br>
        <input type="submit" value="계산">
    </form>
    
    <%
        // 사용자 입력 값 처리
        String koreanStr = request.getParameter("korean");
        String englishStr = request.getParameter("english");
        String mathStr = request.getParameter("math");
        
        if (koreanStr != null && englishStr != null && mathStr != null) {
            try {
                int korean = Integer.parseInt(koreanStr);
                int english = Integer.parseInt(englishStr);
                int math = Integer.parseInt(mathStr);
                
                int total = korean + english + math;
                double average = total / 3.0;
    %>
    
    <h2>학생 성적 결과</h2>
    <p>국어: <%= korean %> 점</p>
    <p>영어: <%= english %> 점</p>
    <p>수학: <%= math %> 점</p>
    <p>총점: <%= total %> 점</p>
    <p>평균: <%= String.format("%.2f", average) %> 점</p>
    
    <%
            } catch (NumberFormatException e) {
    %>
    <p style="color: red;">올바른 숫자를 입력하세요.</p>
    <%
            }
        }
    %>
</body>
</html>
