package main.java.grupo4.tptacs;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


public class ClienteServiciosREST {

	static public String pedir()
	{
		try
		{
			HttpResponse response = obtenerRespuesta("https://api.mercadolibre.com/sites/MLA/search?q=ipod");
			return obtenerTextoDeRespuesta(response);
		}
		catch(ClientProtocolException c)
		{
		}
		catch(IOException e)
		{
		}
		return "";
	}

	private static String obtenerTextoDeRespuesta(HttpResponse response) throws IOException {
		return response.getEntity().getContent().toString();
	}

	private static HttpResponse obtenerRespuesta(String URL) throws IOException, ClientProtocolException {
		return new DefaultHttpClient().execute(new HttpGet(URL));
	}	

	public static void main(String [ ] args)	{
		System.out.println(ClienteServiciosREST.pedir());
	}
}
