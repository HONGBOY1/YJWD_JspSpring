<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="pjh22_mvc_beer3.model.beer.*"%>
<%@ page import="java.util.*"%>
<%@ include file="/globalData.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>>마이쇼핑몰(PJH22) - 맥주자료조회</title>
</head>
<body>
   <h1>마이쇼핑몰(PJH22) - 맥주자료조회</h1>
   <hr>
 <%
   BeerDTO beer;
   ArrayList<BeerDTO> beerList;
   BeerPageInfoVO bpiVO;
   bpiVO = (BeerPageInfoVO)session.getAttribute("beerPageInfoVO");
   beerList = (ArrayList<BeerDTO>)request.getAttribute("beerList");
   
   int currentPageNo = bpiVO.getCurrentPageNo();
   String type = request.getParameter("type");
   String country = request.getParameter("country");
   String pay = request.getParameter("pay");
   String select = request.getParameter("select");
 %>
 <h2>검색 조건 </h2> <br>
 <form action="./BeerController.be" method="post">
    <select name="type">
       <option value="null">종류별</option>
       <option value="에일">에일</option>
       <option value="라거">라거</option>
    </select>
       
    <select name="country">
       <option value="null">국가별</option>
       <option value="한국">한국</option>
       <option value="프랑스">프랑스</option>
       <option value="중국">중국</option>
       <option value="벨기에">벨기에</option>
       <option value="미국">미국</option>
       <option value="오스트리아">오스트리아</option>
    </select>
    
     <select name="pay">
       <option value="null">가격 순</option>
       <option value="ASC">가격낮은순</option>
       <option value="DESC">가격높은순</option>
    </select>

    <input type="text" name="select" placeholder="이름별 검색">
    
    <input type="hidden" name="actionType" value="RC4">
     <input type="submit" value="검색" class="btn">
 </form>
 <br>
<h2>현재 DISPLAY RECORDS NUMBER : <%= bpiVO.getLimitCnt() %></h2>
<hr>
<br>
   <table border="1">
      <thead>
        <tr>
            <th>순번</th>
            <th>코드</th>
            <th>종류</th>
            <th>이름</th>
            <th>원산지</th>
            <th>가격</th>
            <th>알코올</th>
            <th>설명</th>
            <th>좋아요</th>
            <th>싫어요</th>
            <th>사진</th>
         </tr>
      </thead>
      <tbody>
<%
for (int i = 0; i < beerList.size(); i++){
   beer = beerList.get(i);

%>
         <tr>
            <td><%=beer.getB_id()%></td>
            <td><%=beer.getB_code()%></td>
            <td><%=beer.getB_category() %></td>
            <td><%=beer.getB_name()%></td>
            <td><%=beer.getB_country() %></td>
            <td><%=beer.getB_price()%></td>
            <td><%=beer.getB_alcohol()%></td>
            <td><%=beer.getB_content()%></td>
            <td><%=beer.getB_like()%></td>
            <td><%=beer.getB_dislike()%></td>
            <td><img src="<%=beerImage%><%=beer.getB_image()%>" width="100"></td>
         </tr>
<%} %>
      </tbody>
   </table>
<a href="./BeerController.be?actionType=R5&currentPageNo=0&type=<%=type%>&country=<%=country%>&select=<%=select%>&pay=<%=pay%>">[FIRST]</a>
   <%
      if ( currentPageNo > 0){
   %>
   <a href="./BeerController.be?actionType=R5&currentPageNo=<%= currentPageNo - 1 %>&type=<%=type%>&country=<%=country%>&select=<%=select%>&pay=<%=pay%>">[PRE]</a>
   <%
      }else{
   %>
      [PRE]
   <%
      }

      for(int i=bpiVO.getStartPageNo(); i<bpiVO.getEndPageNo(); i++){
         if(i== currentPageNo){
   %>
         [<%=(i+1)%>]
   <%
         }else {
   %>
         <a href="./BeerController.be?actionType=R5&currentPageNo=<%= i %>&type=<%=type%>&country=<%=country%>&select=<%=select%>&pay=<%=pay%>">[<%=i+1%>]</a>
   <%      
      }}
   %>   
   <%
      if(currentPageNo < bpiVO.getPageCnt()-1){
   %>
      <a href="./BeerController.be?actionType=R5&currentPageNo=<%= currentPageNo + 1 %>&type=<%=type%>&country=<%=country%>&select=<%=select%>&pay=<%=pay%>">[NXT]</a>
   <%
      }else{
   %>
      [NXT]
   <%
      }
   %>
<a href="./BeerController.be?actionType=R5&currentPageNo=<%=(bpiVO.getPageCnt()-1)%>&type=<%=type%>&country=<%=country%>&select=<%=select%>&pay=<%=pay%>">[END]</a>

<br><a href="<%= rootDir %>/index.jsp">홈으로 돌아가기</a>
</body>
</html>