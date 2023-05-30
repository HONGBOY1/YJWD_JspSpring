package ch13;

import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadServlet
 */
//@WebServlet("/filedown")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("file");
        String FileDIR = "c:/Temp/img/";
        String FilePath = FileDIR + fileName;

        byte b[] = new byte[4096];

        FileInputStream fileInputStream = new FileInputStream(FilePath);
        String mimeType = getServletContext().getMimeType(FilePath);

        if(mimeType == null) mimeType = "application/octet-stream";
        
        response.setContentType(mimeType);
        String encFileName = new String(fileName.getBytes("utf-8"),"ISO-8859-1");
        
        response.setHeader("Content-Disposition", "attachment; filename= " + encFileName);
        
        ServletOutputStream servletOutStream = response.getOutputStream();

        int read;
        while((read = fileInputStream.read(b,0,b.length))!= -1){
            servletOutStream.write(b,0,read);            
        }
        
        servletOutStream.flush();
        servletOutStream.close();
        fileInputStream.close();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
