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
   
   String pro_cg=request.getParameter("pro_cg");
   int currentPageNo = mpiVO.getCurrentPageNo();   
 %>

<%@ include file="/header.jsp" %> 

  <section class="ftco-section">
    	<div class="container">
    		<div class="row justify-content-center">
    			<div class="col-md-10 mb-5 text-center">
    				<ul class="product-category">
    					<li><a href="./ProductController.pro?actionType=pro_mlist&pro_cg=0" >전체보기</a></li>
    					<li><a href="./ProductController.pro?actionType=pro_mlist&pro_cg=1" >씨앗</a></li>
    					<li><a href="./ProductController.pro?actionType=pro_mlist&pro_cg=2">반려식물</a></li>
    					<li><a href="./ProductController.pro?actionType=pro_mlist&pro_cg=3" >정원용품</a></li>
    					<li><a href="./ProductController.pro?actionType=pro_mlist&pro_cg=4">중고식물</a></li>
    				</ul>
    			</div>
    		</div>
    		<div class="row">
    		   <%
				for (int i = 0; i < productList.size(); i++){
					product = productList.get(i);
				%>
    			<div class="col-md-6 col-lg-3 ftco-animate">
    				<div class="product">
    					<a href="./ProductController.pro?actionType=pro_buy&pro_num=<%=product.getPro_num()%>" class="img-prod">
    					<img class="img-fluid"  src="<%=productImage%><%=product.getPro_img()%>" alt="Colorlib Template" >
	    					<div class="overlay"></div>
	    				</a>
    					<div class="text py-3 pb-4 px-3 text-center">
    						<h3><a href="./ProductController.pro?actionType=pro_buy&pro_num=<%=product.getPro_num()%>"><%=product.getPro_name()%></a></h3>
    						<div class="d-flex">
    							<div class="pricing">
		    						<p class="price">
		    						<c:set var="PX" value="<%=product.getPro_price() %>" /> <fmt:formatNumber value="${PX}" pattern="#,### 원"/></p>
									</p>
		    					</div>
	    					</div>
	    					
    						
    					</div>
    				</div>
    			</div>
    		<%} %>
 		
    		<div class="row mt-5">
          <div class="col text-center">
            <div class="block-27">
              <ul>
              	
              	<%
				if ( currentPageNo > 0){
				%>
					<li><a href="./ProductController.pro?actionType=pro_mlist&pro_cg=<%=pro_cg%>&currentPageNo=<%= currentPageNo - 1 %>">&lt;</a></li>
				<%
					}else{
				%>
					<li><a href="">&lt;</a></li>
				<%
					}
              	%>    
				<%
				for(int i=mpiVO.getStartPageNo(); i<mpiVO.getEndPageNo(); i++){
					if(i== currentPageNo){
				%>
					<%=(i+1)%>
				<%
					}else {
				%>
					<a href="./ProductController.pro?actionType=pro_mlist&pro_cg=<%=pro_cg%>&currentPageNo=<%= i %>"><%=i+1%></a>
				<%		
				}}
				%>
				<%
				if(currentPageNo < mpiVO.getPageCnt()-1){
				%>
					<li><a href="./ProductController.pro?actionType=pro_mlist&pro_cg=<%=pro_cg%>&currentPageNo=<%= currentPageNo + 1 %>">&gt;</a></li>
				<%
					}else{
				%>
					<li><a href="">&gt;</a></li>
				<%
					}
				%>

              </ul>
            </div>
          </div>
        </div>
    	</div>
    </section>
</body>

</html>