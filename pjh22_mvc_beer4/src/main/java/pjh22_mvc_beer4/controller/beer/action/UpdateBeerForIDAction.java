package pjh22_mvc_beer4.controller.beer.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.HttpSession;

import java.util.*;
import pjh22_mvc_beer4.controller.beer.Action;
import pjh22_mvc_beer4.controller.beer.ActionForward;
import pjh22_mvc_beer4.model.beer.*;

public class UpdateBeerForIDAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		BeerDTO beer = new BeerDTO();
		BeerDAO beerDAO = new BeerDAO();
		
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

		boolean result = beerDAO.insertBeer(beer);
	    ActionForward forward = new ActionForward();
	    forward.setRedirect(false);
	    if (result == true) {
	    	 forward.setPath("/index.jsp");
	     } else {
	    	 forward.setPath("/com/yju/2wda/team2/view/etc/error.jsp");
	    }
	   
		return forward;
	}
	
}