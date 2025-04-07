<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.sql.*" %>
<%
String id = request.getParameter("id");
String name = "", pwd = "";
Class.forName("org.mariadb.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/backend", "bsy", "1111");

PreparedStatement pstmt = con.prepareStatement("SELECT * FROM member WHERE id=?");
pstmt.setString(1, id);
ResultSet rs = pstmt.executeQuery();
if(rs.next()){
  name = rs.getString("name");
  pwd = rs.getString("pwd");
}
rs.close(); pstmt.close(); con.close();
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>사용자 수정</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
  <div class="container mt-4">
    <h2 class="text-center">사용자 정보 수정</h2>
    <form action="updatePro.jsp" method="post">
      <input type="hidden" name="id" value="<%=id%>">
      <div class="form-group">
        <label>이름</label>
        <input type="text" name="name" class="form-control" value="<%=name%>">
      </div>
      <div class="form-group">
        <label>비밀번호</label>
        <input type="password" name="pwd" class="form-control" value="<%=pwd%>">
      </div>
      <div class="text-center">
        <button type="submit" class="btn btn-primary">변경</button>
        <a href="delete.jsp?id=<%=id%>" class="btn btn-danger">삭제</a>
        <a href="list.jsp" class="btn btn-secondary">목록</a>
      </div>
    </form>
  </div>
</body>
</html>
