package grupo4.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Le pasa a un ClienteServiciosRest la URL que debe solicitar en base a los
 * parametros del request.
 */
public class ServletWishlist extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5797158012022779648L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletWishlist() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/x-json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(this.obtenerWishlistDeUsuario(request));
		out.close();
	}

	private String obtenerWishlistDeUsuario(HttpServletRequest request) {
		String parameterUser = request.getParameter("user");
		return parameterUser;
	}
}
