package grupo4.wishlist;


import java.util.HashMap;
import java.util.Map;

public class WishlistMapper {
	/**
	 * Mappea a cada usuario con su wishlist usando un mapa.
	 */
	private Map<String, Wishlist> map;
	
	public WishlistMapper()
	{
		this.setMap(new HashMap<String, Wishlist>());
	}
	/**
	 * Devuelve el wishlist del usuario si existe. En caso de no existir, crea uno nuevo y lo agrega al diccionario. 
	 * @param nombreDeUsuario ID que identifica al usuario
	 * @return Objeto Wishlist con el wishlist del usuario si existe, nuevo Wishlist si no.
	 */
	public Wishlist get(String nombreDeUsuario) {
		
		Wishlist wishlistObtenido = this.getMap().get(nombreDeUsuario);
		if(wishlistObtenido != null)
			return this.getMap().get(nombreDeUsuario);
		this.getMap().put(nombreDeUsuario, new Wishlist());
		return this.get(nombreDeUsuario);
	}
	public void setMap(Map<String, Wishlist> map) {
		this.map = map;
	}
	public Map<String, Wishlist> getMap() {
		return this.map;
	}
}
