<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/globalData.jsp" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Plant [·]</title>
</head>

    <!--  -->
    

	<link rel="stylesheet" href="<%=contDir%>/CSS/aos.css">

	<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script><link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>

	<link rel="stylesheet" href="<%=contDir%>/CSS/plant.css">
	
<body>
	<header>
		<%
		   if( session.isNew()){
		      session.setAttribute("loginState", "logout");
		      session.setAttribute("userid", null);
		      session.setAttribute("userpw", null);
		   }
		%>
		<div class="py-1" style="background: #82AE46;">
	    	<div class="container" style="background-color: #82AE46;">
	    		<div class="row no-gutters d-flex align-items-start align-items-center px-md-0">
		    		<div class="col-lg-12 d-block">
			    		<div class="row d-flex">
			    			<div class="col-md-4 pr-3 d-flex topper align-items-center">
							    <span class="text">1901122 박준홍</span>
						    </div>
						    <div class="col-md pr-4 d-flex topper align-items-center">
							    <span class="text">hongboy15@gmail.com</span>
						    </div>
						    <div class="col-md-2 pr-4 d-flex topper align-items-center text-lg-right">
							    <span class="text">2WD - A</span>

						    </div>
					    </div>
				    </div>
			    </div>
			  </div>
	    </div>
	    
		<nav class="navbar navbar-expand-lg lg">
		    <div class="container">
		      <a class="navbar-brand logo" href="<%= rootDir %>/main.jsp">Plant[ · ]</a>
		     		  
		       <ul class="nav">
				  <li class="nav-item">
				    <a class="nav-link active boder" aria-current="page" href="#">Tip 게시판</a>
				  </li>
				  <li class="nav-item">
				    <a class="nav-link boder" href="./ProductController.pro?actionType=pro_mlist&pro_cg=0">쇼핑몰</a>
				  </li>
				  <li class="nav-item">
				    <a class="nav-link boder" href="#">딸기마켓</a>
				  </li>
				</ul>
				
				<ul class="nav justify-content-end">
				  <li class="nav-item">
				    <a class="nav-link active end" aria-current="page" href="<%=cartViewDir%>/cart_list.jsp">장바구니</a>
				  </li>
				  <li class="nav-item">
				  <%if(session.getAttribute("loginState").equals("login")){%>  
					  <li class="nav-item dropdown">
			          <a class="nav-link dropdown-toggle end" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
			            <%=session.getAttribute("userid")%> 님
			          </a>
			          	<%if(session.getAttribute("userid").equals("admin")){ %>
				          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
				            <li><a class="dropdown-item" href="./MemberController.be?actionType=mem_RL">회원정보</a></li>
				            <li><a class="dropdown-item" href="<%=productViewDir%>/product_insert.jsp">상품등록</a></li>
				            <li><a class="dropdown-item" href="./ProductController.pro?actionType=pro_List">상품조회</a></li>
				            <li><hr class="dropdown-divider"></li>
				            <li><a class="dropdown-item" href="./MemberController.be?actionType=logout">로그아웃</a></li>
				          </ul>
			          	  <%}else{ %>
			        		<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
<%-- 				            <li><a class="dropdown-item" href="./MemberController.be?actionType=mem_R&mem_id=<%=session.getAttribute("userid")%>">내정보</a></li> --%>
				            <li><a class="dropdown-item" href="./MemberController.be?actionType=mem_my&mem_id=<%=session.getAttribute("userid")%>&teg=0">내정보</a></li>
				            <li><hr class="dropdown-divider"></li>
				            <li><a class="dropdown-item" href="./MemberController.be?actionType=logout">로그아웃</a></li>
				          </ul>
			        	<%}%>
			        </li>
				  <%}else { %> 
				  	  <a class="nav-link end" href="#"><button class="lobtn" data-bs-toggle="modal" data-bs-target="#loginModal" >로그인</button></a>
				   <%}%>
				  </li>
				</ul>
		     </div>
		 </nav>


	</header>
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
	
	
<script src="<%=contDir%>/JS/jquery-3.4.1.min.js"></script>
<script src="<%=contDir%>/JS/typed.js"></script>
  
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
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
	location.href = "./MemberController.be?actionType=login&teg=0&mem_id=" + mem_id + "&mem_pwd="+mem_pwd;
}

function join() {
	var mem_id = $('input[name=member_id]').val();
	var mem_pwd = $('input[name=member_pw]').val();
	var mem_name = $('input[name=mem_name]').val();
	var mem_nickname = $('input[name=mem_nickname]').val();
	var mem_email = $('input[name=mem_email]').val();
	var mem_phone = $('input[name=mem_phone]').val();
	
	location.href = "./MemberController.be?actionType=join2&teg=3&mem_id=" + mem_id + "&mem_pwd="+mem_pwd+
			"&mem_name="+mem_name+"&mem_nickname="+mem_nickname+"&mem_email="+mem_email+"&mem_phone="+mem_phone;
}

function setdate(pro_name,pro_cnt) {
	
	document.querySelector("#pro_cnt").value = pro_cnt;
    document.querySelector("#pro_name").value = pro_name;

}

const autoHyphen = (target) => {
    target.value = target.value
        .replace(/[^0-9]/, '')
        .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
}

function findAddr(){
    new daum.Postcode({
        oncomplete: function(data) {

            console.log(data);
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var jibunAddr = data.jibunAddress; // 지번 주소 변수
            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('mem_zcode').value = data.zonecode;
            if(roadAddr !== ''){
                document.getElementById("mem_add").value = roadAddr;
            }
            else if(jibunAddr !== ''){
                document.getElementById("mem_add").value = jibunAddr;
            }
        }
    }).open();
}


function findAddr2(){
    new daum.Postcode({
        oncomplete: function(data) {

            console.log(data);
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var jibunAddr = data.jibunAddress; // 지번 주소 변수
            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('ord_zcode').value = data.zonecode;
            if(roadAddr !== ''){
                document.getElementById("ord_add").value = roadAddr;
            }
            else if(jibunAddr !== ''){
                document.getElementById("mem_add").value = jibunAddr;
            }
        }
    }).open();
}

</script>
  


</body>
</html>