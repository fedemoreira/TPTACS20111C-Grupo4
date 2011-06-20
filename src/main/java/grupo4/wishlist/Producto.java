package grupo4.wishlist;

import javax.jdo.annotations.PersistenceCapable;  
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Persistent; 
import javax.jdo.annotations.IdGeneratorStrategy;  
import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION)  
public class Producto {
	/**
	 * Contiene los datos de un producto de la wishlist.
	 */
	@PrimaryKey  
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)  
    private Key key;
	
	@Persistent
	private String nombre;
	@Persistent
	private String link;

	/**
	 * Los valores por defecto son ""
	 */
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
