package grupo4;

import grupo4.wishlist.Producto;

import org.junit.Before;
import org.junit.Test;

public class ProductoTest {
	
    private Producto productoDefecto;
    
	@Before
	public void condicionesIniciales()
	{
		this.productoDefecto = new Producto();
	}

	@Test
	public void unProductoSeCreaConElLinkPorDefecto()
	{   
		assert(this.productoDefecto.getLink() == "");
	}	

	@Test
	public void unProductoSeCreaConElNombrePorDefecto()
	{   
		assert(this.productoDefecto.getNombre() == "");
	}	
	
}