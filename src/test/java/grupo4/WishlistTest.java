package grupo4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import grupo4.wishlist.Producto;
import grupo4.wishlist.WishlistPersistido;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

public class WishlistTest {
	

	private WishlistPersistido wishlist;
    private Producto productoEjemplo;
	private WishlistPersistido wishlistVacia;
	@Before
	public void condicionesIniciales()
	{
		this.wishlist = new WishlistPersistido();
		this.wishlist.vaciar();
		this.productoEjemplo = new Producto("Producto", "Link");
		this.wishlist.aniadirProducto(this.productoEjemplo); 
		this.wishlistVacia = new WishlistPersistido();
		this.wishlistVacia.vaciar();
}
	
	@Test
	public void agregayQuitaBienUnProducto()
	{   
		this.wishlist.quitarProducto(this.productoEjemplo);
	}
	@Test
	public void sePasaAJsonYObtieneElUltimoProducto()
	{
		this.wishlist = new Gson().fromJson(this.wishlist.convertirAJson(), WishlistPersistido.class);
		assertEquals("Producto", this.wishlist.getListaDeProductos().get(0).getNombre());
	}
	@Test
	public void seAgregaCorrectamenteProductoALaWishlist()
	{
		assertTrue(this.wishlist.getListaDeProductos().get(0).getNombre() == "Producto");
	}
	@Test
	public void lasWishlistsSeVacianCorrectamente()
	{
		assertTrue(this.wishlistVacia.getListaDeProductos().size() == 0);
	}
}