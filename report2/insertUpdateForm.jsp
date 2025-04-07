<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.sql.*" %>
<%
String id = request.getParameter("id");
String name = "", pwd = "";
String action = "insertPro.jsp"; // 기본값은 등록 처리 페이지

if(id != null && !id.equals("")) {
    Class.forName("org.mariadb.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/backend", "bsy", "1111");

    String sql = "SELECT * FROM member WHERE id=?";
    PreparedStatement pstmt = con.prepareStatement(sql);
    pstmt.setString(1, id);
    ResultSet rs = pstmt.executeQuery();

    if(rs.next()) {
        name = rs.getString("name");
        pwd = rs.getString("pwd");
        action = "updatePro.jsp"; // 정보가 있으면 수정 모드
    }

    rs.close();
    pstmt.close();
    con.close();
}
%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>사용자 입력/수정</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
  <div class="container mt-4">
    <h2 class="text-center">사용자 정보 <%= (id != null && !id.equals("")) ? "수정" : "입력" %></h2>
    <form action="<%=action %>" method="post">
      <div class="form-group">
        <label for="id">ID</label>
        <input type="text" class="form-control" name="id" value="<%=id%>" <%= (id != null) ? "readonly" : "" %> required>
      </div>
      <div class="form-group">
        <label for="name">이름</label>
        <input type="text" class="form-control" name="name" value="<%=name%>" required>
      </div>
      <div class="form-group">
        <label for="pwd">비밀번호</label>
        <input type="password" class="form-control" name="pwd" value="<%=pwd%>" required>
      </div>
      <div class="text-center">
        <button type="submit" class="btn btn-primary"><%= (id != null && !id.equals("")) ? "변경" : "등록" %></button>
        <% if(id != null && !id.equals("")) { %>
          <a href="delete.jsp?id=<%=id%>" class="btn btn-danger">삭제</a>
        <% } %>
        <a href="list.jsp" class="btn btn-secondary">목록</a>
      </div>
    </form>
  </div>
</body>
</html>
