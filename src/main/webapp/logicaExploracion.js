const direccionDelServlet = "ServletMercadoLibre";

var appendDivAListaCategorias = function(id, name, divDondeAgregar)
{
	$("<div class=\'cat\' idCat=\'" + id + "\'>" + name + "<div style= </div>").appendTo(divDondeAgregar);
};

var regenerarPathToRoot = function(datos)
{   
    $(".pathToRoot").toggle("highlight", 400);
    $(".pathToRoot").empty();
    $.each(datos.path_from_root, function(key, data) {
    	appendDivAListaCategorias(data.id, data.name, '.pathToRoot');
    });
    $(".pathToRoot").toggle("highlight", 400);
};

var regenerarCategorias = function(data)
{
    $.each(data, function(key, val) {
		appendDivAListaCategorias(val.id, val.name, '.listaDeCategorias');
    });
};

var regenerarProductos = function(data)
{
        $.each(data, function(key, val) {
		$("<li> <div class=\"producto\" idProd=\"" + val.id + "\"> " + 
		"<a href=\"" + val.permalink + "\">"  + "$" + val.price 
		+ " -  " +  val.title +  "</a> </div></li>").appendTo('.productos');
    });
};


$(".cat").live('click', function() {   
    
    $(".productos").empty();
    $(".listaDeCategorias").toggle("highlight", 400);
    $(".listaDeCategorias").empty();
    var idCategoria = $(this).attr("idCat");
	$.getJSON("https://api.mercadolibre.com/categories/" + idCategoria + "?callback=?", function(data) {
		regenerarCategorias(data[2].children_categories);
		regenerarPathToRoot(data[2]);
	});
	$(".listaDeCategorias").toggle("highlight", 400);
	
	
    
	$.getJSON("https://api.mercadolibre.com/sites/MLA/search?category=" +
			  idCategoria + "&callback=?", function(data) {
				regenerarProductos(data[2].results);
	});
});

var mostrarSinResultados = function()
{
    $(".tituloProductos").show();
    $(".productos").append("No hay resultados");
}

var busqueda = function(valor)
{
$.getJSON("https://api.mercadolibre.com/sites/MLA/search?q=" +
		valor + "&callback=?", function(data) {
		if(data[2].results.length==0)
		{
		mostrarSinResultados();
		return false;
		}
		regenerarProductos(data[2].results);
		});
}

$('#busqueda').live('submit', function() {
	$(".productos").empty();
	$(".cat").remove();
    busqueda($("input:first").val());
	return false;
	}); 
	     
$('#volverAlIndice').live('submit', function() {
	$.getJSON("https://api.mercadolibre.com/sites/MLA/categories?callback=?", function(data) {
		regenerarCategorias(data[2]);
	});
});	 


// Carga inicial
$(document).ready(function(){
	
	$.getJSON("https://api.mercadolibre.com/sites/MLA/categories?callback=?", function(data) {
		regenerarCategorias(data[2]);
	});
	
});

