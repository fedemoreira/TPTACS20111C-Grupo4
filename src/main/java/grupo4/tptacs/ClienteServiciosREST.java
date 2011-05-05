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
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet("https://api.mercadolibre.com/sites/MLA/search?q=ipod");
		HttpResponse response = httpclient.execute(httpget);
		return response.getEntity().getContent().toString();
		}
		catch(ClientProtocolException c)
		{
		}
		catch(IOException e)
		{
		}
		return "";
	}	
	
	public static void main(String [ ] args)	{
			System.out.println(ClienteServiciosREST.pedir());
	}
}
