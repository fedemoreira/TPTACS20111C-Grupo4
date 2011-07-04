package grupo4.wishlist;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;
import com.google.gson.annotations.Expose;


@Entity
public class Producto {
	/**
	 * Contiene los datos de un producto de la wishlist.
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key id;
    @Expose
	private String nombre;
	@Expose
    private String link;

	public Producto()
	{
	}
	
	public Producto(String nombre, String link) {
		this.nombre = nombre;
		this.link = link;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return this.nombre;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getLink() {
		return this.link;
	}
}
