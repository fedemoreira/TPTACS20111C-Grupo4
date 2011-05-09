package test.java.grupo4;

import java.io.IOException;

import main.java.grupo4.clientes.ClienteServiciosREST;
import main.java.grupo4.exceptions.ImposibleConsumirException;

import org.apache.http.ProtocolVersion;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.junit.Test;

public class TestHttpClient {
	@Test(expected=ImposibleConsumirException.class)
	public void testBasico() throws IOException
	{
		new ClienteServiciosREST().obtenerTextoDeRespuesta(new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("", 0, 0), 404, "Mock")));
	}
}
