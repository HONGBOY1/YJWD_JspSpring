package ch13;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;
import java.util.StringTokenizer;

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

import ch10.News;
import ch10.NewsDAO;

@WebServlet("/news2.nhn")
@MultipartConfig(maxFileSize=1024*1024*2, location="c:/Temp/img")
public class NewsController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private NewsDAO dao;
	private ServletContext ctx;
	
	// �� ���ҽ� �⺻ ��� ����
	private final String START_PAGE = "ch13/newsList.jsp";
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = new NewsDAO();
		ctx = getServletContext();		
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �ѱ� ���� �������̹Ƿ� �ʿ����
		// request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		
		dao = new NewsDAO();
		
		// �ڹ� ���÷����� ����� if, switch ���� ��û�� ���� ���� �޼��尡 ����ǵ��� ��.
		Method m;
		String view = null;
		
		// action �Ķ���� ���� ������ ���
		if (action == null) {
			action = "listNews";
		}
		
		try {
			// ���� Ŭ�������� action �̸��� HttpServletRequest �� �Ķ���ͷ� �ϴ� �޼��� ã��
			m = this.getClass().getMethod(action, HttpServletRequest.class);
			
			// �޼��� ������ ���ϰ� �޾ƿ�
			view = (String)m.invoke(this, request);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			// ���� �α׸� ����� view �� �α��� ȭ������ ����, �տ����� ���� redirection ��뵵 ����.
			ctx.log("��û action ����!!");
			request.setAttribute("error", "action �Ķ���Ͱ� �߸� �Ǿ����ϴ�!!");
			view = START_PAGE;
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		// POST ��û ó���Ŀ��� ���𷺼� ������� �̵� �� �� �־�� ��.
		if(view.startsWith("redirect:/")) {
			// redirect/ ���ڿ� ���� ��θ� ������ ��
			String rview = view.substring("redirect:/".length());
			response.sendRedirect(rview);
		} else {
			// ������ ��� ������, �������� ���ؽ�Ʈ��δ� �ʿ����.
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);	
		}
	}
    
    public String addNews(HttpServletRequest request) {
		News n = new News();
		try {						
			// �̹��� ���� ����
	        Part part = request.getPart("img");
	        String fileName = getFilename(part);
	        if(fileName != null && !fileName.isEmpty()){
	            part.write(fileName);
	        }	        
	        // �Է°��� News ��ü�� ����
			BeanUtils.populate(n, request.getParameterMap());
			
	        // �̹��� ���� �̸��� News ��ü���� ����
	        n.setImg(fileName);

			dao.addNews(n);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("���� �߰� �������� ���� �߻�!!");
			request.setAttribute("error", "������ ���������� ��ϵ��� �ʾҽ��ϴ�!!");
		}
		
		return "redirect:/news2.nhn?action=listNews";
		
	}

	public String deleteNews(HttpServletRequest request) {
    	int aid = Integer.parseInt(request.getParameter("aid"));
		try {
			dao.delNews(aid);
		} catch (SQLException e) {
			e.printStackTrace();
			ctx.log("���� ���� �������� ���� �߻�!!");
			request.setAttribute("error", "������ ���������� �������� �ʾҽ��ϴ�!!");
		}
		return "redirect:/news2.nhn?action=listNews";
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
    	return "ch13/newsList.jsp";
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

    	return "ch13/newsView.jsp";
    }
        
    // multipart ������� �����̸� ����
	private String getFilename(Part part) {
        String fileName = null;   
        // �����̸��� ����ִ� ��� ������ ������ ��
        String header = part.getHeader("content-disposition");
        //part.getHeader -> form-data; name="img"; filename="����5.jpg"
        System.out.println("Header => "+header);

        // ���� �̸��� ����ִ� �Ӽ� �κ��� ������ġ�� ������ �ֵ���ǥ ������ �� �κи� �������
        int start = header.indexOf("filename=");
        fileName = header.substring(start+10,header.length()-1);        
        ctx.log("���ϸ�:"+fileName);        
        return fileName; 
	}
}
