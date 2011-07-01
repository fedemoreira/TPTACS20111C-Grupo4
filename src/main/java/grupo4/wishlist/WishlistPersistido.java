package grupo4.wishlist;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.google.gson.Gson;

@Entity
public class WishlistPersistido {
/**
 * Wishlist sin comportamiento 
 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String usuario;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Producto> listaDeProductos;
	
	public WishlistPersistido()
	{
	}

	public WishlistPersistido(String user) {
		this();
		this.usuario = user;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setListaDeProductos(List<Producto> listaDeProductos) {
		this.listaDeProductos = listaDeProductos;
	}

	public List<Producto> getListaDeProductos() {
		return listaDeProductos;
	}
	
	
	


	public void aniadirProducto(Producto productoAAniadir)
	{
		this.getListaDeProductos().add(productoAAniadir); 
	} 

	public void aniadirProducto(String nombre, String link)
	{
		Producto p = new Producto(nombre, link);
		this.getListaDeProductos().add(p); 
	} 

	public void quitarProducto(Producto productoABuscar)
	{
		for(Producto producto : this.listaDeProductos)
			if (producto.getNombre().equals(productoABuscar.getNombre()))
				this.getListaDeProductos().remove(producto);
	}


	public String convertirAJson() {
		return new Gson().toJson(this);

	}

	public void vaciar() {
		this.setListaDeProductos(new ArrayList<Producto>());
	}

	public Producto dameProducto(int i) {
		return this.listaDeProductos.get(i);
	}
    
}