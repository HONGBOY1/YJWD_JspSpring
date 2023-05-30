<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	 <%
	 int n1 = Integer.parseInt(request.getParameter("n1"));
		int n2 = Integer.parseInt(request.getParameter("n2"));
		String op = request.getParameter("op");
		
		long result =0;
		
		switch (request.getParameter("op")){
			case "+" : result = n1 + n2; break;
			case "-" : result = n1 - n2; break;
			case "/" : result = n1 / n2; break;
			case "*" : result = n1 * n2; break;
		}
 	%>
 	<h2>계산기 서블릿</h2><hr>
 	계산결과 <%= result%>
</body>
</html>