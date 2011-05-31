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
    $(".tituloPruductos").show();
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

const direccionDelServlet = "ServletMercadoLibre";

var obtenerUrlDeCategoria = function(idCategoria)
{
	return direccionDelServlet + "?cat=" + idCategoria;
}

$(".cat").live('click', function() {
    limpiar();
    $.getJSON(obtenerUrlDeCategoria($(this).attr("idCat")), function(datos) {
		obtenerCategorias(datos);
    });	
    $.getJSON(obtenerUrlDeCategoria($(this).attr("idCat") + "&productos=true"), function(datos) {
		regenerarProductos(datos.results);
    });	
});

$(".root").live('click', function() {
    limpiar();
    $.getJSON(direccionDelServlet, function(data) {
	regenerarCategorias(data);
    });
});
	      


// Carga inicial
$(document).ready(function(){
	ocultarTitulos();
	$.getJSON(direccionDelServlet, function(data) {
		regenerarCategorias(data);
		});
});
