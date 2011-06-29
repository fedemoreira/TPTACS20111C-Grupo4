package grupo4.wishlist;


import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class Wishlist {
	/**
	 * Contiene el nombre de usuario y la lista de productos de su wishlist
	 */
	private String usuario;

	private List<Producto> listaDeProductos ;

	public Wishlist()
	{
		this.setListaDeProductos(new ArrayList<Producto>());
	}

	/**
	 * Crea una Wishlist desde una Wishlist persistida.
	 * @param wishlistPersistido Wishlist persistida
	 */
	public Wishlist(WishlistPersistido wishlistPersistido) {
		this.setListaDeProductos(wishlistPersistido.getListaDeProductos());
		this.setUsuario(wishlistPersistido.getUsuario());
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
		Producto p = new Producto();
		p.setNombre(nombre);
		p.setLink(link);
		this.getListaDeProductos().add(p); 
	} 

	public void quitarProducto(Producto productoABuscar)
	{
		for(Producto producto : this.listaDeProductos)
			if (producto.getNombre().equals(productoABuscar.getNombre()))
				this.getListaDeProductos().remove(producto);
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

	/**
	 * Transforma una Wishlist en WishlistPersistido para persistir
	 * @return WishlistPersistido con usuario y productos de la Wishlist
	 */
	public WishlistPersistido getWishlistPersistido() {
		WishlistPersistido retorno = new WishlistPersistido();
		retorno.setListaDeProductos(this.getListaDeProductos());
		retorno.setUsuario(this.usuario);
		return retorno;
	}

	public boolean tieneElProducto(Producto productoAPersistir) {
		for(Producto producto : this.getListaDeProductos())
			if (producto.getNombre().equals(productoAPersistir.getNombre()))
				return true;
		return false;
	}

	public void agregarOQuitar(Producto productoAPersistir) {
		if(this.tieneElProducto(productoAPersistir))
		{
			this.quitarProducto(productoAPersistir);
		}
		else
		{
			this.aniadirProducto(productoAPersistir);
		}
	}
}