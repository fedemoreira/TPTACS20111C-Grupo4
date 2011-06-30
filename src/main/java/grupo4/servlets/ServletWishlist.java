package grupo4.servlets;

import grupo4.persistence.EntityManagerFact;
import grupo4.wishlist.Producto;
import grupo4.wishlist.Wishlist;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
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
	private WishlistService wishlistService = new WishlistService();

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/x-json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		EntityManager em = EntityManagerFact.get().createEntityManager();
		out.println(this.wishlistService.obtenerWishlist(request, em).convertirAJson());
		em.close();
		out.close();
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/x-json;charset=UTF-8");
		EntityManager em = EntityManagerFact.get().createEntityManager();
		Wishlist wishlist = this.wishlistService.obtenerWishlist(request, em);
		wishlist.aniadirProducto(new Producto(request.getParameter("nombre"), request.getParameter("link")));
		em.persist(wishlist.getWishlistPersistido());
		em.close();
	}
}