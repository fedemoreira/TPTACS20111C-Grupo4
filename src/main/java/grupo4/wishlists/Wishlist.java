package grupo4.Wishlist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Wishlist {

	private String usuario;
	private List<String> listaDeProductos ;
	
	public Wishlist()
	{
		this.listaDeProductos = new ArrayList<String>();
	}
	
	public void setUsuario( String user)
	{ 
		this.usuario = user;
	}
    
	public String getUsuario()
	{
		return this.usuario;
	}
	
	public Boolean aniadirProducto(String producto)
	{
		return this.listaDeProductos.add(producto); 
	} 
    public Boolean quitarProducto(String producto)
    {
        return this.listaDeProductos.remove(producto);
    	
    }
    
    public String sacameUnProducto()
    {
    	int indice = this.listaDeProductos.size() - 1;
    	if (indice == -1) 
    	{
    		return "";
    	}
    	String respuesta = this.listaDeProductos.get(indice);
    	this.listaDeProductos.remove(indice);
    	return respuesta;
    }
    
    public String dameProducto(int indice)
    {
    	return this.listaDeProductos.get(indice);
    }
}