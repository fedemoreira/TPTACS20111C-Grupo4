package test.java.grupo4;

import static org.junit.Assert.assertEquals;
import main.java.grupo4.clientes.ParserJSON;
import main.java.grupo4.exceptions.ImposibleParsearJSONException;

import org.junit.Before;
import org.junit.Test;

public class ParserJSONTest {

	private ParserJSON parser;
	private String jsonstring;

	@Before
	public void condicionesIniciales()
	{
		this.parser = new ParserJSON();
		this.jsonstring = "{\"id\":36484980,\"power_seller_status\":\"platinum\",\"car_dealer\":false,\"real_estate_agency\":false}";
	}
	@Test
	public void testObtenerCampoJson() throws org.json.simple.parser.ParseException
	{
		assertEquals("36484980", this.parser.obtenerValorDeCampoJSON(this.jsonstring, "id"));
	}
	
	@Test(expected=ImposibleParsearJSONException.class)
	public void testObtenerCampoJsonImposible() throws org.json.simple.parser.ParseException
	{
		assertEquals("36484980", this.parser.obtenerValorDeCampoJSON(this.jsonstring, "noexiste"));
	}
	
}
