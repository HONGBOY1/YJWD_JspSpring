package pjh22_mvc_plant.controller.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pjh22_mvc_plant.model.cart.*;

public class CartController extends HttpServlet implements Servlet{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
       String RequestURI = request.getRequestURI();
       String contextPath = request.getContextPath();
	   String command = RequestURI.substring(contextPath.length());
	
	   HttpSession session = request.getSession();
		
	   request.setCharacterEncoding("utf-8");
	   String actionType = request.getParameter("actionType");
	   
	   CartDTO Cart;
	   
	   
	    int pro_num;
		String pro_img;
		String pro_name;
		String pro_content;		
		int pro_cnt;
		int pro_price;
		
		ArrayList<CartDTO> cart = null;
		
		Object obj = session.getAttribute("cart");	//세션 객체에서 cart 값을 가져온다.

		if(obj == null) {	//세션 정보가 없으면 배열을 생성 : 최초 주문한 경우
			cart = new ArrayList<CartDTO>();	
		} else {	//세션 정보가 있으면 강제로 캐스팅 : 추가 주문
			cart = (ArrayList<CartDTO>) obj;
		}
		int pos;

	   switch (actionType) {
		   case "list":
			   
			    pro_num = Integer.parseInt(request.getParameter("pro_num"));
				pro_img =request.getParameter("pro_img");
				pro_name=request.getParameter("pro_name");
				pro_content=request.getParameter("pro_content");
				pro_cnt=Integer.parseInt(request.getParameter("pro_cnt"));
				pro_price=Integer.parseInt(request.getParameter("pro_price"));
					
				pos = -1;	//등록된 제품이 없다
				//장바구니 세션에 동일한 제품이 있을 경우 : 수량(cnt) 증가
				for(int i = 0; i < cart.size(); i++) {
					CartDTO dto = cart.get(i);
					if(dto.getPro_name().equals(pro_name)) {
						pos = 1;
						dto.setPro_cnt(dto.getPro_cnt() + pro_cnt);
						break;
					}
				}

				//장바구니 세션에 등록된 제품이 없을 경우 : CartDTO 객체를 생성하여 배열에 등록(add())
				if(pos == -1) {
					CartDTO dto = new CartDTO();
					dto.setPro_name(pro_name);
					dto.setPro_price(pro_price);	//1,500 ▶ 1500 : 쉼표 제거 후 정수형으로 랩핑
					dto.setPro_img(pro_img);
					dto.setPro_cnt(pro_cnt);
					cart.add(dto);
				}

				//cart 세션 객체를 만들어 준다.
				session.setAttribute("cart", cart);
				request.getRequestDispatcher("./ProductController.pro?actionType=pro_buy&pro_num="+pro_num).forward(request, response);
		      break;
		   case "update":
				pro_name = request.getParameter("pro_name");
				pro_cnt=Integer.parseInt(request.getParameter("pro_cnt"));
				
				pos = -1;	//등록된 제품이 없다
				//장바구니 세션에 동일한 제품이 있을 경우 : 수량(cnt) 증가
				for(int i = 0; i < cart.size(); i++) {
					CartDTO dto = cart.get(i);
					if(dto.getPro_name().equals(pro_name)) {
						pos = 1;
						dto.setPro_cnt(pro_cnt);
						break;
					}
				}
				
				session.setAttribute("cart", cart);
				request.getRequestDispatcher("/com/yju/2wda/view/cart/cart_list.jsp").forward(request, response);
			   break;
			   
		   case "clear":
			   session.getAttribute("cart");
			   session.removeAttribute("cart");
				request.getRequestDispatcher("/com/yju/2wda/view/cart/cart_list.jsp").forward(request, response);
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