<%@page import="cs.dit.member.MemberDAO"%>
<%@page import="cs.dit.member.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.sql.*, javax.naming.*, javax.sql.*" %>
	
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>사용자 정보 변경</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container">
		<br>
		<h2 class="text-center font-weight-bold">사용자 정보 변경</h2>
		<hr/>
		<form action="update.do" method="post" enctype="multipart/form-data">
		  <div class="form-group">
	      <label for="id">ID:</label>
	      <input type="text" class="form-control" id="id" name="id" value="${dto.id}" readonly>
	    </div>
	    <div class="form-group">
	      <label for="name">NAME:</label>
	      <input type="text" class="form-control" id="name" name="name" value="${dto.name}">
	    </div>
	    <div class="form-group">
	      <label for="pwd">PASSWORD:</label>
	      <input type="password" class="form-control" id="pwd" name="pwd" value="${dto.pwd}">
	    </div>
	    <div class="form-group">
	      <label for="photo">PHOTO:</label>
	      <input type="file" class="form-control" id="photo" name="photo">
	      <img src="/photos/${dto.photo}" width="100" height= "80">${dto.photo}
	      <input type="hidden" name="photo" value="${dto.photo}">
	    </div>
	    <br>
	    <div class="text-center">
	    		<input type="submit" value="변경" class="btn btn-secondary">  
					<input type="button" value="삭제" class="btn btn-secondary" onclick="location.href='delete.do?id=${dto.id}'">
					<input type="button" value="목록" class="btn btn-secondary" onclick="location.href='list.do'">
			</div>
		</form>
	</div>
</body>
</html>