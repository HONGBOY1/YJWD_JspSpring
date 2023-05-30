<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
      <%@ page import="java.util.*"%>
   <%@ page import="pjh22_mvc_plant.model.member.*"%>
    <%@ page import="pjh22_mvc_plant.model.pay.*"%>
    
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>plant[join]</title>
</head>
<body>


<%@ include file="../header.jsp" %> 
	
	
	<div class="container">
		<br>
		<div class="text-center align-self-center">
           	<span class="logo">내정보[ My ]</span>
       	</div>
       	<br>
       	<br>
		<%
		PayDTO pay;
		   PayDAO payDAO;
		   ArrayList<PayDTO> paylist;
		
          	  MemberDTO member=(MemberDTO)request.getAttribute("member");
			  String id = member.getMem_id();
			  paylist = (ArrayList<PayDTO>)request.getAttribute("paylist");

			  int teg = Integer.parseInt(request.getParameter("teg"));
		 %>
		<div class="row justify-content-center">
				<div class="col-md-10 mb-5 text-center">
					<ul class="product-category">
						<li><a href="./MemberController.be?actionType=mem_my&mem_id=<%=id%>&teg=0">주문정보</a></li>
						<li><a href="./MemberController.be?actionType=mem_my&mem_id=<%=id%>&teg=1">배송정보</a></li>
						<li><a href="./MemberController.be?actionType=mem_my&mem_id=<%=id%>&teg=2">정보수정</a></li>
						<li onclick="del('<%=id%>')"><a href="#">회원탈퇴</a></li>
					</ul>
				</div>
			</div>
		 <div>
			<div>
				<div>
					<table class="table">
					   <tbody>
					    <tr>
					      <td scope="row" align=left >  <img class="img-fluid" src="<%=mianImage%>/main.png" alt="Icon"> <span><%=member.getMem_name()%> 님 안녕하세요.</span></td>
					      <td ></td>
					      <td>
					      	<div class="table-cell vertical-middle point-view text-center">
								<p>레벨</p>
							<span class="text-bold text-32"><%=member.getMem_level()%></span>
							</div>
							</td>
					    </tr>
					</table>
			</div>
		</div>
		
		<% if(teg==0){ %>
		<table class="table align-middle" >
			<thead>
				  <tr>
				    <th>#</th>
		            <th>이미지</th>
		            <th>상품제목</th>
		            <th>수량</th>
		            <th>가격</th>
		            <th>주문상태</th>
		            <th>주문날짜</th>
		         </tr>
		      </thead>
		      	<tbody>
		   	<%if(paylist.size()==0){ %>
		
		   		<tr><td colspan="7"> 주문정보가 없습니다.</td></tr>
		   						</tbody>
				</table>
		   	<%}else{%>
		     <%	
				for (int i = 0; i < paylist.size(); i++){
					pay = paylist.get(i);
			%>
				 <tbody>
				    <tr>
				    	<td></td>
				    	<td><img src="<%=pay.getPro_img()%>" width="100"/></td>
						<td><%=pay.getPro_name() %></td>
						<td><%=pay.getPro_cnt() %> 개</td>
						<td><c:set var="PX" value="<%=pay.getOrd_price()%>" /> <fmt:formatNumber value="${PX}" pattern="#,### 원"/></p></td>
						<td><%if(pay.getOrd_chk()==0){%>결제 준비중<% }%></td>
						<td><%=pay.getOrd_date() %></td>
	  				  </tr>
					</tbody>
		<%		}%>
		  <%   }%>
		  </table>
		<%   } %>
					
				
		<% if(teg==1){ %>
		
		<table class="table" >
		   <tbody>
		    <tr >
		    	<td width="1000">
		    		<div class="form-floating mb-3 col-md-12">
		                <input type="text" class="form-control" name="mem_zcode" id="mem_zcode" placeholder="mem_zcode" value="<%=member.getMem_zcode()%>" onclick="findAddr()">
		                <label for="mem_zcode">우편번호</label>
		            </div>
		            <div class="form-floating mb-3 col-md-12">
		                <input type="text" class="form-control" name="mem_add" id="mem_add" value="<%=member.getMem_add()%>" placeholder="mem_add">
		                <label for="mem_add">주소</label>
		            </div>
		            <div class="form-floating mb-3 col-md-12">
		                <input type="text" class="form-control" name="mem_add2"  id="mem_add2" value="<%=member.getMem_add2()%>" placeholder="mem_add2">
		                <label for="mem_add2">상세주소</label>
		            </div>
				</td>
				<td  style="text-align:center"  >
					<button type="submit" class="mysavebtn btn-lg" style="margin-top: 50px" onclick="addup('<%=id%>')">저장[ Save ]</button>
				</td>
		    </tr>
		</table>
		
		<%} %>
		
		<% if(teg==2){ %>
		
		<table class="table">
		   <tbody>
		    <tr >
		    	<td width="950">
		    		<div class="form-floating mb-3 col-md-12">
		               <input type="text" class="form-control align-self-center" name="mem_id" value="<%=member.getMem_id()%>" id="mem_id" placeholder="id" disabled>
		               <label for="mem_id">아이디</label>
		           </div>
		           <div class="form-floating mb-3 col-md-12">
		               <input type="text" class="form-control pw" name="mem_pwd1" id="mem_pwd" value="<%=member.getMem_pwd()%>" placeholder="mem_pwd1">
		               <label for="mem_pwd">패스워드</label>
		           </div>
		           <div class="form-floating mb-3 col-md-12">
		               <input type="text" class="form-control pw" name="mem_name1" id="mem_name" value="<%=member.getMem_name()%>" placeholder="mem_name1" >
		               <label for="mem_name">이름</label>
		           </div>
		           <div class="form-floating mb-3 col-md-12">
		               <input type="text" class="form-control pw" name="mem_nickname1" id="mem_nickname1" value="<%=member.getMem_nickname()%>" placeholder="mem_nickname">
		               <label for="mem_nickname">닉네임</label>
		           </div>
		           <div class="form-floating mb-3 col-md-12">
		               <input type="email" class="form-control pw"name="mem_email1"  id="mem_email1" value="<%=member.getMem_email()%>" placeholder="mem_email">
		               <label for="mem_email">이메일</label>
		           </div>
		           <div class="form-floating mb-3 col-md-12">
		               <input type="text" class="form-control" name="mem_phone1" id="mem_phone1" placeholder="mem_phone" value="<%=member.getMem_phone()%>" oninput="autoHyphen(this)" maxlength="12">
		               <label for="mem_phone">전화번호</label>
		           </div>
		           <div class="form-floating mb-3 col-md-12">
		                <input type="text" class="form-control" name="mem_date"  id="mem_date" value="<%=member.getMem_date()%>" placeholder="mem_date" disabled>
		                <label for="mem_date">가입날짜</label>
		            </div>
				</td>
				<td  style="text-align:center"  >
					<button type="submit" class="myupbtn btn-lg" style="margin-top: 400px" onclick="myup('<%=id%>')">정보수정[ update ]</button>
				</td>
		    </tr>
		</table>
		 
		<%} %>
