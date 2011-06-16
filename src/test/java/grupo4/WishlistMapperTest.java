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
	public void siPidoUnWishlistEnMapVacioDevuelveUno()
	{
		assertEquals(this.map.get("UsuarioEjemplo"), new Wishlist());
	}	
}
