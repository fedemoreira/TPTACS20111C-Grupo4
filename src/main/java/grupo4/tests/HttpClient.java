package main.java.grupo4.tests;

import java.io.IOException;

import main.java.grupo4.clientes.ClienteServiciosREST;
import main.java.grupo4.exceptions.ImposibleConsumirException;

import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.junit.Test;

public class HttpClient {
	@Test(expected=ImposibleConsumirException.class)
	public void testBasico() throws IOException
	{
		new ClienteServiciosREST().obtenerTextoDeRespuesta(new BasicHttpResponse(new BasicStatusLine(null, 404, "Mock")));
	}
}
