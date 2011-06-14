package grupo4;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import grupo4.wishlist.Producto;
import grupo4.wishlist.Wishlist;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
		
	
	@Test
	public void sePasaAJsonYObtieneElUltimoProducto()
	{
		this.wishlist.aniadirProducto(this.productoEjemplo); 
		this.wishlist = new Gson().fromJson(this.wishlist.convertirAJson(), Wishlist.class);
		assertEquals("Producto", this.wishlist.dameProducto(0).getNombre());
		}
	
}