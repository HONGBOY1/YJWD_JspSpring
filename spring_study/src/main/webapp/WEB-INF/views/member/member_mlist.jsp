<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
   <%@ page import="pjh22_mvc_plant.model.member.*"%>
   <%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 조회[ select ]</title>
</head>
<body>

 <%
   MemberDTO member;
   ArrayList<MemberDTO> memberList;
   MemberPageInfoVO mpiVO;
   mpiVO = (MemberPageInfoVO)session.getAttribute("memberPageInfoVO");
   memberList = (ArrayList<MemberDTO>)request.getAttribute("memberlist");
   
   int currentPageNo = mpiVO.getCurrentPageNo();
 %>

<%@ include file="../header.jsp" %> 
<div class="container text-center">
	<table border="1" class="table">
		<thead>
		  <tr>
            <th>아이디</th>
            <th>비밀번호</th>
            <th>이름</th>
            <th>닉네임</th>
            <th>레벨</th>
            <th>이메일</th>
            <th>전화번호</th>
            <th>우편번호</th>
            <th>주소</th>
            <th>상세주소</th>
            <th>가입날짜</th>
         </tr>
      </thead>
      <tbody>
<%
for (int i = 0; i < memberList.size(); i++){
	member = memberList.get(i);
%>
         <tr>
            <td><%=member.getMem_id()%></td>
            <td><%=member.getMem_pwd()%></td>
            <td><%=member.getMem_name() %></td>
            <td><%=member.getMem_nickname()%></td>
            <td><%=member.getMem_level()%></td>
            <td><%=member.getMem_email() %></td>
            <td><%=member.getMem_phone()%></td>
            <td><%=member.getMem_zcode()%></td>
            <td><%=member.getMem_add()%></td>
            <td><%=member.getMem_add2()%></td>
            <td><%=member.getMem_date()%></td>
         </tr>
<%} %>
      </tbody>
   </table>
<a href="./MemberController.be?actionType=mem_RL&currentPageNo=0">[FIRST]</a>
	<%
		if ( currentPageNo > 0){
	%>
	<a href="./MemberController.be?actionType=mem_RL&currentPageNo=<%= currentPageNo - 1 %>">[PRE]</a>
	<%
		}else{
	%>
		[PRE]
	<%
		}

		for(int i=mpiVO.getStartPageNo(); i<mpiVO.getEndPageNo(); i++){
			if(i== currentPageNo){
	%>
			[<%=(i+1)%>]
	<%
			}else {
	%>
			<a href="./MemberController.be?actionType=mem_RL&currentPageNo=<%= i %>">[<%=i+1%>]</a>
	<%		
		}}
	%>	
	<%
		if(currentPageNo < mpiVO.getPageCnt()-1){
	%>
		<a href="./MemberController.be?actionType=mem_RL&currentPageNo=<%= currentPageNo + 1 %>">[NXT]</a>
	<%
		}else{
	%>
		[NXT]
	<%
		}
	%>
<a href="./MemberController.be?actionType=mem_RL&currentPageNo=<%=(mpiVO.getPageCnt()-1)%>">[END]</a>
</div>
</body>

</html>