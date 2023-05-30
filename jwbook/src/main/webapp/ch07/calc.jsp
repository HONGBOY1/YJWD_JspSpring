<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%-- <jsp:useBean id="calc" class="ch07/Calculator" /> --%>
<jsp:setProperty name="calc" property="*" />

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
<%--  	결과: <%=calc.calc()%> --%>
</body>
</html>