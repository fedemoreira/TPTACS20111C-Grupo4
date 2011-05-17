package main.java.grupo4.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import main.java.grupo4.clientes.ClienteServiciosREST;

/**
 * Servlet implementation class ServletMercadoLibre
 */
public class ServletMercadoLibre extends HttpServlet {
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
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title>Grupo 4</title>");
		out.println("</head>");
		out.println("<body>");


		for(Object jso :  this.clienteRest.obtenerArrayJSON("[" + this.clienteRest.pedir("https://api.mercadolibre.com/sites/MLA/categories") + "]"))
			out.println("Categoría: " + jso + "\n\n");
			
		out.println("</body>");
		out.println("</html>");

		out.close();
	}
}
