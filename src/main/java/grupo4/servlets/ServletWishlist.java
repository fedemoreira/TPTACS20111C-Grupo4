package grupo4.servlets;

<<<<<<< HEAD

import grupo4.wishlist.Producto;
import grupo4.wishlist.Wishlist;
import grupo4.persistence.WishlistPersistido;
=======
import grupo4.persistence.EntityManagerFact;
import grupo4.wishlist.Producto;
import grupo4.wishlist.Wishlist;
import grupo4.wishlist.WishlistPersistido;
>>>>>>> a1c43209c2f4f3edee79e4321a4404aed0c035c1

import java.io.IOException;
import java.io.PrintWriter;

import grupo4.persistence.PMF;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/x-json;charset=UTF-8");
		PrintWriter out = response.getWriter();
<<<<<<< HEAD
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		try
		{
			WishlistPersistido wishlistPersistido = pm.getObjectById(WishlistPersistido.class, request.getParameter("user"));
			Wishlist wishlist;
			if(wishlistPersistido == null)
			{
				wishlist  = new Wishlist(wishlistPersistido);
				wishlist.aniadirProducto(new Producto("Das", "Asdf"));	
				out.println("Posta que era null.");
			}
			else
			{
=======
		EntityManager em = EntityManagerFact.get().createEntityManager();
		if(request.getParameter("user") != null)
			out.println(obtenerWishlist(request, em).convertirAJson());
		else
			out.println(new Wishlist().convertirAJson());			
		em.close();
		out.close();
	}

	private Wishlist obtenerWishlist(HttpServletRequest request, EntityManager em) {
		WishlistPersistido wishlistPersistido = em.find(WishlistPersistido.class, request.getParameter("user").toString());
		Wishlist wishlist;
		if (wishlistPersistido == null)
		{
>>>>>>> a1c43209c2f4f3edee79e4321a4404aed0c035c1
			wishlist = new Wishlist();
			wishlist.setUsuario(request.getParameter("user").toString());
			em.persist(wishlist.getWishlistPersistido());
		}
		else
		{
<<<<<<< HEAD
			pm.close();
=======
			wishlist = new Wishlist(wishlistPersistido);
>>>>>>> a1c43209c2f4f3edee79e4321a4404aed0c035c1
		}
		return wishlist;
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/x-json;charset=UTF-8");
		EntityManager em = EntityManagerFact.get().createEntityManager();
		Wishlist wishlist = obtenerWishlist(request, em);
		Producto p = new Producto();
		p.setNombre(request.getParameter("nombre"));
		p.setLink(request.getParameter("link"));
		wishlist.agregarOQuitar(p);
		em.persist(wishlist.getWishlistPersistido());
		em.close();
	}
}
