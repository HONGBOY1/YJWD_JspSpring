package pjh22_mvc_beer4.controller.beer;

import pjh22_mvc_beer4.controller.beer.*;
import pjh22_mvc_beer4.controller.beer.action.*;
import pjh22_mvc_beer4.model.beer.*;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.DefaultBoundedRangeModel;



public class BeerFrontController extends HttpServlet implements Servlet {
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		
		command = command.substring(command.lastIndexOf("/"));
		
		
	   HttpSession session = request.getSession(); 
	   BeerPageInfoVO bpiVO;

	   if(session.getAttribute("beerPageInfoVO") == null) {
	      bpiVO = new BeerPageInfoVO();
	      session.setAttribute("beerPageInfoVO", bpiVO);
	   } else {
	      bpiVO = (BeerPageInfoVO) session.getAttribute("beerPageInfoVO");
	   }
	   
	   ActionForward forward = null;
	   Action action = null;
	   System.out.println("command = "+command);

	   switch (command) {
	   case "/insertBeer.be":
		   action = new InsertBeerAction();
	      break; 
	   case "/getBeerList.be":
		   action = new GetBeerListAction();
		   break;
	   case "/getBeerListForPage.be":
		   action = new GetBeerListForPageAction();
	      break;
	      
	   case "/adjustBPI.be":
		   action = new AdjustBPIAction();
		    break;
		      
	   case "/deleteBeerForID.be":
		   action = new DefalutBeerForIDAction();
		      break;

	   case "/deleteBeerListDisplay.be":
		   action = new DefalutBeerListDisplayAction();
	      break;

	   case "/updataBeerListDisplay.be":
		   action = new UpdateBeerListDisplayDAction();
	      break;
	      
	   case "/updataBeerDisplay.be":
		   action = new UpdateBeerDisplayAction();
	      break;
	      
	   case "/updataBeerForID.be":
		   action = new UpdateBeerForIDAction();
	      break;
	   default :
		   action = new DefalutAction();
		   break;
	   }
	   
	   try {
		   forward = action.execute(request, response);
	   }catch (Exception e) {
		e.printStackTrace();
	   }
	   
	   if(forward != null) {
		   if(forward.isRedirect()) {
			   response.sendRedirect(forward.getPath());
		   }else {
			   RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
			   dispatcher.forward(request, response);
		   }
	   }  
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		doProcess(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		doProcess(request, response);
	}
}