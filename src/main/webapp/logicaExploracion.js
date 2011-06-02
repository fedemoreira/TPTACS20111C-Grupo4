const direccionDelServlet = "ServletMercadoLibre";


var appendDivAListaCategorias = function(id, name, divDondeAgregar)
{
	$("<div class=\'cat\' idCat=\'" + id + "\'>" + name +  "</div>").appendTo(divDondeAgregar);
}

var regenerarPathToRoot = function(datos)
{
    $(".tituloPathToRoot").show();
    $("<div class=\'root\'\'> Home </div>").appendTo('.pathToRoot');
    $.each(datos.path_from_root, function(key, data) {
		appendDivAListaCategorias(data.id, data.name, '.pathToRoot');
    });
};

var regenerarCategorias = function(data)
{
    $(".tituloCategorias").show();
    $.each(data, function(key, val) {
		appendDivAListaCategorias(val.id, val.name, '.listaDeCategorias');
    });
};

var regenerarProductos = function(data)
{
    $(".tituloProductos").show();
    $.each(data, function(key, val) {
		$("<div class=\"producto\" idProd=\"" + val.id + "\"> " + "<a href=\"" + val.permalink + "\">"  + "$" + val.price + " -  " +  val.title +  "</a> </div>").appendTo('.productos');
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

var obtenerUrlDeCategoria = function(idCategoria)
{
	//return "https://api.mercadolibre.com/categories/" + idCategoria + "?&jsoncallback=?";
	return direccionDelServlet + "?cat=" + idCategoria;
}
	

$(".cat").live('click', function() {
    limpiar();
    var idCategoria = $(this).attr("idCat");
	$.getJSON("https://api.mercadolibre.com/categories/" + idCategoria + "?callback=?", function(data) {
		regenerarCategorias(data[2].children_categories);
	});
	$.getJSON("https://api.mercadolibre.com/sites/MLA/search?category=" + idCategoria + "&callback=?", function(data) {
				regenerarProductos(data[2].results);
	});
});

$(".root").live('click', function() {
    limpiar();
    $.ajax({url:direccionDelServlet, success: function(data) {
	regenerarCategorias(data);
    }});
});
	      


// Carga inicial
$(document).ready(function(){
	ocultarTitulos();
	$.getJSON("https://api.mercadolibre.com/sites/MLA/categories?callback=?", function(data) {
		regenerarCategorias(data[2]);
		});
});
