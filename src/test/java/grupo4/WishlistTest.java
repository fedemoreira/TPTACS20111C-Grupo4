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

public class WishlistTest {
	

	private Wishlist wishlist;

	@Before
	public void condicionesIniciales() throws UnsupportedEncodingException
	{
		this.wishlist = new Wishlist();
	
	}
	
