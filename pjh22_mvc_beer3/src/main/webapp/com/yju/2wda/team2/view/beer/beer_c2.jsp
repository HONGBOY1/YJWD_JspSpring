<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/globalData.jsp" %>
<%@ page import="pjh22_mvc_beer3.model.beer.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이쇼핑몰(PJH22) - 맥주코드 입력(C)</title>
</head>
<body>
<h1>마이쇼핑몰(PJH22) - 맥주자료입력(C)</h1>
<hr>
 <form action="./BeerController.be" method="post">
	 <select name="type">
	 	<option value="null">종류별</option>
	 	<option value="01">에일</option>
	 	<option value="02">라거</option>
	 </select>
	 <select name="country">
	 	<option value="null">국가별</option>
	 	<option value="002">한국</option>
	 	<option value="005">프랑스</option>
	 	<option value="006">중국</option>
	 	<option value="003">벨기에</option>
	 	<option value="004">미국</option>
	 	<option value="001">오스트리아</option>
	 </select>
	 <input type="hidden" name="actionType" value="CC">
	  <input type="submit" value="검색" class="btn">
 </form>
 <%
 	 Object data=" ";
 	 if(data==null) data="";
 	 else data = request.getAttribute("data");
 %>
<h2>b_id 값은 DB에서 결정(auto increment)</h2>
	<form method="post" action="./BeerController.be">
		<br>맥주코드 : <input type="text" name="b_code" size="30" value="<%=data%>">
		<br>맥주종류 : <input type="text" name="b_category" size="30">
		<br>맥주이름 : <input type="text" name="b_name" size="30">
		<br>맥주국가 : <input type="text" name="b_country" size="30">
		<br>맥주가격 : <input type="text" name="b_price" size="30">
		<br>맥주알콜도수 : <input type="text" name="b_alcohol" size="30">
		<br>맥주설명 : <input type="text" name="b_content" size="30">
		<br>맥주사진 : <input type="text" name="b_image" size="30">
		<br><input type="hidden" name="actionType" value="C">
		<br><input type="submit"  value="저장">
	</form>
	<br><a href="<%= rootDir %>/index.jsp">홈으로 돌아가기</a>
</body>
</html>