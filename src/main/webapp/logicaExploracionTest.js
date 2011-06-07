 var respuestaConeccion = function(){
	
	jQuery.getJSON("https://api.mercadolibre.com/sites/MLA/categories?callback=?", function(data) {
		
		return data[0];
     
	});
    
	
}; 

var conexion = respuestaConeccion();

var mockRespuestaBusquedaVacia = ([200, {"X-MLAPI-Version":"1.5.18","ETag":"09d3d615a7c26a0c1bcf4eff94087f7b","X-Echelon-Sort":"55","Vary":"Accept,Accept-Encoding","Content-Type":"application/json;charset=UTF-8","Cache-Control":"max-age=300"}, 
                                  {"site_id":"MLA","query":"''","paging":{"total":0,"offset":0,"limit":30},"results":[],"matching_catalog_products":[],"sort":{"id":"relevance","name":"Más relevantes"},"available_sorts":
                                	  [{"id":"price_asc","name":"Menor precio"},{"id":"price_desc","name":"Mayor precio"}],"filters":[],"available_filters":[],"search_accuracy":"fuzzy"}]);

var mockRespuestaBusquedaNoVacia = ([200, {"X-MLAPI-Version":"1.5.18","ETag":"09d3d615a7c26a0c1bcf4eff94087f7b","X-Echelon-Sort":"55","Vary":"Accept,Accept-Encoding","Content-Type":"application/json;charset=UTF-8","Cache-Control":"max-age=300"},
                                     {"site_id":"MLA","query":"''","paging":{"total":0,"offset":0,"limit":30},"results":[],"matching_catalog_products":[],"sort":{"id":"relevance","name":"Más relevantes"},"available_sorts":
                                    	 [{"id":"price_asc","name":"Menor precio"},{"id":"price_desc","name":"Mayor precio"}],"filters":[],"available_filters":[],"search_accuracy":"fuzzy"}]);



$(document).ready(function() { 
module('Module A');  

	asyncTest('Si hay internet, el API de Mercado Libre esta levantado', function() {
 
	  setTimeout(function(){
	    ok((conexion >= 200 && conexion < 300 ), 'funciona la conexion con el server');
	    start();
	  }, 2000);
	}); 

module('Module B'); 

	asyncTest("a test", function() {
	  setTimeout(function(){
	    ok(true, "always fine");
	    start();
	  }, 13);
	});


module('Module C');
      

      asyncTest("busquedaConResultadoVacio",function() {
	  setTimeout(function(){
		    ok(!(busqueda(mockRespuestaBusquedaVacia)), "Reconoce una busqueda resultado vacio");
		    start();
		  }, 2000);
		});
      
module('Module D');
 
      asyncTest("busquedaConResultadoNoVacio",function() {
    	     	  setTimeout(function(){
    		    ok(busqueda(mockRespuestaBusquedaNoVacia), "Reconoce una busqueda resultado no vacio");
    		    start();
    		  }, 2000);
    	     	  
    		});     

});