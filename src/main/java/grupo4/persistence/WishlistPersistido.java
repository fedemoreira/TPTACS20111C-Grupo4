package grupo4.persistence;

import grupo4.exceptions.WishlistVaciaException;
import grupo4.wishlist.Producto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class WishlistPersistido {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String usuario;
	
	@OneToMany(cascade=CascadeType.PERSIST)
	private List<Producto> listaDeProductos;
	
	public WishlistPersistido()
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

}