package main.java.grupo4.clientes;

import java.io.IOException;

import main.java.grupo4.exceptions.ImposibleConsumirException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.util.EntityUtils;


public class ClienteServiciosREST {

	public String pedir(String URL) throws IOException
	{
		try
		{
			HttpResponse response = this.obtenerRespuesta(URL);
			return this.obtenerTextoDeRespuesta(response);
		}
		catch(ClientProtocolException c)
		{
			return "Imposible obtener respuesta.";
		}
	}

	public String obtenerTextoDeRespuesta(HttpResponse response) throws IOException {
		if (response.getEntity() != null) {
			if (this.obtenerLargoDeRespuesta(response) != -1) {
				return(EntityUtils.toString(response.getEntity()));
			}
		}
		throw new ImposibleConsumirException("Error al obtener texto");
	}
			
		    
		    

	public HttpResponse obtenerRespuesta(String URL) throws IOException, ClientProtocolException {
		HttpResponse respuesta = new DefaultHttpClient().execute(new HttpGet(URL));
		if (!esRespuestaOK(respuesta))
		{
			throw new ImposibleConsumirException("" + respuesta.getStatusLine().getStatusCode());
		}
		return respuesta;
	}

	public boolean esRespuestaOK(HttpResponse respuesta) {
		return respuesta.getStatusLine().getStatusCode() == 200;
	}	

	public long obtenerLargoDeRespuesta(HttpResponse response) {
		return response.getEntity().getContentLength();
	}

}