</div>
<script>

    function addup(mem_id) {
    	var mem_zcode = $('input[name=mem_zcode]').val();
    	var mem_add = $('input[name=mem_add]').val();
    	var mem_add2 = $('input[name=mem_add2]').val();

    	location.href = "./MemberController.be?actionType=mem_addrup&mem_id="+mem_id+"&mem_zcode="+mem_zcode+"&mem_add="+mem_add+"&mem_add2="+mem_add2;
	}
    function myup(mem_id) {
    	var mem_pwd = $('input[name=mem_pwd1]').val();
    	var mem_name = $('input[name=mem_name1]').val();
    	var mem_nickname = $('input[name=mem_nickname1]').val();
    	var mem_email = $('input[name=mem_email1]').val();
    	var mem_phone = $('input[name=mem_phone1]').val();
    	
    	location.href = "./MemberController.be?actionType=mem_myup&mem_id="+mem_id+"&mem_pwd="+mem_pwd+"&mem_name="+mem_name+"&mem_nickname="+mem_nickname+"&mem_email="+mem_email+"&mem_phone="+mem_phone;
	}
    
	function del(mem_id) {

		if(confirm("회원 탈퇴 하시겠습니까??")){
			location.href = "./MemberController.be?actionType=mem_D&mem_id="+mem_id;	
		}
	
	}
	</script>
</body>

</html>