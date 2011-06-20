var appendDivAListaCategorias = function(id, name, divDondeAgregar)
{
	$("<div class=\'cat\' idCat=\'" + id + "\'>" + name + "<div style= </div>").appendTo(divDondeAgregar);
};

var appendDivAListaProductos = function(val, divDondeAgregar)
{
	$("<li> <div class=\"producto\" " + "link=\"" + val.permalink + "\"> " + "$" + val.price + " -  " +  val.title +  "</a> </div></li>").appendTo(divDondeAgregar);
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

var toggleCategorias = function()
{
	$(".listaDeCategorias").toggle("highlight", 100);
	$(".pathToRoot").toggle("highlight", 100);
};


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
};

var limpiar = function()
{
	$(".productos").empty();
	$(".cat").remove();
};

var regenerarRoot = function()
{
	limpiar();
	$(".listaDeCategorias").show();
	$(".pathToRoot").show();
	$.getJSON("https://api.mercadolibre.com/sites/MLA/categories?callback=?", function(data) {
		regenerarCategorias(data[2], ".listaDeCategorias");
	});
};


var obtenerCategorias = function(idCategoria)
{
	$.getJSON("https://api.mercadolibre.com/categories/" + idCategoria + "?callback=?", function(data) 
			{
				regenerarCategorias(data[2].children_categories, ".listaDeCategorias");
				regenerarCategorias(data[2].path_from_root, ".pathToRoot");
			});
};

var obtenerProductos = function(idCategoria)
{
	$.getJSON("https://api.mercadolibre.com/sites/MLA/search?category=" + idCategoria + "&callback=?", function(data) 
			{
				regenerarProductos(data[2].results);
			});
};

$(".cat").live('click', function() {   
	toggleCategorias();
	obtenerCategorias($(this).attr("idCat"));
	toggleCategorias();    
	obtenerProductos($(this).attr("idCat"));
});


$('#busqueda').live('submit', function() {
	limpiar();
	$.getJSON("https://api.mercadolibre.com/sites/MLA/search?q=" + $("input:first").val() + "&callback=?", function(data) 
			{ 
				busqueda(data); 
			});
	return false;
}); 



$('.producto').live('click', function() {

    $.post("ServletWishlist", { nombre: $(this).html(), link: $(this).attr("link"), user: "userPrueba" },
 function(data) {
   process(data);
 }, 
 "xml"
);
}); 

$('#volverAlIndice').live('submit', function() {
	regenerarRoot();
});	 

$(document).ready(function(){
	regenerarRoot();
});

