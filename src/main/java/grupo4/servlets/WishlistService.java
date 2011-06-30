package grupo4.servlets;

import grupo4.wishlist.Wishlist;
import grupo4.wishlist.WishlistPersistido;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

public class WishlistService {

	Wishlist obtenerWishlist(HttpServletRequest request, EntityManager em) {
		WishlistPersistido wishlistPersistido = em.find(WishlistPersistido.class, request.getParameter("user").toString());
		Wishlist wishlist;
		if (wishlistPersistido == null)
		{
			wishlist = crearWishlist(request.getParameter("user").toString(), em);
		}
		else
		{
			wishlist = new Wishlist(wishlistPersistido);
		}
		return wishlist;
	}

	private Wishlist crearWishlist(String user, EntityManager em) {
		Wishlist wishlist;
		wishlist = new Wishlist(user);
		em.persist(wishlist.getWishlistPersistido());
		return wishlist;
	}
	
}
