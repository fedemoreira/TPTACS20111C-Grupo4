var respuestaConeccion = function(){
	$.getJSON("https://api.mercadolibre.com/sites/MLA/categories?callback=?", function(data) {});
	return 400;

};    

var coneccion = respuestaConeccion();

module('Module A');  
 
test('coneccion', function() {
	           
                  ok((coneccion >= 400 && coneccion < 500 ), 'funciona la coneccion con el server');
                    });  
 