<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>plant[loing]</title>

	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
	

</head>
<body>
	<%@ include file="/header.jsp" %> 
<div class="container text-center">
      <div class="align-self-center">
          <span class="logo" >로그인[ login ]</span>
      </div>
       <br>
       <form method="post" action="./MemberController.be?teg=0">
      
	      <div class="form-floating mb-3 col-md-6 offset-md-3">
	          <input name="mem_id" type="text" class="form-control align-self-center"  id="mem_id" placeholder="mem_id">
	          <label for="mem_id">아이디</label>
	      </div>
	      <div class="form-floating mb-3 col-md-6 offset-md-3">
	          <input name="mem_pwd" type="password" class="form-control" id="mem_pwd" placeholder="mem_pwd">
	          <label for="mem_pwd">패스워드</label>
	      </div>
	
	      <div class="row">
	          <div>
	          	  <input type="hidden" name="actionType" value="login">
	              <button type="submit" class="btn btn-outline-success btn-lg">로그인</button>  
	          </div>
	    </div>
	     </form>
	  
	   
 </div>
</body>

</html>