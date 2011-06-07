var appendDivAListaCategorias = function(id, name, divDondeAgregar)
{
	$("<div class=\'cat\' idCat=\'" + id + "\'>" + name + "<div style= </div>").appendTo(divDondeAgregar);
};

var regenerarCategorias = function(data, divAUsar)
{
    $(divAUsar).empty();
    $.each(data, function(key, val) {
		appendDivAListaCategorias(val.id, val.name, divAUsar);
    });
};

var regenerarProductos = function(data)
{
	$(".productos").empty();
    $.each(data, function(key, val) {
		$("<li> <div class=\"producto\" idProd=\"" + val.id + "\"> " + 
		"<a href=\"" + val.permalink + "\">"  + "$" + val.price 
		+ " -  " +  val.title +  "</a> </div></li>").appendTo('.productos');
    });
};


$(".cat").live('click', function() {   
    
    $(".listaDeCategorias").hide("highlight", 100);
    $(".pathToRoot").hide("highlight", 100);
    
    var idCategoria = $(this).attr("idCat");
	$.getJSON("https://api.mercadolibre.com/categories/" + idCategoria + "?callback=?", function(data) {
		regenerarCategorias(data[2].children_categories, ".listaDeCategorias");
		regenerarCategorias(data[2].path_from_root, ".pathToRoot");
	});
	$(".listaDeCategorias").show("highlight", 100);
    $(".pathToRoot").show("highlight", 100);
	
	
    
	$.getJSON("https://api.mercadolibre.com/sites/MLA/search?category=" +
			  idCategoria + "&callback=?", function(data) {
				regenerarProductos(data[2].results);
	});
});

var mostrarSinResultados = function()
{
    $(".tituloProductos").show();
    $(".productos").append("No hay resultados");
};




var busqueda = function(data)
{
		if(data[2].results.length==0)
		{
			mostrarSinResultados();
			return false;
		}
			regenerarProductos(data[2].results);
		    return true;
}

$('#busqueda').live('submit', function() {
	$(".productos").empty();
	$(".cat").remove();
    $.getJSON("https://api.mercadolibre.com/sites/MLA/search?q=" +
    		$("input:first").val() + "&callback=?",
    		function(data) {busqueda(data);});
	return false;
	}); 
	     
$('#volverAlIndice').live('submit', function() {
	$.getJSON("https://api.mercadolibre.com/sites/MLA/categories?callback=?", function(data) {
		regenerarCategorias(data[2], ".listaDeCategorias");
	});
});	 


// Carga inicial
$(document).ready(function(){
	$(".listaDeCategorias").show();
	$(".pathToRoot").show();
	$.getJSON("https://api.mercadolibre.com/sites/MLA/categories?callback=?", function(data) {
		regenerarCategorias(data[2], ".listaDeCategorias");
	});
	
});

