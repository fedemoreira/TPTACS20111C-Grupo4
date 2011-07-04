package grupo4.wishlist;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.google.gson.Gson;

@Entity
/**
 * Wishlist por usuario. El ID es el nombre de usuario (string)
 *
 */
public class WishlistPersistido {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String usuario;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
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

	public void quitarProducto(Producto productoABuscar)
	{
		this.getListaDeProductos().remove(productoABuscar);
	}

	/**
	 * Usa GSON para convertir la wishlist a JSON para su envío.
	 * @return
	 */
	public String convertirAJson() {
		return new Gson().toJson(this);
	}

	
	/**
	 * Vacía la lista de productos
	 */
	public void vaciar() {
		this.setListaDeProductos(new ArrayList<Producto>());
	}

	/**
	 * Busca un producto por nombre
	 * @param string
	 * @return
	 */
	private boolean tieneElProducto(String string) {
		for(Producto p : this.getListaDeProductos())
			if(p.getNombre() == string)
				return true;
		return false;
	}
    
}