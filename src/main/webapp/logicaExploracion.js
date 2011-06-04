const direccionDelServlet = "ServletMercadoLibre";

var appendDivAListaCategorias = function(id, name, divDondeAgregar,sangria)
{
	$("<div class=\'cat\' idCat=\'" + id + "\'>" + name + "<div style= </div>").appendTo(divDondeAgregar);
};

var regenerarPathToRoot = function(datos)
{   var numeroCatPadres = 0; 
    $(".tituloPathToRoot").show();
    $("<div class=\'root\'\'> Home </div>").appendTo('.pathToRoot');
    $.each(datos.path_from_root, function(key, data) {
    	appendDivAListaCategorias(data.id, data.name, '.pathToRoot',numeroCatPadres*10);
		numeroCatPadres += 1;
    });
};

var regenerarCategorias = function(data)
{
    $(".tituloCategorias").show();
    $.each(data, function(key, val) {
		appendDivAListaCategorias(val.id, val.name, '.listaDeCategorias');
    });
};

var limpiarMenuProductos = function()
{   var i = 0;
	for (i = (document.menu.productos2.options.length-1); i >= 0; i -=1)
		{document.menu.productos2.options[i]= null;};
}

var regenerarProductos = function(data)
{
	
        $(".tituloProductos").hide();
        $(".productos").hide();
        limpiarMenuProductos();
        $.each(data, function(key, val) {
		$("<li> <div class=\"producto\" idProd=\"" + val.id + "\"> " + 
		"<a href=\"" + val.permalink + "\">"  + "$" + val.price 
		+ " -  " +  val.title +  "</a> </div></li>").appendTo('.productos');
		nuevaopcion = new Option("$" + val.price + " -  " +  val.title ,val.id);  
		document.menu.productos2.options[document.menu.productos2.options.length]= nuevaopcion;
    });
};

var obtenerCategorias = function(datos)
{	
    var items = [];
    $(".pathToRoot").empty();
    regenerarCategorias(datos.children_categories);
    regenerarPathToRoot(datos);
};

var ocultarTitulos = function()
{
    $(".tituloProductos").hide();
    $(".tituloCategorias").hide();
    $(".tituloPathToRoot").hide();
};

var limpiar = function()
{
    $(".pathToRoot").empty();
    $(".listaDeCategorias").empty();
    $(".productos").empty();
    ocultarTitulos();
};

	

$(".cat").live('click', function() {
    limpiar();
    var idCategoria = $(this).attr("idCat");
	$.getJSON("https://api.mercadolibre.com/categories/" + idCategoria + "?callback=?", function(data) {
		obtenerCategorias(data[2]);
	});
	$.getJSON("https://api.mercadolibre.com/sites/MLA/search?category=" +
			  idCategoria + "&callback=?", function(data) {
				regenerarProductos(data[2].results);
	});
});

$(".root").live('click', function() {
    limpiar();
	$.getJSON("https://api.mercadolibre.com/sites/MLA/categories?callback=?", function(data) {
		regenerarCategorias(data[2]);
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
	limpiar();
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
	ocultarTitulos();
	$.getJSON("https://api.mercadolibre.com/sites/MLA/categories?callback=?", function(data) {
		regenerarCategorias(data[2]);
	});
});

