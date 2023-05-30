package pjh22_mvc_beer3.controller.beer;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.util.*;
import pjh22_mvc_beer3.model.beer.*;

public class BeerController extends HttpServlet implements Servlet{
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		
	    String type;
	    String country;
	    String pay;
	    String select;
		
	   HttpSession session = request.getSession();
		
	   request.setCharacterEncoding("utf-8");
	   String actionType = request.getParameter("actionType");
	   
	   BeerDTO beer;
	   BeerDAO beerDAO;
	   ArrayList<BeerDTO> beerList;
	   
	   BeerPageInfoVO bpiVO;

	   if(session.getAttribute("beerPageInfoVO") == null) {
	      bpiVO = new BeerPageInfoVO();
	      session.setAttribute("beerPageInfoVO", bpiVO);
	   } else {
	      bpiVO = (BeerPageInfoVO) session.getAttribute("beerPageInfoVO");
	   }
	   

	   beerDAO = new BeerDAO();

	   boolean result;
	   String displayRecordCnt;
	   int drc;
	   String currentPageNo;
	   int cpn;
	   

	   switch (actionType) {
	   case "C":

	      beer = new BeerDTO();

	      beer.setB_code(request.getParameter("b_code"));
	      beer.setB_category(request.getParameter("b_category"));
	      beer.setB_name(request.getParameter("b_name"));
	      beer.setB_country(request.getParameter("b_country"));
	      beer.setB_price(Integer.parseInt(request.getParameter("b_price")));
	      beer.setB_alcohol(request.getParameter("b_alcohol"));
	      beer.setB_content(request.getParameter("b_content"));
	      beer.setB_like(0);
	      beer.setB_dislike(0);
	      beer.setB_image(request.getParameter("b_image"));

	      result = beerDAO.insertBeer(beer);

	      if (result == true) {
	    	 request.getRequestDispatcher("/index.jsp").forward(request, response);
	      } else {
	    	  request.getRequestDispatcher("/com/yju/2wda/team2/view/etc/error.jsp").forward(request, response);
	      }
	      break;
	      
	   case "CC":
		      String code ="BE";
		      type=request.getParameter("type");
		      country=request.getParameter("country");
		      code+=type+country;
		      System.out.println(code);
		      
		      String data = beerDAO.getBeerOTPage(type,country);
		      
		      request.setAttribute("data", data);
		      request.getRequestDispatcher("/com/yju/2wda/team2/view/beer/beer_c2.jsp").forward(request, response);
		    
		      break;

	   case "R":
	      beerList = beerDAO.getBeerList();
	      
	      request.setAttribute("beerList", beerList);
	      request.getRequestDispatcher("/com/yju/2wda/team2/view/beer/beer_r.jsp").forward(request, response);
	      break;
	      
	   case "R4":
		      currentPageNo = request.getParameter("currentPageNo");
		      cpn = (currentPageNo == null) ? 0 : Integer.parseInt(currentPageNo);
		      
		      bpiVO.setCurrentPageNo(cpn);
		      bpiVO.adjPageInfo();
		      
		      beerList = beerDAO.getBeerListForPage(bpiVO);
		      
		      request.setAttribute("beerList", beerList);
		      request.getRequestDispatcher("/com/yju/2wda/team2/view/beer/beer_r4.jsp").forward(request, response);
		      break;
		      
	   case "RC4":
	      currentPageNo = request.getParameter("currentPageNo");
	      cpn = (currentPageNo == null) ? 0 : Integer.parseInt(currentPageNo);
	      
	      bpiVO.setCurrentPageNo(cpn);
	      bpiVO.adjPageInfo();
	      
	      type=request.getParameter("type");
	      country=request.getParameter("country");
	      pay = request.getParameter("pay");
	      select = request.getParameter("select");

	      beerList = beerDAO.getBeerListRCPage(bpiVO,type,country,pay,select);
	      
	      request.setAttribute("beerList", beerList);
	      request.getRequestDispatcher("/com/yju/2wda/team2/view/beer/beer_rc4.jsp").forward(request, response);
	      break;

	   case "R_DRC":
	      displayRecordCnt = request.getParameter("displayRecordCnt");
	      drc = (displayRecordCnt == null) ? 10:Integer.parseInt(displayRecordCnt);
	      bpiVO.setLimitCnt(drc);
	      bpiVO.setCurrentPageNo(0);
	      bpiVO.adjPageInfo();

	      request.getRequestDispatcher("/index.jsp").forward(request, response);
	      break;

	   case "D_ID":
	      int b_id = Integer.parseInt(request.getParameter("b_id"));
	      result = beerDAO.deleteBeer(b_id);
	      if (result == true) {
	    	  request.getRequestDispatcher("/com/yju/2wda/team2/BeerController.be?actionType=D").forward(request, response);
	      } else {
	    	  request.getRequestDispatcher("/com/yju/2wda/team2/view/etc/error.jsp").forward(request, response);
	      }
	      break;
	      
	   case "D":
	      currentPageNo = request.getParameter("currentPageNo");
	      cpn = (currentPageNo == null)?0: Integer.parseInt(currentPageNo);
	      
	      bpiVO.setCurrentPageNo(cpn);
	      bpiVO.adjPageInfo();
	      
	      beerList = beerDAO.getBeerListForPage(bpiVO);
	      
	      request.setAttribute("beerList", beerList);
	      request.getRequestDispatcher("/com/yju/2wda/team2/view/beer/beer_d.jsp").forward(request, response);

	      break;
	      
	   case "U":
	      currentPageNo = request.getParameter("currentPageNo");
	      cpn = (currentPageNo == null)?0: Integer.parseInt(currentPageNo);
	      bpiVO.setCurrentPageNo(cpn);
	      bpiVO.adjPageInfo();
	      
	      beerList = beerDAO.getBeerListForPage(bpiVO);
	      
	      request.setAttribute("beerList", beerList);
	      request.getRequestDispatcher("/com/yju/2wda/team2/view/beer/beer_u.jsp").forward(request, response);
	      break;
	      
	   case "U2":
	         
	      b_id = Integer.parseInt(request.getParameter("b_id"));
	      beer = beerDAO.getBeer(b_id);
	      
	      request.setAttribute("beer", beer);
	      request.getRequestDispatcher("/com/yju/2wda/team2/view/beer/beer_u2.jsp").forward(request, response);
	      
	      break;
	      
	   case "U_ID":
	      beer = new BeerDTO();
	      beer.setB_id(Integer.parseInt(request.getParameter("b_id")));
	      beer.setB_code(request.getParameter("b_code"));
	      beer.setB_category(request.getParameter("b_category"));
	      beer.setB_name (request.getParameter("b_name"));
	      beer.setB_country(request.getParameter("b_country"));
	      beer.setB_price(Integer.parseInt(request.getParameter("b_price")));
	      beer.setB_alcohol(request.getParameter("b_alcohol"));
	      beer.setB_content (request.getParameter("b_content"));
	      beer.setB_like (Integer.parseInt(request.getParameter("b_like")));
	      beer.setB_dislike (Integer.parseInt(request.getParameter("b_dislike")));
	      beer.setB_image (request.getParameter("b_image"));
	      
	      result = beerDAO.updateBeer(beer);
	      
	      if(result == true){
	    	  request.getRequestDispatcher("/index.jsp").forward(request, response);
	      }
	      else {
	    	  request.getRequestDispatcher("/com/yju/2wda/team2/view/etc/error.jsp").forward(request, response);
	      }
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


