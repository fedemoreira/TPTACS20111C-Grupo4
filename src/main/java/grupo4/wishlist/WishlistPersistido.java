package grupo4.wishlist;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class WishlistPersistido {
/**
 * Wishlist sin comportamiento 
 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String usuario;
	
	@OneToMany(cascade=CascadeType.PERSIST)
	private List<Producto> listaDeProductos;
	
	public WishlistPersistido()
	{
		this.setListaDeProductos(new ArrayList<Producto>());
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
	
    
}