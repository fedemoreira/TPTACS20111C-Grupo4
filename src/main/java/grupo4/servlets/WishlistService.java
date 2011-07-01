package grupo4.servlets;

import grupo4.wishlist.Producto;
import grupo4.wishlist.WishlistPersistido;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

public class WishlistService {

	WishlistPersistido obtenerWishlist(HttpServletRequest request, EntityManager em) {
		WishlistPersistido wishlistPersistido = em.find(WishlistPersistido.class, request.getParameter("user").toString());
		if (wishlistPersistido == null)
		{
			System.out.println("No esistia " + "\n");
			wishlistPersistido = crearWishlist(request.getParameter("user").toString(), em);
		}
		return wishlistPersistido;
	}

	private WishlistPersistido crearWishlist(String user, EntityManager em) {
		WishlistPersistido wishlist;
		wishlist = new WishlistPersistido(user);
		wishlist.vaciar();
		em.persist(wishlist);
		return wishlist;
	}
	
	void agregarProductoAWishlist(HttpServletRequest request, EntityManager em) {
		WishlistPersistido wishlist = this.obtenerWishlist(request, em);
		if (wishlist == null)
		{
			wishlist = crearWishlist(request.getParameter("user").toString(), em);
		}

		
		wishlist.aniadirProducto(new Producto(request.getParameter("nombre"), request.getParameter("link")));
		em.persist(wishlist);
	}
}
