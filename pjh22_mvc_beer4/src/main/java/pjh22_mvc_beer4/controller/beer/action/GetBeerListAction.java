package pjh22_mvc_beer4.controller.beer.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;
import pjh22_mvc_beer4.controller.beer.Action;
import pjh22_mvc_beer4.controller.beer.ActionForward;
import pjh22_mvc_beer4.model.beer.*;


public class GetBeerListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		BeerDAO beerDAO = new BeerDAO();
		ArrayList<BeerDTO> beerList;
		
		beerList = beerDAO.getBeerList();
		request.setAttribute("beerList", beerList);
		
		ActionForward forward = new ActionForward();
	    forward.setRedirect(false);
	    forward.setPath("/com/yju/2wda/team2/view/beer/beer_r.jsp");  
		return forward;
	}
	
}