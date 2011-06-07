var respuestaConeccion = function(){
	$.getJSON("https://api.mercadolibre.com/sites/MLA/categories?callback=?", function(data) {});
	return data[0];

};    

var conexion = respuestaConexion();

module('Module A');  
 
test('Si hay internet, el API de Mercado Libre está levantado', function() {
                  ok((conexion >= 400 && conexion < 500 ), 'funciona la conexion con el server');
});  
 
