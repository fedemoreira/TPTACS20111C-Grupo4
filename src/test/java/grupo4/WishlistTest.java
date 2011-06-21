package grupo4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import grupo4.wishlist.Producto;
import grupo4.wishlist.Wishlist;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

public class WishlistTest {
	

	private Wishlist wishlist;
    private Producto productoEjemplo;
    
	@Before
	public void condicionesIniciales()
	{
		this.wishlist = new Wishlist();
		this.productoEjemplo = new Producto("Producto", "Link de prueba");
		this.wishlist.aniadirProducto(this.productoEjemplo); 
	}
	
	@Test
	public void agregayQuitaBienUnProducto()
	{   
		this.wishlist.quitarProducto(this.productoEjemplo);
	}	
	
	@Test
	public void sePasaAJsonYObtieneElUltimoProducto()
	{
		this.wishlist = new Gson().fromJson(this.wishlist.convertirAJson(), Wishlist.class);
		assertEquals("Producto", this.wishlist.dameProducto(0).getNombre());
	}

	@Test
	public void detectaCorrectamenteQueUnProductoYaExisteEnWishlist()
	{
		assertTrue(this.wishlist.tieneElProducto("Producto"));
	}

	@Test
	public void detectaCorrectamenteQueUnProductoInexistenteNoExisteEnWishlist()
	{
		assertFalse(this.wishlist.tieneElProducto("Producto que ni loco esta"));
	}


	@Test
	public void detectaCorrectamenteQueUnProductoInexistenteNoExisteEnWishlistBuscandoPorProducto()
	{
		Producto productoQueEsta = new Producto("Un-", "ProductoQueNoEsta");
		assertFalse(this.wishlist.tieneElProducto(productoQueEsta));
	}


	@Test
	public void detectaCorrectamenteQueUnProductoExistenteExisteEnWishlistBuscandoPorProducto()
	{
		Producto productoQueEsta = new Producto("Un", "Producto");
		this.wishlist.aniadirProducto(productoQueEsta);
		assertTrue(this.wishlist.tieneElProducto(productoQueEsta));
	}
	
	@Test
	public void detectaCorrectamenteQueUnProductoInexistenteNoExisteEnWishlistVacia()
	{
		this.wishlist.vaciar();
		assertFalse(this.wishlist.tieneElProducto("Producto que ni loco esta"));
	}
	
}