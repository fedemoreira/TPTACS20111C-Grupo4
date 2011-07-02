package grupo4.servlets;

import grupo4.persistence.EntityManagerFact;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Maneja la persistencia de wishlists.
 */
public class ServletWishlist extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5797158012022779648L;
	private WishlistService wishlistService = new WishlistService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletWishlist() {
		super();
	}

	/**
	 * Obtiene la wishlist de un usuario, el ID de usuario viene por parámetro en el request.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/x-json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		EntityManager em = EntityManagerFact.get().createEntityManager();
		out.println(this.wishlistService.obtenerWishlistDeUsuario(request, em).convertirAJson());
		em.close();
		out.close();
	}

	/**
	 * Agrega un producto a la wishlist del usuario, los parámetros del producto vienen por parámetros de request.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/x-json;charset=UTF-8");
		EntityManager em = EntityManagerFact.get().createEntityManager();
		if(request.getParameter("limpiar") != null)
			this.wishlistService.limpiarWishlist(request, em);
		else
			this.wishlistService.agregarProductoAWishlist(request, em);
		em.close();
	}
}