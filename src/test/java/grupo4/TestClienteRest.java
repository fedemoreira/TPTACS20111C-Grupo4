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

public class TestClienteRest {
	
	private HttpResponse Respuesta404;
	private BasicHttpResponse RespuestaValida;
	
	@Before
	public void condicionesIniciales() throws UnsupportedEncodingException
	{
		this.Respuesta404 = new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("", 0, 0), 404, "Mock"));
		this.RespuestaValida = new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("Mock", 3, 2), 200, "Mock"));
		this.RespuestaValida.setEntity(new StringEntity("Mock") );
	}
	
	@Test(expected=ImposibleConsumirException.class)
	public void Respuesta404TiraExcepcion() throws IOException
	{
		new ClienteServiciosREST().obtenerTextoDeRespuesta(this.Respuesta404);
	}

	
	@Test
	public void DetectaRespuestaValida() throws IOException
	{
		String textoObtenido = new ClienteServiciosREST().obtenerTextoDeRespuesta(this.RespuestaValida);
		assertEquals(textoObtenido, "Mock");
	}
	
	@Test
	public void DetectaLargoDeRespuestaValida()
	{
		assertEquals(new ClienteServiciosREST().obtenerLargoDeRespuesta(RespuestaValida), 4);
	}
	
	@Test
	public void DetectaRespuestaInvalida() throws IOException
	{
		assertTrue(new ClienteServiciosREST().esRespuestaOK(RespuestaValida));
	}

	@Test
	public void ExtraeTextoDeRespuestaDeMock() throws IOException
	{
		String textoObtenido = new ClienteServiciosREST().obtenerTextoDeRespuesta(this.RespuestaValida);
		assertEquals(textoObtenido, "Mock");
	}
}
