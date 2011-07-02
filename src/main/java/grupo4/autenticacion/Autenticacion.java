package main.java.grupo4.autenticacion;


import grupo4.clientes.ClienteServiciosRest;

public class Autenticacion{

	
public String requestToFacebook(String codigo){
	   
		ClienteServiciosRest cliente= new ClienteServiciosRest();
		String respuesta= new String();
		String token= new String();
		String urlCode = new String("https://graph.facebook.com/oauth/access_token?client_id=140959625981357&redirect_uri=http://localhost:8080&client_secret=2313fe1bac3a941de3a714e5a0c0eafc&code="
			   + codigo);
	    respuesta = cliente.pedir(urlCode);
	    token=respuesta.substring(0,respuesta.indexOf('&') );
	  
       	return token; 
       	}
     
public Autenticacion()
{
			
}

}
