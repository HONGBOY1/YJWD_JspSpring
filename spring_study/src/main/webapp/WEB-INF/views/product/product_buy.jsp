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
<title>plant[buy]</title>
</head>
<style>
.offcanvas h5{
		font-weight: 1000;
}
.offcanvas p{
		font-size : 15px;
		font-weight: 1000;
}
</style>
<body>

<%@ include file="../header.jsp" %> 
	<%
          ProductDTO product=(ProductDTO)request.getAttribute("product");

	%>

     <div class="container">
     		<div class="align-self-center">
               	<span class="logo">구매페이지[ buy ]</span>
            </div>
     
         <div>
             <div class="row">
                 <div class="col-md-6 md-1">
                     <figure>
                         <img id="pro_img" class="product" src="<%=productImage%><%=product.getPro_img()%>" width="500" >
                     </figure>
                 </div>
                 <div class="col-md-5 description">
                     <hr class="hr1">
                     <h3 id="pro_name"><%=product.getPro_name()%></h3>
                     <hr>
                     <p id="pro_content"><%=product.getPro_content()%></p>
                     <hr>
                      <%if(product.getPro_cg()==1){ %>
              
                     	 <%for(int i=0; i < product.getPro_level(); i++ ){ %>
                     	 		<img src="<%=productImage%>/level.png" width="30" >
	             		<%} %>
	             		<button style="float: right;" class="lewat" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasScrolling" aria-controls="offcanvasScrolling"><i class="bi bi-star"></i></button>

	                     <hr>
	                     <%for(int i=0; i < product.getPro_water(); i++ ){ %>
                     	 	<img  src="<%=productImage%>/water.png" width="30" >
	             		<%} %>
							 <button style="float: right;" class="lewat" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasScrolling1" aria-controls="offcanvasScrolling1"><i class="bi bi-sun-fill"></i></button>						          
	                     <hr>
                     <%} %>
                     <table>
                       <td>
                            <span class="quantity">
                              수량 <input id="quantity" name="pro_cnt"  value ="1" type="text">
                            </span>
					   </td>
                     </table>
                   	 <p id="pro_price"> <c:set var="PX" value="<%=product.getPro_price()%>" /> <fmt:formatNumber value="${PX}" pattern="#,### 원"/></p>
                     <hr>
                     <% if(session.getAttribute("loginState").equals("login")){%> 
						 <button class="purchase" onclick="one('<%=product.getPro_num()%>','<%=productImage%><%=product.getPro_img()%>','<%=product.getPro_name()%>',
                     '<%=product.getPro_content()%>','<%=product.getPro_price()%>')">구매하기</button>
					<%}else {%>
						<button class="purchase" data-bs-toggle="modal" data-bs-target="#loginModal" >구매하기</button>
					<%}%>
                     <button class="basket" onclick="saveCart('<%=product.getPro_num()%>','<%=productImage%><%=product.getPro_img()%>','<%=product.getPro_name()%>',
                     '<%=product.getPro_content()%>','<%=product.getPro_price()%>')">장바구니</button>
                 </div>
             </div>
             <hr>
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
                <input type="text" class="form-control pw" name="member_pw" id="member_pw" placeholder="member_pw">
                <label for="member_pw">패스워드</label>
            </div>
            <div class="form-floating mb-3 col-md-12">
                <input type="text" class="form-control pw" name="mem_pwdchk" id="mem_pwdchk" placeholder="mem_pwdchk">
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
	
	
	
	<div class="offcanvas offcanvas-end" data-bs-scroll="true" data-bs-backdrop="false" tabindex="-1" id="offcanvasScrolling1" aria-labelledby="offcanvasScrollingLabel1">
						  <div class="offcanvas-header">
						    <h5 class="offcanvas-title" id="offcanvasScrollingLabel1">식물 물주기</h5>
						    <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
						  </div>
						  
						  <div class="offcanvas-body">
						    <p> 
						   <figure>
									<p>1주일에 한번 주세요</p>
							     	<img src="<%=productImage%>/water.png" width="50">
								</figure>
								<figure>
									<p>화분의 겉흙이 말라 있을때 주세요</p>
							     	<img src="<%=productImage%>/water.png" width="50">
							     	<img src="<%=productImage%>/water.png" width="50">
								</figure>
								<figure>
								<p>겉흙이 마르고 2~3일 후에 주세요</p>
							     	<img src="<%=productImage%>/water.png" width="50">
							     	<img src="<%=productImage%>/water.png" width="50">
							     	<img src="<%=productImage%>/water.png" width="50">
								</figure>
								<figure>
								<p>항상 물이 마르지 않도록 물을 충분하게 주세요.</p>
							     	<img src="<%=productImage%>/water.png" width="50">
							     	<img src="<%=productImage%>/water.png" width="50">
							     	<img src="<%=productImage%>/water.png" width="50">
							     	<img src="<%=productImage%>/water.png" width="50">
								</figure>
								<figure>
								<p>매일 매일 하루씩 물을 충분하게 주세요</p>
							     	<img src="<%=productImage%>/water.png" width="50">
							     	<img src="<%=productImage%>/water.png" width="50">
							     	<img src="<%=productImage%>/water.png" width="50">
							     	<img src="<%=productImage%>/water.png" width="50">
							     	<img src="<%=productImage%>/water.png" width="50">
								</figure>
							</p>
						  </div>
						</div>
						
						<div class="offcanvas offcanvas-end" data-bs-scroll="true" data-bs-backdrop="false" tabindex="-1" id="offcanvasScrolling" aria-labelledby="offcanvasScrollingLabel">
						  <div class="offcanvas-header">
						    <h5 class="offcanvas-title" id="offcanvasScrollingLabel">식물 난이도</h5>
						    <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
						  </div>
						  <div class="offcanvas-body">
						    <p> 
						    <figure>
								  <p>누구나 키워볼수 있는 단계</p>
							      <img src="<%=productImage%>/level.png" width="50" >
								</figure>
								<figure>
								 <p>조금 관심이 필요한 단계</p>
							      <img src="<%=productImage%>/level.png" width="50" >
							      <img src="<%=productImage%>/level.png" width="50" >
								</figure>
								<figure>
								 <p>관심이 필요한 단계</p>
							      <img src="<%=productImage%>/level.png" width="50" >
							      <img src="<%=productImage%>/level.png" width="50" >
							      <img src="<%=productImage%>/level.png" width="50" >
								</figure>
								<figure>
								<p>관심이 많이 필요한 단계</p>
							      <img src="<%=productImage%>/level.png" width="50" >
							      <img src="<%=productImage%>/level.png" width="50" >
							      <img src="<%=productImage%>/level.png" width="50" >
							      <img src="<%=productImage%>/level.png" width="50" >
								</figure>
								<figure>
								<p>관심이 매일 필요한 단계</p>
							      <img src="<%=productImage%>/level.png" width="50" >
							      <img src="<%=productImage%>/level.png" width="50" >
							      <img src="<%=productImage%>/level.png" width="50" >
							      <img src="<%=productImage%>/level.png" width="50" >
							      <img src="<%=productImage%>/level.png" width="50" >
								</figure>
							</p>
						  </div>
						</div>		
