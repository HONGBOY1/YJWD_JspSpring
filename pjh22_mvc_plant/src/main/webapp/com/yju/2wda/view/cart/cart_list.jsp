<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
	
	<%@ page import="pjh22_mvc_plant.model.product.*"%>
	<%@ page import="pjh22_mvc_plant.model.cart.*"%>
   <%@ page import="java.util.*"%>
   
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>plant[cart]</title>
</head>
<body>

<%
	request.setCharacterEncoding("utf-8");
	ArrayList<CartDTO> cart = null;
	
	Object obj = session.getAttribute("cart");	//세션 객체에서 cart 값을 가져온다.
	
	if(obj == null) {	//세션 정보가 없으면 배열을 생성 : 주문한 제품이 없다
		cart = new ArrayList<CartDTO>();	
	} else {			//세션 정보가 있으면 강제로 캐스팅 : 주문한 제품이 있다
		cart = (ArrayList<CartDTO>) obj;
	}
	
	if( session.isNew()){
	      session.setAttribute("loginState", "logout");
	      session.setAttribute("userid", null);
	      session.setAttribute("userpw", null);
	}
%>

<%@ include file="/header.jsp" %> 


	<div class="container text-center">
	
		 <div class="align-self-center">
	       <span class="logo">장바구니[ cart ]</span>
	     	<br>
	     	<table border="1" class="table  align-middle" >
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
				if(cart.size() == 0) {
			%>
					<tr>
						<td colspan="5">장바구니에 담긴 상품이 없습니다.</td>
					</tr>
				</tbody>
				</table>
			<%			
				} else {
					int totalSum = 0, total = 0;
					for(int i = 0; i < cart.size(); i++) {
					
						CartDTO dto = cart.get(i);
						total = dto.getPro_price() * dto.getPro_cnt();
			%>
						<tr>
							<td></td>
							<td><img src="<%=dto.getPro_img()%>" width="100"></td>
				            <td><%=dto.getPro_name()%></td>
				            <td name="inputValue"><%=dto.getPro_cnt()%> 개 <br>
							<button type="button" class="cartbtn" data-bs-toggle="modal" data-bs-target="#exampleModal" 
							onclick="setdate('<%=dto.getPro_name()%>','<%=dto.getPro_cnt()%>')">
						 	 수량변경
							</button>				            
				            </td>
				            <td><c:set var="PX" value="<%= dto.getPro_price()%>" /> <fmt:formatNumber value="${PX}" pattern="#,### 원"/></td>
				         </tr>			         
					<%totalSum += total;} %> 
					</tbody>
				</table>
			<p style="font-size: 25px">총 주문금액 =  <c:set var="PX" value="<%=totalSum%>" /> <fmt:formatNumber value="${PX}" pattern="#,### 원"/></p><%}%>
	     </div>
	     <div>
		 <% if(session.getAttribute("loginState").equals("login")){%> 
			<a href="./PayController.pay?actionType=list&mem_id=<%=session.getAttribute("userid")%>"><button class="purchase">결제하기</button></a>
		<%}else {%>
			<button class="purchase" data-bs-toggle="modal" data-bs-target="#loginModal" >결제하기</button>
		<%}%>	
			<a href="./CartController.cart?actionType=clear"><button class="basket" >장바구니삭제</button></a>
	
	</div>
	</div>
	


	 <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">수량변경</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body ">
	        <input class="form-control" name="pro_cnt" id="pro_cnt" type="text">
	      </div>
	      <div class="modal-footer">
			 <button type="button" class="purchase"  data-bs-dismiss="modal"  id="pro_name"
	        onclick="updateCnt()">변경</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<div class="modal fade" id="loginModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered">
	    <div class="modal-content">
	      <div class="modal-header text-center">
	        <h5 class="modal-title" id="exampleModalLabel">로그인</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
		       <div class="form-floating mb-3 col-md-12">
		          <input name="mem_id" type="text" class="form-control align-self-center"  id="mem_id" placeholder="mem_id">
		          <label for="mem_id">아이디</label>
		      </div>
		      <div class="form-floating mb-3 col-md-12">
		          <input name="mem_pwd" type="password" class="form-control" id="mem_pwd" placeholder="mem_pwd">
		          <label for="mem_pwd">패스워드</label>
		      </div>
		      
		      <a type="button" data-bs-toggle="modal" data-bs-target="#joinModal" data-bs-dismiss="modal">회원가입</a>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
	        <button type="button" class="btn btn-primary"  data-bs-dismiss="modal"
	        onclick="login()">로그인</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	
	<div class="modal fade text-center" id="joinModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">회원가입</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
		    <div class="form-floating mb-3 col-md-12">
                <input type="text" class="form-control align-self-center" name="member_id" id="member_id" placeholder="id">
                <label for="member_id">아이디</label>
            </div>
                    
            <div class="form-floating mb-3 col-md-12">
                <input type="password" class="form-control pw" name="member_pw" id="member_pw" placeholder="member_pw">
                <label for="member_pw">패스워드</label>
            </div>
            <div class="form-floating mb-3 col-md-12">
                <input type="password" class="form-control pw" name="mem_pwdchk" id="mem_pwdchk" placeholder="mem_pwdchk">
                <label for="mem_pwdchk">패스워드확인</label>
                <span id="alert-success" style="display: none; color: #008F26FF; font-weight: bold" >비밀번호가 일치합니다.</span>
    			<span id="alert-danger" style="display: none; color: #d92742; font-weight: bold; ">비밀번호가 일치하지 않습니다.</span>
            </div>
            <div class="form-floating mb-3 col-md-12">
                <input type="text" class="form-control " name="mem_name" id="mem_name" placeholder="mem_name">
                <label for="mem_name">이름</label>
            </div>
            <div class="form-floating mb-3 col-md-12">
                <input type="text" class="form-control " name="mem_nickname" id="mem_nickname" placeholder="mem_nickname">
                <label for="mem_nickname">닉네임</label>
            </div>
            <div class="form-floating mb-3 col-md-12">
                <input type="email" class="form-control"name="mem_email"  id="mem_email" placeholder="mem_email">
                <label for="mem_email">이메일</label>
            </div>
            <div class="form-floating mb-3 col-md-12">
                <input type="text" class="form-control" name="mem_phone" id="mem_phone" placeholder="mem_phone" oninput="autoHyphen(this)" maxlength="12">
                <label for="mem_phone">전화번호</label>
            </div>
            
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#loginModal"  data-bs-dismiss="modal">취소</button>
	        <button type="button" class="btn btn-primary"  data-bs-dismiss="modal"
	        onclick="join()">회원가입</button>
	      </div>
	    </div>
	  </div>
	</div>					
