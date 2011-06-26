package grupo4.wishlist;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Producto {
	/**
	 * Contiene los datos de un producto de la wishlist.
	 */
    @Id @GeneratedValue long id;
	
	@Basic
	private String nombre;
	@Basic
	private String link;

	public Producto()
	{
	}
	public Producto(String nombre2, String link) {
		this.setNombre(nombre2);
		this.setLink(link);
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
