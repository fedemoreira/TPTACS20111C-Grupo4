package main.java.grupo4.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.grupo4.clientes.ClienteServiciosRest;

/**
 * Le pasa a un ClienteServiciosRest la URL que debe solicitar en base a los
 * parametros del request.
 */
public class ServletMercadoLibre extends HttpServlet {
	private static final String URLDeProductosDeCategoria = "https://api.mercadolibre.com/sites/MLA/search?category=";
	private static final String URLDeSubcategorias = "https://api.mercadolibre.com/categories/";
	private static final String URLDeCategorias = "https://api.mercadolibre.com/sites/MLA/categories";
	private static final long serialVersionUID = 1L;
	private ClienteServiciosRest clienteRest;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletMercadoLibre() {
		super();
		this.clienteRest = new ClienteServiciosRest();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 *      Realiza un get en base a los parametros que se le pase.
	 *      ?cat=MLAxxxxxx obtiene las subcategorías de esa categoría
	 *      ?cat=MLAxxxxxx&productos=Y obtiene la primera página de productos
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/x-json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(this.clienteRest.pedir(this
				.obtenerURLCategoriaPedida(request)));
		out.close();
	}

	/**
	 * Genera el string con la URL en base a los parametros del request.
	 */
	private String obtenerURLCategoriaPedida(HttpServletRequest request) {
		String parameterCategoria = request.getParameter("cat");
		String parameterProductos = request.getParameter("cat");
		if (parameterCategoria != null) {
			if (parameterProductos != null)
				return URLDeProductosDeCategoria + parameterCategoria;
			return URLDeSubcategorias + parameterCategoria;
		}
		return URLDeCategorias;
	}
}
