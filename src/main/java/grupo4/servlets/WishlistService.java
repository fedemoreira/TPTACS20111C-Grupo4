package grupo4.servlets;

import grupo4.wishlist.Producto;
import grupo4.wishlist.WishlistPersistido;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

/**
 * Clase de servicio para el servlet de Wishlists
 *
 */
public class WishlistService {

	/**
	 * Obtiene el wishlist persistido por JPA, si no existe crea uno y lo persiste
	 * @return Wishlist del usuario,(siempre retorna uno)
	 */
	WishlistPersistido obtenerWishlist(HttpServletRequest request, EntityManager em) {
		WishlistPersistido wishlistPersistido = em.find(WishlistPersistido.class, request.getParameter("user").toString());
		if (wishlistPersistido == null)
		{
			System.out.println("No esistia " + "\n");
			wishlistPersistido = crearWishlist(request.getParameter("user").toString(), em);
		}
		return wishlistPersistido;
	}

	/**
	 * Crea un wishlist nuevo y lo persiste
	 * @return Wishlist
	 */
	private WishlistPersistido crearWishlist(String user, EntityManager em) {
		WishlistPersistido wishlist;
		wishlist = new WishlistPersistido(user);
		wishlist.vaciar();
		em.persist(wishlist);
		return wishlist;
	}
	
	/**
	 * Agrega un producto a la wishlist del usuario.
	 * @param request Request con la información del producto (viene de un post)
	 */
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
