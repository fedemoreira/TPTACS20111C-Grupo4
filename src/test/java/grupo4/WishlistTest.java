package grupo4;

import static org.junit.Assert.assertTrue;
import grupo4.wishlist.Producto;
import grupo4.wishlist.Wishlist;

import org.junit.Before;
import org.junit.Test;

public class WishlistTest {
	

	private Wishlist wishlist;
    private Producto productoEjemplo;
	@Before
	public void condicionesIniciales()
	{
		this.wishlist = new Wishlist();
		this.productoEjemplo = new Producto("Producto");
	}
	
	@Test
	public void agregayQuitaBienUnProducto()
	{   
		this.wishlist.aniadirProducto(this.productoEjemplo); 
		this.wishlist.quitarProducto(this.productoEjemplo);
	}	
	@Test
	public void agregaySacaBienUnProducto()
	{   
		this.wishlist.aniadirProducto(this.productoEjemplo); 
		assertTrue(this.wishlist.sacameUnProducto()== this.productoEjemplo );
	}
		
}