package main.java.grupo4.tptacs;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


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
		if (response.getEntity() != null) {
			if (response.getEntity().getContentLength() != -1) {
				return(EntityUtils.toString(response.getEntity()));
			}
			
		}
		return "";
	}
		    
		    

	private static HttpResponse obtenerRespuesta(String URL) throws IOException, ClientProtocolException {
		return new DefaultHttpClient().execute(new HttpGet(URL));
	}	

	public static void main(String [ ] args)	{
		System.out.println(ClienteServiciosREST.pedir());
	}
}
