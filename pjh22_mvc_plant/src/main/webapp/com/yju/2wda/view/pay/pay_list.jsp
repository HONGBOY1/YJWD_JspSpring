<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
	<%@ page import="pjh22_mvc_plant.model.product.*"%>
	<%@ page import="pjh22_mvc_plant.model.cart.*"%>
   <%@ page import="java.util.*"%>
      <%@ page import="pjh22_mvc_plant.model.member.*"%>
       <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>plant[pay]</title>
</head>
<body>


<script type="text/javascript">


</script>

<%
	request.setCharacterEncoding("utf-8");
	ArrayList<CartDTO> cart = null;
	
	Object obj = session.getAttribute("cart");	//세션 객체에서 cart 값을 가져온다.
	
	if(obj == null) {	//세션 정보가 없으면 배열을 생성 : 주문한 제품이 없다
		cart = new ArrayList<CartDTO>();	
	} else {			//세션 정보가 있으면 강제로 캐스팅 : 주문한 제품이 있다
		cart = (ArrayList<CartDTO>) obj;
	}
	
	int pro_num;
	String pro_img;
	String pro_name;
	String pro_content;		
	int pro_cnt;
	String pro_price =request.getParameter("pro_price");
	int teg;
	MemberDTO member=(MemberDTO)request.getAttribute("member");
	teg=Integer.parseInt(request.getParameter("teg"));
	if(pro_price==null) pro_price="0";
	
%>

<%@ include file="/header.jsp" %> 

	
	<div class="container text-center">
	
		 <div class="align-self-center">
	       <span class="logo">구매[ pay ]</span>
	     	<br>
	     	<table border="1" class="table  align-middle">
				<thead>
				  <tr>
				    <th>#</th>
		            <th>이미지</th>
		            <th>상품제목</th>
		            <th>수량</th>
		            <th>가격</th>
		         </tr>
		      </thead>
		      <tbody>
	      	<% 
	      		int totalSum = 0, total = 0, cnt=0;
	      		if(teg==1){
	      			total=Integer.parseInt(request.getParameter("pro_price"))*Integer.parseInt(request.getParameter("pro_cnt"));
	      			totalSum += total;
	      	%>
					<td></td>
					<td><img src="<%=request.getParameter("pro_img")%>" width="100"></td>
		            <td><%=request.getParameter("pro_name")%></td>
		            <td><%=request.getParameter("pro_cnt")%></td>
		            <td>
		           		<c:set var="PX" value="<%=pro_price%>" /> <fmt:formatNumber value="${PX}" pattern="#,### 원"/>
		            </td>
			<%} else {
					
					for(int i = 0; i < cart.size(); i++) {
					
						CartDTO dto = cart.get(i);
						total = dto.getPro_price() * dto.getPro_cnt();
						cnt +=dto.getPro_cnt();
			%>
							<tr>
							<td></td>
							<td><img src="<%=dto.getPro_img()%>" width="100"></td>
				            <td><%=dto.getPro_name()%></td>
				            <td><%=dto.getPro_cnt()%></td>
				            <td><c:set var="PX" value="<%=dto.getPro_price() %>" /> <fmt:formatNumber value="${PX}" pattern="#,### 원"/></td>
				            </tr>         
						<%
							totalSum += total;
						}%> 		
				<%}%><p></p>
				</tbody>
			</table>
	     </div>
	     <hr>
	     <table class="table  align-middle" >
				<tbody>
					<tr>
						<td>
							<span class="logo">구매자정보[ pay ]</span>
					            <div class="form-floating mb-3 col-md-6 offset-md-3">
					                <input type="text" class="form-control pw" name="mem_name" id="mem_name" value="<%=member.getMem_name()%>" placeholder="mem_name">
					                <label for="mem_name">이름</label>
					            </div> 
					            <div class="form-floating mb-3 col-md-6 offset-md-3">
					                <input type="text" class="form-control" name="mem_phone" id="mem_phone" value="<%=member.getMem_phone()%>" placeholder="mem_phone" oninput="autoHyphen(this)" maxlength="12">
					                <label for="mem_phone">연락처</label>
					            </div>
					            <input type="hidden" name="mem_name1" id="mem_name1" value="<%=member.getMem_name()%>">
					            <input type="hidden" name="mem_phone1" id="mem_phone1" value="<%=member.getMem_phone()%>">
					            <input type="hidden" name="mem_zcode" id="mem_zcode" value="<%=member.getMem_zcode()%>">
					            <input type="hidden" name="mem_add" id="mem_add" value="<%=member.getMem_add()%>">
					            <input type="hidden" name="mem_add2" id="mem_add2" value="<%=member.getMem_add2()%>">
						</td>
						<td>
							<span class="logo">주문 요약[ pay ]</span>
						    <p>상품가격 <c:set var="PX" value="<%=totalSum%>" /> <fmt:formatNumber value="${PX}" pattern="#,### 원"/></p>
						    <p>배송비 무료</p>
						    <p id="totalSum">총 주문 금액 <c:set var="PX" value="<%=totalSum%>" /> <fmt:formatNumber value="${PX}" pattern="#,### 원"/></p>
						</td>
					</tr>
					<tr>
						<td>
							 <span class="logo">배송지[ pay ]</span>
							 <div class="option">
		                        <input type="checkbox" id="infoOption" name="infoOption">
		                        <label for="infoOption" class="optionLabel">주문 고객 정보와 동일</label>
		                    </div>
				            <div class="form-floating mb-3 col-md-6 offset-md-3">
				                <input type="text" class="form-control pw" name="ord_name" id="ord_name" placeholder="ord_name">
				                <label for="ord_name">수령인</label>
				            </div>
				            <div class="form-floating mb-3 col-md-6 offset-md-3">
				                <input type="text" class="form-control" name="ord_phone" id="ord_phone" placeholder="ord_phone" oninput="autoHyphen(this)" maxlength="12">
				                <label for="ord_phone">연락처</label>
				     			
				            </div>
			
				            <div class="form-floating mb-3 col-md-6 offset-md-3">
				                <input type="text" class="form-control" name="ord_zcode" id="ord_zcode" placeholder="ord_zcode" onclick="findAddr2()">
				                <label for="ord_zcode">우편번호</label> 
				            </div>
				            <div class="form-floating mb-3 col-md-6 offset-md-3">
				                <input type="text" class="form-control" name="ord_add" id="ord_add" placeholder="ord_add">
				                <label for="ord_add">주소</label>
				            </div>
				            <div class="form-floating mb-3 col-md-6 offset-md-3">
				                <input type="text" class="form-control" name="ord_add2"  id="ord_add2" placeholder="ord_add2">
				                <label for="ord_add2">상세주소</label>
				            </div>
				            
				            
				            <div class="form-floating mb-3 col-md-6 offset-md-3">
				            	배송메모
				                <select id='payoption'>
								  <option selected  value='없음'>배송메모를 선택해주세요</option>
								  <option name="payoption" value='배송전 미리 연락해주세요</'>배송전 미리 연락해주세요</option>
								  <option value='부재시 경비실에 맡겨주세요'>부재시 경비실에 맡겨주세요</option>
								  <option value='부재시 전화나 문자를 남겨주세요'>부재시 전화나 문자를 남겨주세요</option>
								</select>
				            </div>
						</td>
						<td>
							<span class="logo">결제방식[ pay ]</span>
							<div class="form-check mb-3 col-md-6 offset-md-3">
							  <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
							  <label class="form-check-label" for="flexCheckDefault">
							    무통장입금
							 </label>
							 <div class="form-floating mb-3 col-md-12">
					                <input type="text" class="form-control" name="mem_add2"  id="mem_add2" placeholder="mem_add2">
					                <label for="mem_add2">입급자 명</label>
					            </div>
							</div>
						    <button onclick="paychk()">결제</button>
						</td>
					</tr>
				</tbody>
		</table>
	   
