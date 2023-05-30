package pjh22_mvc_plant.controller.member;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


import java.util.*;
import pjh22_mvc_plant.model.member.*;
import pjh22_mvc_plant.model.pay.PayDAO;
import pjh22_mvc_plant.model.pay.PayDTO;

public class MemberController extends HttpServlet implements Servlet{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
	
		HttpSession session = request.getSession();
		
		
		response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
	   String actionType = request.getParameter("actionType");
	   
	   MemberDTO member;
	   MemberDAO memberDAO;
	   ArrayList<MemberDTO> memberlist;
	   
	   PayDTO pay;
	   PayDAO payDAO;
	   ArrayList<PayDTO> paylist;
	   
	   MemberPageInfoVO mpiVO;

	   if(session.getAttribute("memberPageInfoVO") == null) {
		   mpiVO = new MemberPageInfoVO();
	      session.setAttribute("memberPageInfoVO", mpiVO);
	   } else {
		   mpiVO = (MemberPageInfoVO) session.getAttribute("memberPageInfoVO");
	   }
	   
	   memberDAO = new MemberDAO();
	   payDAO = new PayDAO();
	   
	   boolean result;
	   String displayRecordCnt;
	   int drc;
	   String currentPageNo;
	   int cpn;
	   
	   String mem_id ="";
	   
