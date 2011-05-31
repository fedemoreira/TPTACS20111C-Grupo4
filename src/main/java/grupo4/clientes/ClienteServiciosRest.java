package main.java.grupo4.clientes;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import main.java.grupo4.exceptions.ImposibleConsumirException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.util.EntityUtils;


/**
 * Realiza pedidos HTTP y permite obtener el texto de respuesta.
 */
public class ClienteServiciosRest {

	private DefaultHttpClient clienteHttp;
	
	public ClienteServiciosRest()
	{
		this.clienteHttp = new DefaultHttpClient(new ThreadSafeClientConnManager());		
	}
	
	/**
	 * Ejecuta un get en la url de parametro.
	 * 
	 * @param url La URL que se utiliza para el pedido
	 * @return Contenido de la respuesta del pedido.
	 */
	public String pedir(String url) throws IOException
	{
		try
		{
			String respuesta = this.obtenerTextoDeRespuesta(this.obtenerRespuesta(url));
			this.clienteHttp.getConnectionManager().closeIdleConnections(1, TimeUnit.MILLISECONDS);
			return respuesta;
		}
		catch(ClientProtocolException c)
		{
			return "Imposible obtener respuesta.";
		}
		catch(ImposibleConsumirException c)
		{
			return "Imposible consumir Servicio REST.";
		}
	}

	/**
	 * Obtiene la respuesta desde un HttpResponse
	 * @param response Objeto desde donde se obtiene el texto de respuesta
	 * @return String con el contenido de la respuesta
	 * @throws IOException
	 */
	public String obtenerTextoDeRespuesta(HttpResponse response) throws IOException {
		if (tieneEntidadValida(response)) {
				return(extraerTextoDeRespuestaValida(response));
		}
		throw new ImposibleConsumirException();
	}

	private boolean tieneEntidadValida(HttpResponse response) {
		return (response.getEntity() != null) && 
		        response.getEntity().getContentLength() != 1;
	}

	private String extraerTextoDeRespuestaValida(HttpResponse response) throws IOException {
		return EntityUtils.toString(response.getEntity());
	}
	/**
	 * Obtiene el objeto HTTPResponse de un pedido a una URL de parametro.		
	 * @param url URL sobre la que efectuar el pedido.
	 * @return HTTPResponse con la respuesta del pedido.
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public HttpResponse obtenerRespuesta(String url) throws IOException, ClientProtocolException {
		HttpResponse respuesta = this.clienteHttp.execute(new HttpGet(url));
		if (!esRespuestaOK(respuesta))
		{
			throw new ImposibleConsumirException();
		}
		return respuesta;
	}

	private int obtenerCodigoDeEstado(HttpResponse respuesta) {
		return respuesta.getStatusLine().getStatusCode();
	}

	public boolean esRespuestaOK(HttpResponse respuesta) {
		return obtenerCodigoDeEstado(respuesta) == 200;
	}
}

