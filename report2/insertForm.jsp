<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>사용자 입력</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
  <div class="container">
    <h2 class="text-center mt-4">사용자 입력</h2>
    <form action="insertPro.jsp" method="post">
      <div class="form-group">
        <label for="id">ID:</label>
        <input type="text" class="form-control" name="id" required>
      </div>
      <div class="form-group">
        <label for="name">NAME:</label>
        <input type="text" class="form-control" name="name" required>
      </div>
      <div class="form-group">
        <label for="pwd">PASSWORD:</label>
        <input type="password" class="form-control" name="pwd" required>
      </div>
      <div class="text-center">
        <button type="submit" class="btn btn-primary">등록</button>
        <a href="index.jsp" class="btn btn-secondary">홈으로</a>
      </div>
    </form>
  </div>
</body>
</html>
