package grupo4.wishlist;

public class Producto {
	private String nombre;
	private String link;

	public Producto()
	{
		this.setNombre("");
		this.setLink("");
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