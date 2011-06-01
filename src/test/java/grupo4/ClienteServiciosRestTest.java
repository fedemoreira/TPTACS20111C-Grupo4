package grupo4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import grupo4.clientes.ClienteServiciosRest;
import grupo4.exceptions.ImposibleConsumirException;

import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.junit.Before;
import org.junit.Test;

public class ClienteServiciosRestTest {
	
	private HttpResponse respuesta404;
	private BasicHttpResponse respuestaValida;
	private ClienteServiciosRest clienteRest;

	@Before
	public void condicionesIniciales() throws UnsupportedEncodingException
	{
		this.respuesta404 = new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("", 0, 0), 404, "Mock"));
		this.respuestaValida = new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("Mock", 3, 2), 200, "Mock"));
		this.respuestaValida.setEntity(new StringEntity("Mock") );
		this.clienteRest = new ClienteServiciosRest();
	}
	
	@Test(expected=ImposibleConsumirException.class)
	public void respuesta404TiraExcepcion()
	{
		this.clienteRest.obtenerTextoDeRespuesta(this.respuesta404);
	}

	@Test
	public void detectaRespuestaInvalida()
	{
		assertTrue(this.clienteRest.esRespuestaOk(this.respuestaValida));
	}

	@Test
	public void extraeTextoDeRespuestaDeMock()
	{
		assertEquals("Mock", this.clienteRest.obtenerTextoDeRespuesta(this.respuestaValida));
	}
	
}
