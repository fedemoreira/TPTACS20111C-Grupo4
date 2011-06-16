package grupo4.wishlist;

import grupo4.exceptions.WishlistVaciaException;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class Wishlist {

	private String usuario;
	private List<Producto> listaDeProductos ;
	
	public Wishlist()
	{
		this.setListaDeProductos(new ArrayList<Producto>());
	}
	
	public void setUsuario( String user)
	{ 
		this.usuario = user;
	}
    
	public String getUsuario()
	{
		return this.usuario;
	}
	
	public void aniadirProducto(Producto productoAAniadir)
	{
		this.getListaDeProductos().add(productoAAniadir); 
	} 
	
	public void aniadirProducto(String nombre, String link)
	{
		this.getListaDeProductos().add(new Producto(nombre, link)); 
	} 
	
    public void quitarProducto(Producto productoEjemplo)
    {
        this.getListaDeProductos().remove(productoEjemplo);
    	
    }
    
    public Producto sacameUnProducto()
    {
    	if (this.getListaDeProductos().isEmpty()) 
    	{
    		throw new WishlistVaciaException();
    	}
    	Producto productoASacar = this.getListaDeProductos().get(this.getListaDeProductos().size() - 1);
    	this.getListaDeProductos().remove(productoASacar);
    	return productoASacar;
    }
    
    public Producto dameProducto(int indice)
    {
    	return this.getListaDeProductos().get(indice);
    }

	public void setListaDeProductos(List<Producto> listaDeProductos) {
		this.listaDeProductos = listaDeProductos;
	}

	public List<Producto> getListaDeProductos() {
		return this.listaDeProductos;
	}

	public String convertirAJson() {
		return new Gson().toJson(this);
		
	}

	public boolean tieneElProducto(String nombreDeProductoABuscar) {
		for(Producto producto : this.listaDeProductos)
			if (producto.getNombre().equals(nombreDeProductoABuscar))
				return true;
		return false;
	}

	public void vaciar() {
		this.setListaDeProductos(new ArrayList<Producto>());
	}
}