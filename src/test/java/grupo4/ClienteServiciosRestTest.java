package test.java.grupo4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import main.java.grupo4.clientes.ClienteServiciosREST;
import main.java.grupo4.exceptions.ImposibleConsumirException;

import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.junit.Before;
import org.junit.Test;

public class ClienteServiciosRestTest {
	
	private HttpResponse Respuesta404;
	private BasicHttpResponse RespuestaValida;
	private ClienteServiciosREST CSRest;

	@Before
	public void condicionesIniciales() throws UnsupportedEncodingException
	{
		this.Respuesta404 = new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("", 0, 0), 404, "Mock"));
		this.RespuestaValida = new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("Mock", 3, 2), 200, "Mock"));
		this.RespuestaValida.setEntity(new StringEntity("Mock") );
		this.CSRest = new ClienteServiciosREST();
	}
	
	@Test(expected=ImposibleConsumirException.class)
	public void respuesta404TiraExcepcion() throws IOException
	{
		this.CSRest.obtenerTextoDeRespuesta(this.Respuesta404);
	}

	@Test
	public void detectaRespuestaInvalida()
	{
		assertTrue(this.CSRest.esRespuestaOK(this.RespuestaValida));
	}

	@Test
	public void extraeTextoDeRespuestaDeMock() throws IOException
	{
		assertEquals("Mock", this.CSRest.obtenerTextoDeRespuesta(this.RespuestaValida));
	}
	
}