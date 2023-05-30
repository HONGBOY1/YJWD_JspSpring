<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>plant[Tip]</title>
</head>
	
<style>
	p{
		font-size : 20px;
		font-weight: 1000;
	}
</style>
<body>

<%@ include file="/header.jsp" %>
<div class="container">
	<img src="<%=tipImage%>/tip.jpg">
	<img src="<%=tipImage%>/tip2.png">
	<img src="<%=tipImage%>/tip3.png">
	
</div>
<button type="button" class="btn btn-secondary">
<i class="bi bi-sun-fill"></i>
 </button>
              
<div class="container">

  <div class="row">
	<div class="col">
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
	</div>
	
	<div class="col">
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
	</div>
	</div>
</div>

</body>	
</html>