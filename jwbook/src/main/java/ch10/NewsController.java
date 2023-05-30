package ch10;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Servlet implementation class NewsController
 */
@WebServlet("/news.nhn")
@MultipartConfig(maxFileSize=1024*1024*2, location="C:/Temp/img")
public class NewsController extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
   private NewsDAO dao;
   private ServletContext ctx;
  
   private final String START_PAGE = "ch10/newsList.jsp";
   
   public void init(ServletConfig config) throws ServletException {
      super.init(config);
      dao = new NewsDAO();
      ctx = getServletContext();      
   }

   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("utf-8");
      String action = request.getParameter("action");
      
      dao = new NewsDAO();
      
      Method m;
      String view = null;

      if (action == null) {
         action = "listNews";
      }
      
      try {
         m = this.getClass().getMethod(action, HttpServletRequest.class);

         view = (String)m.invoke(this, request);
      } catch (NoSuchMethodException e) {
         e.printStackTrace();
         ctx.log("��û action ����!!");
         request.setAttribute("error", "action �Ķ���Ͱ� �߸� �Ǿ����ϴ�!!");
         view = START_PAGE;
      } catch (Exception e) {
         e.printStackTrace();
      }
   
    
      if(view.startsWith("redirect:/")) {
         String rview = view.substring("redirect:/".length());
         response.sendRedirect(rview);
      } else {
         RequestDispatcher dispatcher = request.getRequestDispatcher(view);
         dispatcher.forward(request, response);   
      }
   }
    
    public String addNews(HttpServletRequest request) {
      News n = new News();
      try {                 
           Part part = request.getPart("file");
           String fileName = getFilename(part);
           if(fileName != null && !fileName.isEmpty()){
               part.write(fileName);
           }           
         BeanUtils.populate(n, request.getParameterMap());
           n.setImg("/img/"+fileName);

         dao.addNews(n);
      } catch (Exception e) {
         e.printStackTrace();
         ctx.log("���� �߰� �������� ���� �߻�!!");
         request.setAttribute("error", "������ ���������� ��ϵ��� �ʾҽ��ϴ�!!");
         return listNews(request);
      }
      
      return "redirect:/news.nhn?action=listNews";
      
   }

   public String deleteNews(HttpServletRequest request) {
       int aid = Integer.parseInt(request.getParameter("aid"));
      try {
         dao.delNews(aid);
      } catch (SQLException e) {
         e.printStackTrace();
         ctx.log("���� ���� �������� ���� �߻�!!");
         request.setAttribute("error", "������ ���������� �������� �ʾҽ��ϴ�!!");
         return listNews(request);
      }
      return "redirect:/news.nhn?action=listNews";
   }

   public String listNews(HttpServletRequest request) {
       List<News> list;
      try {
         list = dao.getAll();
          request.setAttribute("newslist", list);
      } catch (Exception e) {
         e.printStackTrace();
         ctx.log("���� ��� ���� �������� ���� �߻�!!");
         request.setAttribute("error", "���� ����� ���������� ó������ �ʾҽ��ϴ�!!");
      }
       return "ch10/newsList.jsp";
    }
    
    public String getNews(HttpServletRequest request) {
        int aid = Integer.parseInt(request.getParameter("aid"));
        try {
         News n = dao.getNews(aid);
         request.setAttribute("news", n);
      } catch (SQLException e) {
         e.printStackTrace();
         ctx.log("������ �������� �������� ���� �߻�!!");
         request.setAttribute("error", "������ ���������� �������� ���߽��ϴ�!!");
      }

       return "ch10/newsView.jsp";
    }
        
   private String getFilename(Part part) {
        String fileName = null;   
        String header = part.getHeader("content-disposition");
        System.out.println("Header => "+header);

        int start = header.indexOf("filename=");
        fileName = header.substring(start+10,header.length()-1);        
        ctx.log("���ϸ�:"+fileName);        
        return fileName; 
   }
}