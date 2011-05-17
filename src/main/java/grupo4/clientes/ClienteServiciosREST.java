package main.java.grupo4.clientes;

import java.io.IOException;
import java.text.ParseException;

import main.java.grupo4.exceptions.ImposibleConsumirException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;


public class ClienteServiciosREST {

	private DefaultHttpClient clienteHTTP;
	private JSONParser parser;

	public ClienteServiciosREST()
	{
		this.clienteHTTP= new DefaultHttpClient();
		this.parser = new JSONParser();
	}
	public String pedir(String URL) throws IOException
	{
		try
		{
			return this.obtenerTextoDeRespuesta(this.obtenerRespuesta(URL));
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
		throw new ImposibleConsumirException("Error al obtener texto");
	}

	private boolean tieneEntidadValida(HttpResponse response) {
		return (response.getEntity() != null) && this.obtenerLargoDeRespuesta(response) != 1;
	}

	private String extraerTextoDeRespuestaValida(HttpResponse response) throws IOException {
		return EntityUtils.toString(response.getEntity());
	}
			
	public HttpResponse obtenerRespuesta(String URL) throws IOException, ClientProtocolException {
		HttpResponse respuesta = ejecutarGet(URL);
		if (!esRespuestaOK(respuesta))
		{
			throw new ImposibleConsumirException("" + obtenerCodigoDeEstado(respuesta));
		}
		return respuesta;
	}

	public int obtenerCodigoDeEstado(HttpResponse respuesta) {
		return respuesta.getStatusLine().getStatusCode();
	}

	private HttpResponse ejecutarGet(String URL) throws IOException,
			ClientProtocolException {
		return this.clienteHTTP.execute(new HttpGet(URL));
	}

	public boolean esRespuestaOK(HttpResponse respuesta) {
		return obtenerCodigoDeEstado(respuesta) == 200;
	}	

	public long obtenerLargoDeRespuesta(HttpResponse response) {
		return response.getEntity().getContentLength();
	}

	public String obtenerValorDeCampoJSON(String respuesta, String campo) throws org.json.simple.parser.ParseException
	{
		return obtenerJSONObject(respuesta).get("id").toString();           
	}
	
	private JSONObject obtenerJSONObject(String respuesta)
	throws org.json.simple.parser.ParseException {
		return (JSONObject) this.parser.parse(respuesta);
	}


}