</body>


<script type="text/javascript">

$('.pw').focusout(function () {
    var pwd1 = $("#member_pw").val();
    var pwd2 = $("#mem_pwdchk").val();

    if ( pwd1 != '' && pwd2 == '' ) {
        null;
    } else if (pwd1 != "" || pwd2 != "") {
        if (pwd1 == pwd2) {
            $("#alert-success").css('display', 'inline-block');
            $("#alert-danger").css('display', 'none');
        } else {
            $("#alert-success").css('display', 'none');
            $("#alert-danger").css('display', 'inline-block');
        }
    }
});

$('#button-addon2').click(function () {
		
    if ($('#member_id').val() != '') {
   				
        // 아이디를 서버로 전송 > DB 유효성 검사 > 결과 반환받기
        $.ajax({
   					
            type: 'GET',
            url: '/ajax/login/idcheck.action',
            data: 'id=' + $('#member_id').val(),
            dataType: 'json',
            success: function(result) {
                if (result == '0') {
                    $('#result').text('사용 가능한 아이디입니다.');
                } else {
                    $('#result').text('이미 사용중인 아이디입니다.');
                }
            },
            error: function(a, b, c) {
                console.log(a, b, c);
            }
   					
        });
   				
    } else {
        alert('아이디를 입력하세요.');
        $('#txtid').focus();
    }
   			
});


function updateCnt(pro_name) {
	var pro_cnt = $('input[name=pro_cnt]').val();
	var pro_name = document.getElementById("pro_name").value;
	location.href = "./CartController.cart?actionType=update&pro_name=" + pro_name + "&pro_cnt="+pro_cnt;
}

function login() {
	var mem_id = $('input[name=mem_id]').val();
	var mem_pwd = $('input[name=mem_pwd]').val();
	location.href = "./MemberController.be?actionType=login&teg=1&mem_id=" + mem_id + "&mem_pwd="+mem_pwd;
}

function join() {
	var mem_id = $('input[name=member_id]').val();
	var mem_pwd = $('input[name=member_pw]').val();
	var mem_name = $('input[name=mem_name]').val();
	var mem_nickname = $('input[name=mem_nickname]').val();
	var mem_email = $('input[name=mem_email]').val();
	var mem_phone = $('input[name=mem_phone]').val();
	
	location.href = "./MemberController.be?actionType=join2&teg=1&mem_id=" + mem_id + "&mem_pwd="+mem_pwd+
			"&mem_name="+mem_name+"&mem_nickname="+mem_nickname+"&mem_email="+mem_email+"&mem_phone="+mem_phone;
}

function setdate(pro_name,pro_cnt) {
	
	document.querySelector("#pro_cnt").value = pro_cnt;
    document.querySelector("#pro_name").value = pro_name;

}


</script>

</html>