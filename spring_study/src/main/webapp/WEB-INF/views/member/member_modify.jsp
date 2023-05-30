<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
   <%@ page import="pjh22_mvc_plant.model.member.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 조회[ select ]</title>
</head>
<body>


<%@ include file="../header.jsp" %> 

	<div class="container text-center">
            <div class="align-self-center">
               	<span class="logo">회원 조회[ select ]</span>
            </div>
            <br>
           <%
          	  MemberDTO member=(MemberDTO)request.getAttribute("member");
		   %>
		  	<form action="./MemberController.be">
            <div class="form-floating mb-3 col-md-6 offset-md-3">
                <input type="text" class="form-control align-self-center" name="mem_id" value="<%=member.getMem_id()%>" id="mem_id" placeholder="id" >
                <label for="mem_id">아이디</label>
            </div>
            <div class="form-floating mb-3 col-md-6 offset-md-3">
                <input type="text" class="form-control pw" name="mem_pwd" id="mem_pwd" value="<%=member.getMem_pwd()%>" placeholder="mem_pwd">
                <label for="mem_pwd">패스워드</label>
            </div>
            <div class="form-floating mb-3 col-md-6 offset-md-3">
                <input type="text" class="form-control pw" name="mem_name" id="mem_name" value="<%=member.getMem_name()%>" placeholder="mem_name">
                <label for="mem_name">이름</label>
            </div>
            <div class="form-floating mb-3 col-md-6 offset-md-3">
                <input type="text" class="form-control pw" name="mem_nickname" id="mem_nickname" value="<%=member.getMem_nickname()%>" placeholder="mem_nickname">
                <label for="mem_nickname">닉네임</label>
            </div>
            <div class="form-floating mb-3 col-md-6 offset-md-3">
                <input type="text" class="form-control pw"name="mem_level"  id="mem_level" value="<%=member.getMem_level()%>" placeholder="mem_level">
                <label for="mem_level">레벨</label>
            </div>
            <div class="form-floating mb-3 col-md-6 offset-md-3">
                <input type="email" class="form-control pw"name="mem_email"  id="mem_email" value="<%=member.getMem_email()%>" placeholder="mem_email">
                <label for="mem_email">이메일</label>
            </div>
            <div class="form-floating mb-3 col-md-6 offset-md-3">
                <input type="text" class="form-control" name="mem_phone" id="mem_phone" placeholder="mem_phone" value="<%=member.getMem_phone()%>" oninput="autoHyphen(this)" maxlength="12">
                <label for="mem_phone">전화번호</label>
            </div>
            <div class="form-floating mb-3 col-md-6 offset-md-3">
                <input type="text" class="form-control" name="mem_zcode" id="mem_zcode" placeholder="mem_zcode" value="<%=member.getMem_zcode()%>" onclick="findAddr()">
                <label for="mem_zcode">우편번호</label>
            </div>
            <div class="form-floating mb-3 col-md-6 offset-md-3">
                <input type="text" class="form-control" name="mem_add" id="mem_add" value="<%=member.getMem_add()%>" placeholder="mem_add">
                <label for="mem_add">주소</label>
            </div>
            <div class="form-floating mb-3 col-md-6 offset-md-3">
                <input type="text" class="form-control" name="mem_add2"  id="mem_add2" value="<%=member.getMem_add2()%>" placeholder="mem_add2">
                <label for="mem_add2">상세주소</label>
            </div>
            <div class="form-floating mb-3 col-md-6 offset-md-3">
                <input type="text" class="form-control" name="mem_date"  id="mem_date" value="<%=member.getMem_date()%>"placeholder="mem_date">
                <label for="mem_date">가입날짜</label>
            </div>
            
            <div class="row">
                <div>
               		<input type="hidden" name="actionType" value="mem_U">
	               <button type="submit" class="btn btn-outline-warning btn-lg">회원수정</button>
	        	</div>
            </div>
            </form>
           	<a href="./MemberController.be?actionType=mem_D&mem_id=<%=member.getMem_id()%>">
                <button type="button" class="btn btn-outline-danger btn-lg">회원탈퇴</button>
              </a>
            
     </div>
</body>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
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

</script>
</html>