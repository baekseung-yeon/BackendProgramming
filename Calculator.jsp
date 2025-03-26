<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JSP 계산기</title>
</head>
<body>
    <h2>간단한 계산기</h2>
    <form action="" method="post">
        숫자 1: <input type="number" name="num1" required><br>
        연산자: <br>
        <button type="submit" name="operator" value="+">+</button>
        <button type="submit" name="operator" value="-">-</button>
        <button type="submit" name="operator" value="*">*</button>
        <button type="submit" name="operator" value="/">/</button><br>
        숫자 2: <input type="number" name="num2" required><br>
    </form>
    
    <%
        request.setCharacterEncoding("UTF-8");
        String num1Str = request.getParameter("num1");
        String num2Str = request.getParameter("num2");
        String operator = request.getParameter("operator");
        
        if (num1Str != null && num2Str != null && operator != null) {
            try {
                double num1 = Double.parseDouble(num1Str);
                double num2 = Double.parseDouble(num2Str);
                double result = 0;
                boolean validOperation = true;
                
                switch (operator) {
                    case "+": result = num1 + num2; break;
                    case "-": result = num1 - num2; break;
                    case "*": result = num1 * num2; break;
                    case "/": 
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            validOperation = false;
                        }
                        break;
                    default: validOperation = false;
                }
    %>
    
    <h2>계산 결과</h2>
    <%
        if (validOperation) {
    %>
        <p><strong>결과:</strong> <%= result %></p>
    <%
        } else {
    %>
        <p style="color: red;">잘못된 연산입니다.</p>
    <%
        }
    %>
    
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
