package main.java.grupo4.clientes;

import main.java.grupo4.exceptions.ImposibleParsearJSONException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ParserJSON {

	private JSONParser parser;
	
	public ParserJSON()
	{
		this.parser = new JSONParser();
	}

	
	public String obtenerValorDeCampoJSON(String respuesta, String campo) throws org.json.simple.parser.ParseException
	{
		try
		{
			String retorno = obtenerJSONObject(respuesta).get(campo).toString(); 
			return retorno;
		}
		catch(NullPointerException e)
		{
			throw new ImposibleParsearJSONException();
		}
	}
	
	private JSONObject obtenerJSONObject(String respuesta)
	throws org.json.simple.parser.ParseException {
		return (JSONObject) this.parser.parse(respuesta);
	}
	
}
