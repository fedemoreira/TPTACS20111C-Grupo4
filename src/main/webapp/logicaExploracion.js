var regenerarPathToRoot = function(datos)
{
    $(".tituloPathToRoot").show();
    $("<div class=\'root\'\'> Home </div>").appendTo('.pathToRoot');
    $.each(datos.path_from_root, function(key, data) {
        $("<div class=\'cat\' idCat=\'" + data.id + "\'>" + data.name +  "</div>").appendTo('.pathToRoot');
    });
};

var regenerarCategorias = function(data)
{
    $(".tituloCategorias").show();
    $.each(data, function(key, val) {
	$("<div class=\"cat\" idCat=\"" + val.id + "\">" + val.name + "</div>").appendTo('.listaDeCategorias');
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

const direccionInicial = "ServletMercadoLibre";


$(".cat").live('click', function() {
    limpiar();
    $.getJSON(direccionInicial + "?cat=" + $(this).attr("idCat"), function(datos) {
	obtenerCategorias(datos);
    });	
    $.getJSON(direccionInicial + "?cat=" + $(this).attr("idCat") + "&productos=true", function(datos) {
	regenerarProductos(datos.results);
    });	
});

$(".root").live('click', function() {
    limpiar();
    $.getJSON(direccionInicial, function(data) {
	regenerarCategorias(data);
    });
});
	      


// Carga inicial
$(document).ready(function(){
	ocultarTitulos();
	$.getJSON(direccionInicial, function(data) {
		regenerarCategorias(data);
		});
});
