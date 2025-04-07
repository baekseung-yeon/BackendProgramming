<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.sql.*" %>
<%
request.setCharacterEncoding("UTF-8");
String id = request.getParameter("id");
String name = request.getParameter("name");
String pwd = request.getParameter("pwd");

Class.forName("org.mariadb.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/backend", "bsy", "1111");

String sql = "UPDATE member SET name=?, pwd=? WHERE id=?";
PreparedStatement pstmt = con.prepareStatement(sql);
pstmt.setString(1, name);
pstmt.setString(2, pwd);
pstmt.setString(3, id);
pstmt.executeUpdate();

pstmt.close(); con.close();
response.sendRedirect("list.jsp");
%>
