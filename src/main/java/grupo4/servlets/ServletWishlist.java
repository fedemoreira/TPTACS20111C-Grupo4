package grupo4.servlets;

import grupo4.persistence.EntityManagerFact;
import grupo4.persistence.WishlistPersistido;
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
		EntityManager em = EntityManagerFact.get().createEntityManager();
		WishlistPersistido wishlistPersistido = em.find(WishlistPersistido.class, request.getParameter("user").toString());
		if(wishlistPersistido!=null)
		{
			out.println(new Wishlist(wishlistPersistido).convertirAJson());
		}
		else
		{
			Wishlist wishlist = new Wishlist();
			wishlist.setUsuario(request.getParameter("user").toString());
			em.persist(wishlist.getWishlistPersistido());
			out.println(wishlist.convertirAJson());
		}
		em.close();
		out.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/x-json;charset=UTF-8");
		EntityManager em = EntityManagerFact.get().createEntityManager();
		WishlistPersistido wishlistPersistido = em.find(WishlistPersistido.class, request.getParameter("user").toString());
		Producto productoAPersistir = new Producto();
		productoAPersistir.setLink(request.getParameter("link"));
		productoAPersistir.setNombre(request.getParameter("nombre"));
		Wishlist wishlist;
		if(wishlistPersistido!=null)
		{
			wishlist = new Wishlist(wishlistPersistido);
		}
		else
		{
			wishlist = new Wishlist();
			wishlist.setUsuario(request.getParameter("user").toString());
		}
		wishlist.aniadirProducto(productoAPersistir);
		em.persist(wishlist.getWishlistPersistido());
		em.close();
	}
}
