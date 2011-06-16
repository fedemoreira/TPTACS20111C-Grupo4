package grupo4.wishlist;


import java.util.Dictionary;

public class WishlistMapper {
	/**
	 * Mappea a cada usuario con su wishlist usando un diccionario.
	 */
	Dictionary<String, Wishlist> map;
	
	/**
	 * Devuelve el wishlist del usuario si existe. En caso de no existir, crea uno nuevo y lo agrega al diccionario. 
	 * @param nombreDeUsuario ID que identifica al usuario
	 * @return Objeto Wishlist con el wishlist del usuario si existe, nuevo Wishlist si no.
	 */
	public Wishlist get(String nombreDeUsuario) {
		Wishlist wishlistObtenido = this.map.get(nombreDeUsuario);
		if(wishlistObtenido != null)
			return this.map.get(nombreDeUsuario);
		this.map.put(nombreDeUsuario, new Wishlist());
		return this.get(nombreDeUsuario);
	}	
}
