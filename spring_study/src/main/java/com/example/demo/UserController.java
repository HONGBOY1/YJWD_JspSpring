package com.example.demo;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserController {

  private final UserDAO userDAO;


   @Autowired
   public UserController(UserDAO userDAO) {
       this.userDAO = userDAO;
   }

   // login
   @PostMapping("/login")
   public String login(HttpServletRequest request, HttpSession session, @RequestBody String requestBody)
         throws Exception {
      request.setCharacterEncoding("utf-8");
      String mem_id = request.getParameter("mem_id");
      String mem_pwd = request.getParameter("mem_pwd");
      UserDAO userDAO = new UserDAO();
      UserDTO user = new UserDTO();
      int result = userDAO.login(mem_id, mem_pwd);
      if (result == 1) {
         session.setAttribute("loginState", "login");
         session.setAttribute("userid", mem_id);
         session.setAttribute("userpw", mem_pwd);
         System.out.println("hi");
      } else if (result == 0) {
         session.setAttribute("userid", null);
         session.setAttribute("userpw", null);
         String script = "<script>alert('비밀번호가 맞지 않습니다.'); history.back();</script>";
         return script;
      } else if (result == -1) {
         session.setAttribute("userid", null);
         session.setAttribute("userpw", null);
         String script = "<script>alert('아이디가 존재하지 않습니다.'); history.back();</script>";
         return script;
      }

      return "redirect:/main";
   }

   // logout
   @GetMapping("/logout")
   public String logout(HttpServletRequest request) throws Exception {
      request.setCharacterEncoding("utf-8");

      HttpSession session = request.getSession();
      session.invalidate();
      String script = "<script>alert('로그아웃 되었습니다.'); history.back();</script>";
      return "redirect:/main";
      
   }
   
// login
   @PostMapping("/join")
   public String join(HttpServletRequest request, HttpSession session, @RequestBody String requestBody)
         throws Exception {
		      request.setCharacterEncoding("utf-8");
		      String mem_id = request.getParameter("mem_id");
		      String mem_pwd = request.getParameter("mem_pwd");
		      UserDAO userDAO = new UserDAO();
		      UserDTO user = new UserDTO();
		      
		      user.setMem_id(request.getParameter("mem_id"));
		      user.setMem_pwd(request.getParameter("mem_pwd"));
		      user.setMem_name(request.getParameter("mem_name"));
		      user.setMem_nickname(request.getParameter("mem_nickname"));
		      user.setMem_email(request.getParameter("mem_email"));
		      user.setMem_phone(request.getParameter("mem_phone"));
		      user.setMem_zcode(request.getParameter("mem_zcode"));
		      user.setMem_add(request.getParameter("mem_add"));
		      user.setMem_add2(request.getParameter("mem_add2"));
		      
		      
		      boolean result = userDAO.joinMember(user);
		      
		      
		      if (result) {
		    	 System.out.println("성공");
		      } else {
		    	  System.out.println("실패");
		      }
		
		      return "redirect:/main";
   }
   
   



}