</body>

<script>

	function paychk() {
		var payoption  = document.getElementById("payoption");
		var option = (payoption.options[payoption.selectedIndex].value);
		
		var ord_name = $('input[name=ord_name]').val();
		var ord_phone = $('input[name=ord_phone]').val();
		var ord_zcode = $('input[name=ord_zcode]').val();
		var ord_add = $('input[name=ord_add]').val();
		var ord_add2 = $('input[name=ord_add2]').val();
		
		if(<%=teg%>==1){
			location.href = "./PayController.pay?actionType=pay&mem_id=<%=session.getAttribute("userid")%>&ord_name="+ord_name+"&ord_phone="+ord_phone+
			"&ord_zcode="+ord_zcode+"&ord_add="+ord_add+"&ord_add2="+ord_add2+"&ord_content="+option+"&ord_price=<%=totalSum%>&pro_img=<%=request.getParameter("pro_img")%>&pro_name=<%=request.getParameter("pro_name")%>&pro_cnt=<%=request.getParameter("pro_cnt")%>&teg=1";
		}
		else{
			location.href = "./PayController.pay?actionType=pay&teg=2&mem_id=<%=session.getAttribute("userid")%>&ord_name="+ord_name+"&ord_phone="+ord_phone+"&ord_zcode="+ord_zcode+"&ord_add="+ord_add+"&ord_add2="+ord_add2+"&ord_content="+option+"&ord_price=<%=totalSum%>";
		}
		
	

	}
	function pay() {
		location.href = "./PayController.pay";
	}
    
    var infoOption = document.querySelector("#infoOption");

    infoOption.addEventListener("click", function() { //checkbox에 이벤트 리스너 등록
        if(infoOption.checked == true) { //체크된 경우 -> 주문고객 정보에 입력한 정보를 배송지 정보 input에 값 넣기

        	var name = document.querySelector("#mem_name1").value;  //입력한 주문자명
            var phoneNumber = document.querySelector("#mem_phone1").value; //입력한 주문고객 연락처
            var zcode = document.querySelector("#mem_zcode").value;  
            var add = document.querySelector("#mem_add").value;
            var add2 = document.querySelector("#mem_add2").value;  

            document.querySelector("#ord_name").value = name;
            document.querySelector("#ord_phone").value = phoneNumber;
            document.querySelector("#ord_zcode").value = zcode;
            document.querySelector("#ord_add").value = add;
            document.querySelector("#ord_add2").value = add2;
        }
        else { //체크해제한 경우 -> 배송지 정보를 비워주기
        	document.querySelector("#ord_name").value = "";
            document.querySelector("#ord_phone").value = "";
            document.querySelector("#ord_zcode").value = "";
            document.querySelector("#ord_add").value = "";
            document.querySelector("#ord_add2").value = "";

        }
    });

</script>
</html>