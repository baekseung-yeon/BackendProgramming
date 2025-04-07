<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.sql.*" %>
<%
String id = request.getParameter("id");
Class.forName("org.mariadb.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/backend", "bsy", "1111");

String sql = "DELETE FROM member WHERE id=?";
PreparedStatement pstmt = con.prepareStatement(sql);
pstmt.setString(1, id);
pstmt.executeUpdate();

pstmt.close(); con.close();
response.sendRedirect("list.jsp");
%>
