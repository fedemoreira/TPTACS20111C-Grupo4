package main.java.grupo4.clientes;

import java.io.IOException;

import main.java.grupo4.exceptions.ImposibleConsumirException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


public class ClienteServiciosREST {

	public String pedir() throws IOException
	{
		try
		{
			HttpResponse response = this.obtenerRespuesta("https://api.mercadolibre.com/sites/MLA/search?q=asd");
			return this.obtenerTextoDeRespuesta(response);
		}
		catch(ClientProtocolException c)
		{
			return "Imposible obtener respuesta.";
		}
	}

	public String obtenerTextoDeRespuesta(HttpResponse response) throws IOException {
		if (response.getEntity() != null) {
			if (response.getEntity().getContentLength() != -1) {
				return(EntityUtils.toString(response.getEntity()));
			}
		}
		throw new ImposibleConsumirException("Error al obtener texto");
	}
			
		    
		    

	public HttpResponse obtenerRespuesta(String URL) throws IOException, ClientProtocolException {
		HttpResponse respuesta = new DefaultHttpClient().execute(new HttpGet(URL));
		if (respuesta.getStatusLine().getStatusCode() == 404)
		{
			throw new ImposibleConsumirException("404");
		}
		return respuesta;
	}	

	public static void main(String [ ] args) throws IOException	{
		System.out.println(new ClienteServiciosREST().pedir());
	}
}
