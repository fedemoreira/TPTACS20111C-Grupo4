package grupo4.wishlist;

public class Producto {
	private String nombre;

	public Producto(String nombreProducto)
	{
		this.nombre = nombreProducto;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return this.nombre;
	}
}
