<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>plant[join]</title>
</head>
<body>


<%@ include file="../header.jsp" %> 

	<div class="container text-center">
	 <form action="./ProdcutController.pro" enctype="multipart/form-data" method="post">
            <div class="align-self-center">
               	<span class="logo">상품등록[ insert ]</span>
            </div>
            <br>
            
            <div class="form-floating mb-3 col-md-6 offset-md-3">
                <input type="text" class="form-control align-self-center" name="pro_cg" id="pro_cg" placeholder="pro_cg">
                <label for="pro_cg">카테고리(1,2,3,4,5)</label>
            </div>
            <div class="form-floating mb-3 col-md-6 offset-md-3">
                <input type="text" class="form-control pw" name="pro_name" id="pro_name" placeholder="pro_name">
                <label for="pro_name">상품제목</label>
            </div>
            <div class="form-floating mb-3 col-md-6 offset-md-3">
                <input type="text" class="form-control pw" name="pro_content" id="pro_content" placeholder="pro_content">
                <label for="pro_content">상품설명</label>
            </div>
            <div class="form-floating mb-3 col-md-6 offset-md-3">
                <input type="text" class="form-control pw" name="pro_cnt" id="pro_cnt" placeholder="pro_cnt">
                <label for="pro_cnt">상품수량</label>
            </div>
            <div class="form-floating mb-3 col-md-6 offset-md-3">
                <input type="text" class="form-control pw" name="pro_price"  id="pro_price" placeholder="pro_price">
                <label for="pro_price">상품가격</label>
            </div>
            <div class="form-floating mb-3 col-md-6 offset-md-3">
                <input type="text" class="form-control pw" name="pro_level"  id="pro_level" placeholder="pro_price">
                <label for="pro_level">상품난이도</label>
            </div>
            <div class="form-floating mb-3 col-md-6 offset-md-3">
                <input type="text" class="form-control pw" name="pro_water"  id="pro_water" placeholder="pro_price">
                <label for="pro_water">상품물주기</label>
            </div>
            <div class="input-group mb-3 col-md-6">
				  <input type="file" class="form-control" name="pro_img" id="pro_img">
				  <label class="input-group-text" for="pro_img">상품 이미지</label>
			</div>
      
            <div class="row">
                <div>
                	<input type="hidden" name="actionType" value="C">
                    <button type="submit" class="btn btn-outline-success btn-lg">상품등록</button>
                </div>
            </div>
        </form>
     </div>
</body>

</html>