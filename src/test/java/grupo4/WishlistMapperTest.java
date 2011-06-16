package grupo4;

import static org.junit.Assert.*;
import grupo4.wishlist.Wishlist;
import grupo4.wishlist.WishlistMapper;

import org.junit.Before;
import org.junit.Test;

public class WishlistMapperTest {
	
	WishlistMapper map;
	@Before
	public void condicionesIniciales()
	{
		this.map = new WishlistMapper();
	}
	@Test
	public void siPidoUnWishlistQueExisteLoDevuelve()
	{
		Wishlist wishlist = new Wishlist();
		this.map.getMap().put("UsuarioEjemplo", wishlist);
		assertEquals(this.map.get("UsuarioEjemplo"), wishlist);
	}	
	
	@Test
	public void siPidoUnWishlistEnMapVacioDevuelveUno()
	{
		assertEquals(0, this.map.get("UsuarioEjemplo").getListaDeProductos().size());
	}	
}
