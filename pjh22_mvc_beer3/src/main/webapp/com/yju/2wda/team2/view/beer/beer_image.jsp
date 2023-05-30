<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ include file="/globalData.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h1>마이쇼핑몰(PJH22) - 맥주사진입력(I)</h1>
   <hr>
   <script language="javascript">
      var count = 1;
      var addCount;

      //행추가
      function addInputBox() {
         for (var i = 1; i <= count; i++) {
            if (!document.getElementsByName("test" + i)[0]) {
               addCount = i;
               break;
            } else
               addCount = count;
         }

         var addStr = "<tr><th width=40><input type=checkbox name=checkList value="+addCount+" size=40 ></th><th width=140><input type=file id=image name=test"+addCount+" size=40></th></tr>";
         var table = document.getElementById("dynamic_table");
         var newRow = table.insertRow();
         var newCell = newRow.insertCell();
         newCell.innerHTML = addStr;
         count++;
      }

      //행삭제
      function subtractInputBox() {
         var table = document.getElementById("dynamic_table");
         //var max = document.gForm.checkList.length;
         //alert(max);
         var rows = dynamic_table.rows.length;
         var chk = 0;
         if (rows > 1) {
            for (var i = 0; i < document.gForm.checkList.length; i++) {
               if (document.gForm.checkList[i].checked == true) {
                  table.deleteRow(i);
                  i--;
                  count--;
                  chk++;
               }
            }
            if (chk <= 0) {
               alert("삭제할 행을 체크해 주세요.");
            }
         } else {
            alert("더이상 삭제할 수 없습니다.");
         }
      }

      function submitbutton() {
         var gform = document.gForm;
         gform.count.value = eval(count);
         //alert(count);
         gForm.submit();
         return;
      }
   </script>


   <BODY -nLoad="addInputBox()">
   <input type="button" value="행 추가" onclick="javascript:addInputBox();"> : <input
      type="button" value="행 삭제" onclick="javascript:subtractInputBox();">
   <br>
   <br>

<form name="gForm" action="./upload.do" enctype="multipart/form-data" method="post">
  <input type="hidden" name="count">
   <p>맥주코드 : <input type="text" name="b_code" size="30"><br><br>
     <input type="submit" value="전송"  onclick="javascript:submitbutton();">

<table border="1">
<tr>
 <th width="40">체크</th>
 <th width="220">내용</th>
</tr>
<tr>
<table cellpadding=0 cellspacing=0 id="dynamic_table" border="1">
</table>
</tr>
</table>
</form>
<br>
   <a href="<%=rootDir%>/index.jsp">홈으로 돌아가기</a>
</body>


</html>