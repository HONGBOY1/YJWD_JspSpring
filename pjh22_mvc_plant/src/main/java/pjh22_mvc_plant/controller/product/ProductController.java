package pjh22_mvc_plant.controller.product;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.util.*;
import pjh22_mvc_plant.model.product.*;

import java.util.Enumeration;

import javax.naming.spi.DirStateFactory.Result;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

public class ProductController extends HttpServlet implements Servlet{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		
	String RequestURI = request.getRequestURI();
	String contextPath = request.getContextPath();
	String command = RequestURI.substring(contextPath.length());
	
	String imgDirPath = "C:\\Users\\hongb\\eclipse-workspace\\pjh22_mvc_plant\\src\\main\\webapp\\com\\yju\\2wda\\controller\\image\\product";
	int maxSize = 1024*1024*5;
	MultipartRequest multi = null;
	String element = null;
	String originalFileName = null;
	String actionType = request.getParameter("actionType");
	
	if(actionType==null) {
		multi = new MultipartRequest(request,imgDirPath,maxSize,"utf-8",new DefaultFileRenamePolicy());
		actionType = multi.getParameter("actionType");
		Enumeration<?> files = multi.getFileNames();
		
		if(files.hasMoreElements()){
			element = (String)files.nextElement();
			originalFileName = multi.getOriginalFileName(element);

		}
	}
		

	  HttpSession session = request.getSession();
		
	   request.setCharacterEncoding("utf-8");
	  

	   
	   ProductDTO product;
	   ProductDAO productDAO;
	   ArrayList<ProductDTO> productlist;
	   
	   ProductPageInfoVO mpiVO;

	   if(session.getAttribute("productPageInfoVO") == null) {
		   mpiVO = new ProductPageInfoVO();
	      session.setAttribute("productPageInfoVO", mpiVO);
	   } else {
		   mpiVO = (ProductPageInfoVO) session.getAttribute("productPageInfoVO");
	   }
	   

	   productDAO = new ProductDAO();

	   boolean result;
	   String displayRecordCnt;
	   int drc;
	   String currentPageNo;
	   int cpn;
	   
	   
	   switch (actionType) {
		   case "C":

			  product = new ProductDTO();
			
			  product.setPro_cg(Integer.parseInt(multi.getParameter("pro_cg")));
			  product.setPro_name(multi.getParameter("pro_name"));
			  product.setPro_content(multi.getParameter("pro_content"));
			  product.setPro_cnt(Integer.parseInt(multi.getParameter("pro_cnt")));
			  product.setPro_price(Integer.parseInt(multi.getParameter("pro_price")));
			  product.setPro_level(Integer.parseInt(multi.getParameter("pro_level")));
			  product.setPro_water(Integer.parseInt(multi.getParameter("pro_water")));
			  product.setPro_img(originalFileName);
			  
		      result = productDAO.insertProduct(product);
	
		      if (result == true) {
		    	 request.getRequestDispatcher("/index.jsp").forward(request, response);
		      } else {
		    	  request.getRequestDispatcher("/com/yju/2wda/view/etc/error.jsp").forward(request, response);
		      }
		      break;
		   case "pro_List" :
			   currentPageNo = request.getParameter("currentPageNo");
	    	   cpn = (currentPageNo == null) ? 0 : Integer.parseInt(currentPageNo);
	    	   
	    	   mpiVO.setCurrentPageNo(cpn);
			   mpiVO.adjPageInfo();
			   
			   productlist=productDAO.getproductList(mpiVO);
			   
	    	   request.setAttribute("productlist", productlist);
	    	   request.getRequestDispatcher("/com/yju/2wda/view/product/product_list.jsp").forward(request, response);
			   break;
		   case "pro_update" :
			   product = new ProductDTO();
			   product.setPro_num(Integer.parseInt(multi.getParameter("pro_num")));
			   product.setPro_cg(Integer.parseInt(multi.getParameter("pro_cg")));
			   product.setPro_name(multi.getParameter("pro_name"));
			   product.setPro_content(multi.getParameter("pro_content"));
			   product.setPro_cnt(Integer.parseInt(multi.getParameter("pro_cnt")));
			   product.setPro_price(Integer.parseInt(multi.getParameter("pro_price")));
				 product.setPro_level(Integer.parseInt(multi.getParameter("pro_level")));
				  product.setPro_water(Integer.parseInt(multi.getParameter("pro_water")));
			   product.setPro_img(originalFileName);
			   
			   result = productDAO.updateProduct(product);
			   
			   if (result == true) {
				   request.getRequestDispatcher("/ProductController.pro?actionType=pro_List").forward(request, response);
				   
		      } else {
		    	  request.getRequestDispatcher("/com/yju/2wda/view/etc/error.jsp").forward(request, response);
		      }
			   break;
	    	  
		   case "pro_delete" :
			   int pro_num = Integer.parseInt(request.getParameter("pro_num"));
		    	
	 	       result = productDAO.deleteProduct(pro_num);
	 	       
		 	    if (result == true) {
				    request.getRequestDispatcher("/ProductController.pro?actionType=pro_List").forward(request, response);
			    } else {
			    	  request.getRequestDispatcher("/com/yju/2wda/view/etc/error.jsp").forward(request, response);
			    }
			   break;
		   case "pro_chk" :
			   pro_num =Integer.parseInt(request.getParameter("pro_num"));
	    	   product = productDAO.getproduct(pro_num);
	    	   
	    	   request.setAttribute("product", product);
	    	   request.getRequestDispatcher("/com/yju/2wda/view/product/product_update.jsp").forward(request, response);
	    	   break;
	    	   
		  case "pro_buy" :
		   pro_num =Integer.parseInt(request.getParameter("pro_num"));
    	   product = productDAO.getproduct(pro_num);
    	   
    	   request.setAttribute("product", product);
    	   request.getRequestDispatcher("/com/yju/2wda/view/product/product_buy.jsp").forward(request, response);
    	   break;	   
		         
	      case "pro_mlist" :
	    	   currentPageNo = request.getParameter("currentPageNo");
	    	   cpn = (currentPageNo == null) ? 0 : Integer.parseInt(currentPageNo);
	    	   int cg=Integer.parseInt( request.getParameter("pro_cg"));
	    	   mpiVO.setCurrentPageNo(cpn);
			   mpiVO.adjPageInfo();
			   
			   productlist=productDAO.getproductmlist(mpiVO,cg);
			   
	    	   request.setAttribute("productlist", productlist);
	    	   request.getRequestDispatcher("/com/yju/2wda/view/product/product_mlist.jsp").forward(request, response);
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