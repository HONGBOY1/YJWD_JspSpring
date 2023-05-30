<%@page import="java.util.ArrayList"%>
<%@page import="pjh22_mvc_beer3.model.beer.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/globalData.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%

int count = Integer.parseInt(request.getParameter("count"));
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body> 
<%

for(int i = 1; i<=count; i++){
    String filename = "fileName"+i;
    String filelength = "fileLength"+i;
    if(request.getParameter(filename) != null){%>       
    파일이름 : <%=request.getParameter(filename)%><br>
    파일 용량 : <%=request.getParameter(filelength) %>KB<br><br>
    <%}     
}
%>
<br>
   <a href="<%=rootDir%>/index.jsp">홈으로 돌아가기</a>
</body>
</html>