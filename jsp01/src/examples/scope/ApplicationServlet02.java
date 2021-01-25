package examples.scope;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ApplicationServlet02
 */
@WebServlet("/app2")
public class ApplicationServlet02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplicationServlet02() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext servletContext = getServletContext();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		
		try {
			int value = (int) servletContext.getAttribute("value");
			value += 1;
			servletContext.setAttribute("value", value);
			writer.print("<h1>" + value + "</h1>");
		}catch(Exception e) {
			writer.print("attribute value is not exist");
		}
		
	}

}