	   switch (actionType) {
		/*
		 * case "join":
		 * 
		 * member = new MemberDTO();
		 * 
		 * member.setMem_id(request.getParameter("mem_id"));
		 * member.setMem_pwd(request.getParameter("mem_pwd"));
		 * member.setMem_name(request.getParameter("mem_name"));
		 * member.setMem_nickname(request.getParameter("mem_nickname"));
		 * member.setMem_email(request.getParameter("mem_email"));
		 * member.setMem_phone(request.getParameter("mem_phone"));
		 * member.setMem_zcode(request.getParameter("mem_zcode"));
		 * member.setMem_add(request.getParameter("mem_add"));
		 * member.setMem_add2(request.getParameter("mem_add2"));
		 * 
		 * result = memberDAO.joinMember(member);
		 * 
		 * if (result == true) {
		 * request.getRequestDispatcher("/index.jsp").forward(request, response); } else
		 * { request.getRequestDispatcher("/com/yju/2wda/view/etc/error.jsp").forward(
		 * request, response); } break;
		 */
		   case "join2":
			   	String pro_num=request.getParameter("pro_num");
			     int teg=Integer.parseInt(request.getParameter("teg"));
			      member = new MemberDTO();
			      member.setMem_id(request.getParameter("mem_id"));
			      member.setMem_pwd(request.getParameter("mem_pwd"));
			      member.setMem_name(request.getParameter("mem_name"));
			      member.setMem_nickname(request.getParameter("mem_nickname"));
			      member.setMem_email(request.getParameter("mem_email"));
			      member.setMem_phone(request.getParameter("mem_phone"));
			      member.setMem_zcode("");
			      member.setMem_add("");
			      member.setMem_add2("");
			      result = memberDAO.joinMember(member);
		
			      if (result == true) {
			    	  if(teg==1)request.getRequestDispatcher("./PayController.pay?actionType=list").forward(request, response);
			    	  else  if(teg==2) request.getRequestDispatcher("./ProductController.pro?actionType=pro_buy&pro_num="+pro_num).forward(request, response);
			    	  else	request.getRequestDispatcher("/index.jsp").forward(request, response);
			      } else {
			    	  request.getRequestDispatcher("/com/yju/2wda/view/etc/error.jsp").forward(request, response);
			      }
			      break;
		   case "login":
			   
		          String userID = "";
		          String userPW = "";
		          pro_num=request.getParameter("pro_num");
		          teg=Integer.parseInt(request.getParameter("teg"));
		          userID = request.getParameter("mem_id");
		          userPW = request.getParameter("mem_pwd");
		          int result1 = memberDAO.login(userID, userPW);
		         
		          
		          if(result1==1) {
		        	  session.setAttribute("loginState", "login");
		        	  session.setAttribute("userid", userID);
		        	  session.setAttribute("userpw", userPW);
		        	  if(teg==1) request.getRequestDispatcher("./PayController.pay?actionType=list").forward(request, response);
		        	  else if(teg==2) request.getRequestDispatcher("./ProductController.pro?actionType=pro_buy&pro_num="+pro_num).forward(request, response);
		        	  else request.getRequestDispatcher("/index.jsp").forward(request, response);
		          }
		          
		          else if(result1==0){
					  PrintWriter script = response.getWriter();
					  script.println("<script>");
					  script.println("alert('비밀번호가 틀립니다.')");
					  script.println("history.back()");
					  script.println("</script>");
					}
					else if(result1==-1){
						  PrintWriter script = response.getWriter();
						  script.println("<script>");
						  script.println("alert('아이디가 없습니다.')");
						  script.println("history.back()");
						  script.println("</script>");
					}
					else if(result1==-2) {
			        	  request.getRequestDispatcher("/com/yju/2wda/view/etc/error.jsp").forward(request, response);
			          }
		           break;
		           
		       case "logout":
		          session.setAttribute("userid", null);
		          session.setAttribute("userpw", null);
		          session.setAttribute("loginState", "logout");
		          request.getRequestDispatcher("/index.jsp").forward(request, response);
		          break;
		       case "mem_R" : // 본인 확인 
		    	   mem_id =request.getParameter("mem_id");
		    	   member = memberDAO.getmember(mem_id);
		    	   
		    	   request.setAttribute("member", member);
		    	   request.getRequestDispatcher("/com/yju/2wda/view/member/member_modify.jsp").forward(request, response);
		    	   break;
				/*
				 * case "mem_U" : // 본인 수정 member = new MemberDTO();
				 * member.setMem_id(request.getParameter("mem_id"));
				 * member.setMem_pwd(request.getParameter("mem_pwd"));
				 * member.setMem_name(request.getParameter("mem_name"));
				 * member.setMem_nickname(request.getParameter("mem_nickname"));
				 * member.setMem_email(request.getParameter("mem_email"));
				 * member.setMem_phone(request.getParameter("mem_phone"));
				 * member.setMem_zcode(request.getParameter("mem_zcode"));
				 * member.setMem_add(request.getParameter("mem_add"));
				 * member.setMem_add2(request.getParameter("mem_add2"));
				 * 
				 * result = memberDAO.updateMember(member);
				 * 
				 * if (result == true) {
				 * request.getRequestDispatcher("/index.jsp").forward(request, response); } else
				 * { request.getRequestDispatcher("/com/yju/2wda/view/etc/error.jsp").forward(
				 * request, response); } break;
				 */
		       case "mem_D" : // 본인 탈퇴
		    	   mem_id = request.getParameter("mem_id");
		    	
		 	       result = memberDAO.deleteMember(mem_id);
		 	       
			 	    if (result == true) {
			 	    	session.setAttribute("userid", null);
				        session.setAttribute("userpw", null);
				        session.setAttribute("loginState", "logout");
					    request.getRequestDispatcher("/index.jsp").forward(request, response);
				    } else {
				    	  request.getRequestDispatcher("/com/yju/2wda/view/etc/error.jsp").forward(request, response);
				    }
		    	   break;
		    	  
		       case "mem_RL" : // 회원 리스트 
		    	   currentPageNo = request.getParameter("currentPageNo");
		    	   cpn = (currentPageNo == null) ? 0 : Integer.parseInt(currentPageNo);
		    	   
		    	   mpiVO.setCurrentPageNo(cpn);
				   mpiVO.adjPageInfo();
				   
				   memberlist=memberDAO.getmemberList(mpiVO);
				   
		    	   request.setAttribute("memberlist", memberlist);
		    	   request.getRequestDispatcher("/com/yju/2wda/view/member/member_mlist.jsp").forward(request, response);
		    	   break;
		    	   
		       case "mem_my" : // 본인 확인 
		    	   mem_id =request.getParameter("mem_id");
		    	   member = memberDAO.getmember(mem_id);
		    	   paylist = payDAO.getPayList(mem_id);
		    	   request.setAttribute("member", member);
		    	   request.setAttribute("paylist", paylist);
		    	   request.getRequestDispatcher("/com/yju/2wda/view/member/member_mylist.jsp").forward(request, response);
		    	   break;
		    	   
		       case "mem_addrup" : // 주소 수정 
		    	   	member = new MemberDTO();
			    	   	
		    		member.setMem_id(request.getParameter("mem_id"));
				    member.setMem_zcode(request.getParameter("mem_zcode"));
				    member.setMem_add(request.getParameter("mem_add"));
				    member.setMem_add2(request.getParameter("mem_add2"));
		
				      result = memberDAO.updateaddr(member);
			
				      if (result == true) {
				    	 request.getRequestDispatcher("./MemberController.be?actionType=mem_my&teg=1&mem_id="+session.getAttribute("userid")).forward(request, response);
				      } else {
				    	  request.getRequestDispatcher("/com/yju/2wda/view/etc/error.jsp").forward(request, response);
				      }
		    	   break;
		       case "mem_product" : // 본인 확인 
		    	   mem_id =request.getParameter("mem_id");
		    	   member = memberDAO.getmember(mem_id);
		    	   
		    	   request.setAttribute("member", member);
		    	   request.getRequestDispatcher("/com/yju/2wda/view/member/member_mylist.jsp").forward(request, response);
		    	   break;  
		    	   
		       case "mem_myup" : // 본인수정
		    	   	member = new MemberDTO();
		    	   	
		    		member.setMem_id(request.getParameter("mem_id"));
				    member.setMem_pwd(request.getParameter("mem_pwd"));
				    member.setMem_name(request.getParameter("mem_name"));
				    member.setMem_nickname(request.getParameter("mem_nickname"));
				    member.setMem_email(request.getParameter("mem_email"));
				    member.setMem_phone(request.getParameter("mem_phone"));

				      result = memberDAO.updateMy(member);
			
				      if (result == true) {
				    	 request.getRequestDispatcher("./MemberController.be?actionType=mem_my&teg=2&mem_id="+session.getAttribute("userid")).forward(request, response);
				      } else {
				    	  request.getRequestDispatcher("/com/yju/2wda/view/etc/error.jsp").forward(request, response);
				      }
		    	   break;
		      default:
		         break;
	   }
	
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		doProcess(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		doProcess(request, response);
	}

}