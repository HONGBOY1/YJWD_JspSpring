package pjh22_mvc_beer4.controller.beer.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;
import pjh22_mvc_beer4.controller.beer.Action;
import pjh22_mvc_beer4.controller.beer.ActionForward;
import pjh22_mvc_beer4.model.beer.*;

public class DefalutBeerForIDAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int b_id = Integer.parseInt(request.getParameter("b_id"));
		
		BeerDAO beerDAO = new BeerDAO();
		boolean result = beerDAO.deleteBeer(b_id);
		
		ActionForward forward = new ActionForward();

		
		
		if(result==true) {
			forward.setRedirect(true);
			forward.setPath("./deletBeerListDisplay.be");  
		}
		else {
			forward.setRedirect(false);
			forward.setPath("/com/yju/2wda/team2/view/etc/error.jsp");  
		}
		return forward;
	}
	
}