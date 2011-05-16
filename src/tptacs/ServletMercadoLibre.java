package tptacs;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.grupo4.tptacs.ClienteServiciosREST;

/**
 * Servlet implementation class ServletMercadoLibre
 */
@WebServlet("/ServletMercadoLibre")
public class ServletMercadoLibre extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletMercadoLibre() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 	response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Grupo 4</title>");
        out.println("</head>");
        out.println("<body>");

        out.println(ClienteServiciosREST.pedir());
        out.println("Test");
        out.println("</body>");
        out.println("</html>");

        out.close();
	}
}
