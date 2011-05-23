package main.java.grupo4.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.grupo4.clientes.ClienteServiciosREST;
import main.java.grupo4.clientes.ParserJSON;

/**
 * Servlet implementation class ServletMercadoLibre
 */
public class ServletMercadoLibre extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClienteServiciosREST clienteRest;
	private ParserJSON JSONParser;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletMercadoLibre() {
		super();
		this.clienteRest = new ClienteServiciosREST();
		this.setJSONParser(new ParserJSON());
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
		String parameterCategoria = request.getParameter("cat");
		String parameterProductos = request.getParameter("productos");
		if(parameterCategoria != null)
		{
			if(parameterProductos != null)
				return "https://api.mercadolibre.com/sites/MLA/search?category=" + parameterCategoria;
			return "https://api.mercadolibre.com/categories/" + parameterCategoria;
		}
		return "https://api.mercadolibre.com/sites/MLA/categories";
	}

	public void setJSONParser(ParserJSON jSONParser) {
		this.JSONParser = jSONParser;
	}

	public ParserJSON getJSONParser() {
		return this.JSONParser;
	}
}
