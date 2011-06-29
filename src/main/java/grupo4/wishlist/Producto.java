package grupo4.wishlist;

import javax.jdo.annotations.PersistenceCapable;  
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Persistent; 
import javax.jdo.annotations.IdGeneratorStrategy;  
import com.google.appengine.api.datastore.Key;

<<<<<<< HEAD
@PersistenceCapable(identityType = IdentityType.APPLICATION)  
=======
import com.google.appengine.api.datastore.Key;


@Entity
>>>>>>> a1c43209c2f4f3edee79e4321a4404aed0c035c1
public class Producto {
	/**
	 * Contiene los datos de un producto de la wishlist.
	 */
<<<<<<< HEAD
	@PrimaryKey  
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)  
    private Key key;
	
	@Persistent
	private String nombre;
	@Persistent
=======
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key id;
    private String nombre;
>>>>>>> a1c43209c2f4f3edee79e4321a4404aed0c035c1
	private String link;

	public Producto()
	{
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
