<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
	
	<%@ page import="pjh22_mvc_plant.model.product.*"%>
   <%@ page import="java.util.*"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>plant[join]</title>
</head>
<body>


 <%
   ProductDTO product;
   ArrayList<ProductDTO> productList;
   ProductPageInfoVO mpiVO;
   mpiVO = (ProductPageInfoVO)session.getAttribute("productPageInfoVO");
   productList = (ArrayList<ProductDTO>)request.getAttribute("productlist");
   
   int currentPageNo = mpiVO.getCurrentPageNo();
   
 %>

<%@ include file="/header.jsp" %> 
<div class="container text-center">
	<table border="1" class="table">
		<thead>
		  <tr>
            <th nowrap>상품번호</th>
            <th>상품이미지</th>
            <th nowrap>상품카테고리</th>
            <th>상품제목</th>
            <th>상품설명</th>
            <th nowrap>상품수량</th>
            <th nowrap>상품가격</th>
            <th nowrap>상품난이도</th>
            <th nowrap>상품물주기</th>
            <th nowrap>상품수정</th>
            <th nowrap>상품삭제</th>
         </tr>
      </thead>
      <tbody>
<%
for (int i = 0; i < productList.size(); i++){
	product = productList.get(i);
%>
         <tr>
            <td><%=product.getPro_num()%></td>
            <td><img src="<%=productImage%><%=product.getPro_img()%>" width="100"/></td>
            <td><%=product.getPro_cg() %></td>
            <td><%=product.getPro_name()%></td>
            <td><%=product.getPro_content()%></td>
            <td><%=product.getPro_cnt() %></td>
            <td nowrap><c:set var="PX" value="<%=product.getPro_price() %>" /> <fmt:formatNumber value="${PX}" pattern="#,### 원"/></td>
            <td><%=product.getPro_level() %></td>
            <td><%=product.getPro_water() %></td>
            <td><a href="./ProductController.pro?actionType=pro_chk&pro_num=<%=product.getPro_num()%>">수정</a></td>
            <td><a href="./ProductController.pro?actionType=pro_delete&pro_num=<%=product.getPro_num()%>">삭제</a></td>
         </tr>
<%} %>
      </tbody>
   </table>
<a href="./ProductController.pro?actionType=pro_List&currentPageNo=0">[FIRST]</a>
	<%
		if ( currentPageNo > 0){
	%>
	<a href="./ProductController.pro?actionType=pro_List&currentPageNo=<%= currentPageNo - 1 %>">[PRE]</a>
	<%
		}else{
	%>
		[PRE]
	<%
		}

		for(int i=mpiVO.getStartPageNo(); i<mpiVO.getEndPageNo(); i++){
			if(i== currentPageNo){
	%>
			[<%=(i+1)%>]
	<%
			}else {
	%>
			<a href="./ProductController.pro?actionType=pro_List&currentPageNo=<%= i %>">[<%=i+1%>]</a>
	<%		
		}}
	%>	
	<%
		if(currentPageNo < mpiVO.getPageCnt()-1){
	%>
		<a href="./ProductController.pro?actionType=pro_List&currentPageNo=<%= currentPageNo + 1 %>">[NXT]</a>
	<%
		}else{
	%>
		[NXT]
	<%
		}
	%>
<a href="./ProductController.pro?actionType=pro_List&currentPageNo=<%=(mpiVO.getPageCnt()-1)%>">[END]</a>
</div>
</body>

</html>