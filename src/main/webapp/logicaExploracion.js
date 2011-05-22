
var regenerarPathToRoot = function(datos)
{
        $.each(datos.path_from_root, function(key, data)
        {
          $("<div class=\'cat\' idCat=\'" + data.id + "\'>" + data.name +  "</div>").appendTo('.pathToRoot');
        });
}

var regenerarCategorias = function(data)
{
  var items = [];

  $.each(data, function(key, val) {
    $("<div class=\"cat\" idCat=\"" + val.id + "\">" + val.name + "</div>").appendTo('.listaDeCategorias');
  });
}

var obtenerCategorias = function(datos)
{	
  var items = [];
  $(".pathToRoot").empty();
  regenerarCategorias(datos.children_categories);
  regenerarPathToRoot(datos);
};

var limpiar = function()
{
        $(".pathToRoot").empty();
	$(".listaDeCategorias").empty();
}



// Carga inicial
$.getJSON("ServletMercadoLibre", function(data) {
regenerarCategorias(data);
});


$(".cat").live('click', function() {
        limpiar();
	$.getJSON("ServletMercadoLibre?cat=" + $(this).attr("idCat"), function(datos) {
		obtenerCategorias(datos);
		});	
	});
