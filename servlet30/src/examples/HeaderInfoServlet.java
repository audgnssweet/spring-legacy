package examples;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HeaderInfoServlet
 */
@WebServlet("/info")
public class HeaderInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HeaderInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		String requestURI = request.getRequestURI();
		StringBuffer requestURL = request.getRequestURL();
		String contextPath = request.getContextPath();
		String remoteAddr = request.getRemoteAddr();
		
		writer.print("<h5>"+ "requestURI" + " : " + requestURI +"</h5><br>");
		writer.print("<h5>"+ "requestURL" + " : " + requestURL +"</h5><br>");
		writer.print("<h5>"+ "contextPath" + " : " + contextPath +"</h5><br>");
		writer.print("<h5>"+ "remoteAddr" + " : " + remoteAddr +"</h5><br>");
		
		writer.close();
	}

}
