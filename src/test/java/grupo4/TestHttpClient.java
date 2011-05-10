package test.java.grupo4;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.swing.text.html.parser.Entity;

import main.java.grupo4.clientes.ClienteServiciosREST;
import main.java.grupo4.exceptions.ImposibleConsumirException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.junit.Before;
import org.junit.Test;

public class TestHttpClient {
	
	private HttpResponse Respuesta404;
	private BasicHttpResponse RespuestaValida;
	
	@Before
	public void condicionesIniciales() throws UnsupportedEncodingException
	{
		this.Respuesta404 = new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("", 0, 0), 404, "Mock"));
		this.RespuestaValida = new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("Mock", 3, 2), 200, "Mock"));
	}
	
	@Test(expected=ImposibleConsumirException.class)
	public void Respuesta404TiraExcepción() throws IOException
	{
		new ClienteServiciosREST().obtenerTextoDeRespuesta(this.Respuesta404);
	}
	
/*	@Test
	public void Largo() throws IOException
	{
		assertTrue(RespuestaValida.getEntity().getContentLength() != -1);
	}
	*/
	/*@Test
	public void ExtraeTextoDeRespuestaDeMock() throws IOException
	{
		String textoObtenido = new ClienteServiciosREST().obtenerTextoDeRespuesta(this.RespuestaValida);
		assertEquals(textoObtenido, "Mock");
	}*/
	
	
}
