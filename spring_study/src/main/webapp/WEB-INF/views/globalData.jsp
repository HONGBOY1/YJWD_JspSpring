<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <%!
  
   static final String rootDir = "/spring_study";
   static final String workDir = rootDir + "/src/main/resources";
   
   static final String all = rootDir + "spring_study/src/main/resources";
   
   
   static final String modelDir = workDir + "/model";
   static final String viewDir = workDir + "/view";
   static final String contDir = workDir + "";
   
   static final String memberModelDir = modelDir + "/member";
   static final String memberViewDir = viewDir + "/member";
   static final String memberContDir = contDir + "/member";
   
   static final String productModelDir = modelDir + "/product";
   static final String productViewDir = viewDir + "/product";
   static final String productContDir = contDir + "/product";

   
   static final String cartModelDir = modelDir + "/cart";
   static final String cartViewDir = viewDir + "/cart";
   static final String cartContDir = contDir + "/cart";
   
   static final String payModelDir = modelDir + "/pay";
   static final String payViewDir = viewDir + "/pay";
   static final String payContDir = contDir + "/pay";

   static final String mianImage ="/image/main";
   static final String memberImage = contDir + "/image/member/";
   static final String productImage = contDir + "/image/product/";
   static final String tipImage = contDir + "/image/tip/";
   
   static final String etcViewDir = viewDir + "/etc";
   %>
</body>
</html>