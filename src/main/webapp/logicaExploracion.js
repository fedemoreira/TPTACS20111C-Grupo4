var appendDivAListaCategorias = function(id, name, divDondeAgregar)
{
	$("<div class=\'cat\' idCat=\'" + id + "\'>" + name + "<div style= </div>").appendTo(divDondeAgregar);
};

var appendDivAListaProductos = function(val, divDondeAgregar)
{
	$("<li> <div class=\"producto\" idProd=\"" + val.id + "\"> " + 	"<a href=\"" + val.permalink + "\">"  
		+ "$" + val.price + " -  " +  val.title +  "</a> </div></li>").appendTo(divDondeAgregar);
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
		appendDivAListaProductos(val, ".productos");
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

var limpiar = function()
{
	$(".productos").empty();
	$(".cat").remove();
}

$('#busqueda').live('submit', function() {
	limpiar();
    $.getJSON("https://api.mercadolibre.com/sites/MLA/search?q=" + $("input:first").val() + "&callback=?",
    		function(data) { busqueda(data); });
	return false;
	}); 
	     
var regenerarRoot = function()
{
	limpiar();
	$(".listaDeCategorias").show();
	$(".pathToRoot").show();
	$.getJSON("https://api.mercadolibre.com/sites/MLA/categories?callback=?", function(data) {
		regenerarCategorias(data[2], ".listaDeCategorias");
	});
}

$('#volverAlIndice').live('submit', function() {
	regenerarRoot();
});	 


// Carga inicial
$(document).ready(function(){
	regenerarRoot();
});

