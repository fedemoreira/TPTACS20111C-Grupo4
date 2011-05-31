package main.java.grupo4.servlets;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.grupo4.clientes.ClienteServiciosREST;

/**
 * Servlet implementation class ServletMercadoLibre
 */
public class ServletMercadoLibre extends HttpServlet {
	private static final String URLDeProductosDeCategoria = "https://api.mercadolibre.com/sites/MLA/search?category=";
	private static final String URLDeSubcategorias = "https://api.mercadolibre.com/categories/";
	private static final String URLDeCategorias = "https://api.mercadolibre.com/sites/MLA/categories";
	private static final long serialVersionUID = 1L;
	private ClienteServiciosREST clienteRest;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletMercadoLibre() {
		super();
		this.clienteRest = new ClienteServiciosREST();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/x-json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(this.clienteRest.pedir(this.obtenerURLCategoriaPedida(request)));
		out.close();
	}

	private String obtenerURLCategoriaPedida(HttpServletRequest request){
		String parameterCategoria = obtenerParametroCategoria(request);
		String parameterProductos = obtenerParametroProductos(request);
		if(existeParametroProductos(parameterCategoria))
		{
			if(existeParametroProductos(parameterProductos))
				return URLDeProductosDeCategoria + parameterCategoria;
			return URLDeSubcategorias + parameterCategoria;
		}
		return URLDeCategorias;
	}

	private boolean existeParametroProductos(String parameterProductos) {
		return parameterProductos != null;
	}

	private String obtenerParametroProductos(HttpServletRequest request) {
		return request.getParameter("productos");
	}

	private String obtenerParametroCategoria(HttpServletRequest request) {
		return request.getParameter("cat");
	}
}
