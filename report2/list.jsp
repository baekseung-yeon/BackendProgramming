<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>사용자 목록</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
  <div class="container mt-5">
    <h2 class="text-center">사용자 목록</h2>
    <hr/>
<%
  Class.forName("org.mariadb.jdbc.Driver");
  Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/backend", "bsy", "1111");
  Statement st = con.createStatement();
  ResultSet rs = st.executeQuery("SELECT * FROM member");
%>
    <table class="table table-bordered table-hover mt-3">
      <thead class="thead-dark">
        <tr>
          <th>ID</th>
          <th>이름</th>
          <th>비밀번호</th>
          <th>수정</th>
          <th>삭제</th>
        </tr>
      </thead>
      <tbody>
<%
  while(rs.next()) {
    String id = rs.getString("id");
    String name = rs.getString("name");
    String pwd = rs.getString("pwd");
%>
        <tr>
          <td><%= id %></td>
          <td><%= name %></td>
          <td><%= pwd %></td>
          <td>
            <a href="updateForm.jsp?id=<%=id%>" class="btn btn-sm btn-info">수정</a>
          </td>
          <td>
            <a href="delete.jsp?id=<%=id%>" class="btn btn-sm btn-danger"
               onclick="return confirm('정말 삭제하시겠습니까?');">삭제</a>
          </td>
        </tr>
<%
  }
  rs.close();
  st.close();
  con.close();
%>
      </tbody>
    </table>

    <div class="text-center">
      <a href="index.jsp" class="btn btn-secondary">홈으로</a>
      <a href="insertForm.jsp" class="btn btn-success">새 사용자 등록</a>
    </div>
  </div>
</body>
</html>
