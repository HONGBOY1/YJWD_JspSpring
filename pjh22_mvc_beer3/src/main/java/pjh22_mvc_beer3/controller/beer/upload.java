package pjh22_mvc_beer3.controller.beer;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import pjh22_mvc_beer3.model.beer.BeerDAO;
import pjh22_mvc_beer3.model.beer.BeerDTO;
import pjh22_mvc_beer3.model.beer.BeerPageInfoVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class upload extends HttpServlet {

   public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

      doPost(request, response);

   }

   public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

      RequestDispatcher rd = null;
      String fileName = "";
      String fileLength = "";
      File file = null;
      String savePath = "C:\\Users\\hongb\\eclipse-workspace\\pjh22_mvc_beer3\\src\\main\\webapp\\com\\yju\\2wda\\team2\\controller\\image";
      String count = "";
      String b_code = "";
      int maxSize = 5 * 1024 * 1024;
      
      //
      String RequestURI = request.getRequestURI();
      String contextPath = request.getContextPath();
      String command = RequestURI.substring(contextPath.length());
      
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
         //
         
         
      try {
         
         MultipartRequest multi = new MultipartRequest(request, savePath, maxSize, "UTF-8",
               new DefaultFileRenamePolicy());


         //
         count = multi.getParameter("count");
         
         b_code = multi.getParameter("b_code");
         System.out.println("업로드 비코드 : " + b_code);

         beer = new BeerDTO();
         
         beer.setB_code(multi.getParameter("b_code"));
         
         
         String code = multi.getParameter("b_code");
         
         Enumeration<?> files = multi.getFileNames();
         
         String element = "";
         String originalFileName = "";
         
         if(files.hasMoreElements()){
            element = (String)files.nextElement();
            
            b_code = multi.getParameter(b_code);
            System.out.println("code " + code);
            originalFileName = multi.getOriginalFileName(element);
         }
         System.out.println("originalFileName : " + originalFileName);

         result = beerDAO.updateImage(beer, originalFileName, code);
         //

         
         Enumeration efiles = multi.getFileNames();
         int i = 0;
         while (efiles.hasMoreElements()) {
            String name = (String) efiles.nextElement();
            file = multi.getFile(name);
            String str = file.getName();
            i++;
            fileName += "&fileName" + i + "=" + str;
            fileLength += "&fileLength" + i + "=" + file.length();
         }

      } catch (Exception e) {
         System.out.print("예외 발생 : " + e);
      }

      rd = getServletContext().getRequestDispatcher("/com/yju/2wda/team2/view/beer/beer_fileView.jsp?count=" + count + fileName + fileLength);
      rd.forward(request, response);
   }
}