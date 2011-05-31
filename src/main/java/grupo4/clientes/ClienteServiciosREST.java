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


public class ClienteServiciosREST {

	private DefaultHttpClient clienteHttp;

	public ClienteServiciosREST()
	{
		this.clienteHttp = new DefaultHttpClient(new ThreadSafeClientConnManager());
		
	}
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

	public String obtenerTextoDeRespuesta(HttpResponse response) throws IOException {
		if (tieneEntidadValida(response)) {
				return(extraerTextoDeRespuestaValida(response));
		}
		throw new ImposibleConsumirException();
	}

	private boolean tieneEntidadValida(HttpResponse response) {
		return (response.getEntity() != null) && this.obtenerLargoDeRespuesta(response) != 1;
	}

	private String extraerTextoDeRespuestaValida(HttpResponse response) throws IOException {
		return EntityUtils.toString(response.getEntity());
	}
			
	public HttpResponse obtenerRespuesta(String url) throws IOException, ClientProtocolException {
		HttpResponse respuesta = ejecutarGet(url);
		if (!esRespuestaOK(respuesta))
		{
			throw new ImposibleConsumirException();
		}
		return respuesta;
	}

	private int obtenerCodigoDeEstado(HttpResponse respuesta) {
		return respuesta.getStatusLine().getStatusCode();
	}

	private HttpResponse ejecutarGet(String url) throws IOException,
			ClientProtocolException {
		return this.clienteHttp.execute(new HttpGet(url));
	}

	public boolean esRespuestaOK(HttpResponse respuesta) {
		return obtenerCodigoDeEstado(respuesta) == 200;
	}	

	private long obtenerLargoDeRespuesta(HttpResponse response) {
		return response.getEntity().getContentLength();
	}
}