</body>	
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">


function saveCart(pro_num,pro_img,pro_name,pro_content,pro_price) {
	var pro_cnt = $('input[name=pro_cnt]').val()
	location.href = "./CartController.cart?actionType=list&pro_num="+pro_num+"&pro_img=" + pro_img + "&pro_name=" + pro_name + "&pro_content="+ pro_content+"&pro_cnt="+pro_cnt+"&pro_price="+pro_price;
	
}
function one(pro_num,pro_img,pro_name,pro_content,pro_price) {
	var pro_cnt = $('input[name=pro_cnt]').val()
	location.href = "./PayController.pay?actionType=one&pro_img="+pro_img+"&pro_name="+pro_name+"&pro_price="+pro_price+"&pro_cnt="+pro_cnt+"&teg=1&mem_id=<%=session.getAttribute("userid")%>";
    
}



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
	location.href = "./MemberController.be?actionType=login&teg=2&mem_id=" + mem_id + "&mem_pwd="+mem_pwd+"&pro_num=<%=product.getPro_num()%>";
}

function join() {
	var mem_id = $('input[name=member_id]').val();
	var mem_pwd = $('input[name=member_pw]').val();
	var mem_name = $('input[name=mem_name]').val();
	var mem_nickname = $('input[name=mem_nickname]').val();
	var mem_email = $('input[name=mem_email]').val();
	var mem_phone = $('input[name=mem_phone]').val();
	
	location.href = "./MemberController.be?actionType=join2&teg=2&mem_id=" + mem_id + "&mem_pwd="+mem_pwd+
			"&mem_name="+mem_name+"&mem_nickname="+mem_nickname+"&mem_email="+mem_email+"&mem_phone="+mem_phone+"&pro_num=<%=product.getPro_num()%>";
}

function setdate(pro_name,pro_cnt) {
	
	document.querySelector("#pro_cnt").value = pro_cnt;
    document.querySelector("#pro_name").value = pro_name;

}
</script>




</html>