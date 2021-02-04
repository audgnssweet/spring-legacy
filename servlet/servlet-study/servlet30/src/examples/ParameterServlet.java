package examples;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ParameterServlet
 */
@WebServlet("/param")
public class ParameterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParameterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		
		//여기서 말하는 parameter은 GET method에서 ? 뒤에 붙는 파라미터. query string이라고도 함.
		Enumeration<String> parameterNames = request.getParameterNames();
		while(parameterNames.hasMoreElements()) {
			String parameterName = parameterNames.nextElement();
			String parameterContent = request.getParameter(parameterName);
			writer.print("<h5>" + parameterName + " : " + parameterContent + "</h5><br>");
		}
		writer.close();
	}

}
