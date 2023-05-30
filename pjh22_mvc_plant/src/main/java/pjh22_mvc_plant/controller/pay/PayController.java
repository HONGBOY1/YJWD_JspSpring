package pjh22_mvc_plant.controller.pay;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.util.*;

import pjh22_mvc_plant.model.cart.CartDTO;
import pjh22_mvc_plant.model.pay.PayDTO;
import pjh22_mvc_plant.model.pay.PayDAO;
import pjh22_mvc_plant.model.member.*;

public class PayController extends HttpServlet implements Servlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());

		HttpSession session = request.getSession();

		request.setCharacterEncoding("utf-8");
		String actionType = request.getParameter("actionType");
		MemberDTO member;
		MemberDAO memberDAO;
		memberDAO = new MemberDAO();
		CartDTO Cart;
		PayDTO pay;
		PayDAO payDAO;
		int pro_num;
		String pro_img;
		String pro_name;
		String pro_content;
		int pro_cnt;
		int pro_price;
		boolean result = false;
		String mem_id;

		payDAO = new PayDAO();

		ArrayList<CartDTO> cart = null;

		Object obj = session.getAttribute("cart"); // 세션 객체에서 cart 값을 가져온다.

		if (obj == null) { // 세션 정보가 없으면 배열을 생성 : 최초 주문한 경우
			cart = new ArrayList<CartDTO>();
		} else { // 세션 정보가 있으면 강제로 캐스팅 : 추가 주문
			cart = (ArrayList<CartDTO>) obj;
		}
		int pos;

		switch (actionType) {
		case "list":
			mem_id = request.getParameter("mem_id");
			member = memberDAO.getmember(mem_id);

			request.setAttribute("member", member);

			request.getRequestDispatcher("/com/yju/2wda/view/pay/pay_list.jsp?teg=0").forward(request, response);
			break;
		case "one":
			mem_id = request.getParameter("mem_id");
			member = memberDAO.getmember(mem_id);

			pro_img = request.getParameter("pro_img");
			pro_name = request.getParameter("pro_name");
			/* pro_content=request.getParameter("pro_content"); */
			pro_cnt = Integer.parseInt(request.getParameter("pro_cnt"));
			pro_price = Integer.parseInt(request.getParameter("pro_price"));

			request.setAttribute("member", member);

			request.getRequestDispatcher("/com/yju/2wda/view/pay/pay_list.jsp?pro_img=" + pro_img + "&pro_name="
					+ pro_name + "&pro_price=" + pro_price + "&pro_cnt=" + pro_cnt + "&teg=1")
					.forward(request, response);
			break;
		case "pay":

			pay = new PayDTO();
			int teg = Integer.parseInt(request.getParameter("teg"));

			if (teg == 1) {
				pay.setMem_id(request.getParameter("mem_id"));
				pay.setPro_img(request.getParameter("pro_img"));
				pay.setPro_name(request.getParameter("pro_name"));
				pay.setPro_cnt(Integer.parseInt(request.getParameter("pro_cnt")));
				pay.setOrd_name(request.getParameter("ord_name"));
				pay.setOrd_phone(request.getParameter("ord_phone"));
				pay.setOrd_zcode(request.getParameter("ord_zcode"));
				pay.setOrd_add(request.getParameter("ord_add"));
				pay.setOrd_add2(request.getParameter("ord_add2"));
				pay.setOrd_content(request.getParameter("ord_content"));
				pay.setOrd_price(Integer.parseInt(request.getParameter("ord_price")));
				result = payDAO.order(pay);
			} else {
				System.out.print(cart.size());
				
				 mem_id=request.getParameter("mem_id"); String
				 ord_name=request.getParameter("ord_name"); String
				 ord_phone=request.getParameter("ord_phone"); String
				 ord_zcode=request.getParameter("ord_zcode"); String
				 ord_add=request.getParameter("ord_add"); String
				 ord_add2=request.getParameter("ord_add2"); String
				 ord_content=request.getParameter("ord_content"); String
				 ord_price=request.getParameter("ord_price"); 
				 for(int i = 0; i < cart.size(); i++) {
					 payDAO = new PayDAO();
					 CartDTO dto = cart.get(i);

					 pay.setMem_id(mem_id);
					 pay.setPro_img(dto.getPro_img());
					 pay.setPro_name(dto.getPro_name()); 
					 pay.setPro_cnt(dto.getPro_cnt());
					 pay.setOrd_name(ord_name); 
					 pay.setOrd_phone(ord_phone);
					 pay.setOrd_zcode(ord_zcode); 
					 pay.setOrd_add(ord_add);
					 pay.setOrd_add2(ord_add2); 
					 pay.setOrd_content(ord_content);
					 pay.setOrd_price(dto.getPro_price()); 
					payDAO.order(pay);
				 } 
				 result=true;

			}

			if (result == true) {
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/com/yju/2wda/view/etc/error.jsp").forward(request, response);
			}
			break;

		default:
			break;
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}