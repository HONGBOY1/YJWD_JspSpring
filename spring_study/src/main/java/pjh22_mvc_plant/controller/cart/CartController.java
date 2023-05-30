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
		
		Object obj = session.getAttribute("cart");	//���� ��ü���� cart ���� �����´�.

		if(obj == null) {	//���� ������ ������ �迭�� ���� : ���� �ֹ��� ���
			cart = new ArrayList<CartDTO>();	
		} else {	//���� ������ ������ ������ ĳ���� : �߰� �ֹ�
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
					
				pos = -1;	//��ϵ� ��ǰ�� ����
				//��ٱ��� ���ǿ� ������ ��ǰ�� ���� ��� : ����(cnt) ����
				for(int i = 0; i < cart.size(); i++) {
					CartDTO dto = cart.get(i);
					if(dto.getPro_name().equals(pro_name)) {
						pos = 1;
						dto.setPro_cnt(dto.getPro_cnt() + pro_cnt);
						break;
					}
				}

				//��ٱ��� ���ǿ� ��ϵ� ��ǰ�� ���� ��� : CartDTO ��ü�� �����Ͽ� �迭�� ���(add())
				if(pos == -1) {
					CartDTO dto = new CartDTO();
					dto.setPro_name(pro_name);
					dto.setPro_price(pro_price);	//1,500 �� 1500 : ��ǥ ���� �� ���������� ����
					dto.setPro_img(pro_img);
					dto.setPro_cnt(pro_cnt);
					cart.add(dto);
				}

				//cart ���� ��ü�� ����� �ش�.
				session.setAttribute("cart", cart);
				request.getRequestDispatcher("./ProductController.pro?actionType=pro_buy&pro_num="+pro_num).forward(request, response);
		      break;
		   case "update":
				pro_name = request.getParameter("pro_name");
				pro_cnt=Integer.parseInt(request.getParameter("pro_cnt"));
				
				pos = -1;	//��ϵ� ��ǰ�� ����
				//��ٱ��� ���ǿ� ������ ��ǰ�� ���� ��� : ����(cnt) ����
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