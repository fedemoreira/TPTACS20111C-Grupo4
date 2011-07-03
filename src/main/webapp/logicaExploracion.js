//  --- Sacado verbatim de http://jquery-howto.blogspot.com/2009/09/get-url-parameters-values-with-jquery.html
$.extend({
  getUrlVars: function(){
    var vars = [], hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for(var i = 0; i < hashes.length; i++)
    {
      hash = hashes[i].split('=');
      vars.push(hash[0]);
      vars[hash[0]] = hash[1];
    }
    return vars;
  },
  getUrlVar: function(name){
    return $.getUrlVars()[name];
  }
});
//  --- Sacado verbatim de http://jquery-howto.blogspot.com/2009/09/get-url-parameters-values-with-jquery.html


var usuario;

var appendDivAListaCategorias = function(id, name, divDondeAgregar)
{
	$("<div class=\'cat\' idCat=\'" + id + "\'>" + name + "<div style= </div>").appendTo(divDondeAgregar);
};

var appendDivAListaProductos = function(val, divDondeAgregar)
{
	$("<div class=\"producto\" " + "link=\"" + val.permalink + "\"> " + "$" + val.price + " -  " +  val.title +  "</div>").appendTo(divDondeAgregar);
};

var regenerarCategorias = function(data, divAUsar)
{
	$(divAUsar).empty();
	$.each(data, function(key, val) {
		appendDivAListaCategorias(val.id, val.name, divAUsar);
	});
};

var obtenerWishlist = function(idUsuario)
{
	$.getJSON("ServletWishlist?user=" + idUsuario, function(data) {
		regenerarWishlist(data.listaDeProductos);
	});
};

var regenerarProductos = function(data)
{
	$("#productos").empty();
	$.each(data, function(key, val) {
		appendDivAListaProductos(val, "#productos");
	});
};

var regenerarWishlist = function(data)
{
	$("#wishlist").empty();
	$.each(data, function(key, val) {
		$("<li> <div class=\"productoWishlist\" " + "link=\"" + val.link + "\"> " + val.nombre + "</div></li>").appendTo("#wishlist");
	});
};

var toggleCategorias = function()
{
	$("#listaDeCategorias").toggle("highlight", 100);
	$("#pathToRoot").toggle("highlight", 100);
};


var mostrarSinResultados = function()
{
	$("#tituloProductos").show();
	$("#productos").append("No hay resultados");
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
	$("#productos").empty();
	$(".cat").remove();
};

var regenerarRoot = function()
{
	limpiar();
	$("#listaDeCategorias").show();
	$("#pathToRoot").show();
	$("#wishlist").show();
	$.getJSON("https://api.mercadolibre.com/sites/MLA/categories?callback=?", function(data) {
		regenerarCategorias(data[2], "#listaDeCategorias");
	});
	//obtenerWishlist(usuario.id);
};


var obtenerCategorias = function(idCategoria)
{
	$.getJSON("https://api.mercadolibre.com/categories/" + idCategoria + "?callback=?", function(data) 
			{
				regenerarCategorias(data[2].children_categories, "#listaDeCategorias");
				regenerarCategorias(data[2].path_from_root, "#pathToRoot");
			});
};

var obtenerProductos = function(idCategoria)
{
	$.getJSON("https://api.mercadolibre.com/sites/MLA/search?category=" + idCategoria + "&callback=?", function(data) 
			{
				regenerarProductos(data[2].results);
			});
};

var conectarse = function(usuario){
	   
		if(FB.getSession() != null) {
	    FB.api('/me', function(response) {
	        alert ("Welcome " + response.name + ": Your UID is " + response.id);
	        usuario=response.id;
	      });
	      
	};
		
	};
$(".cat").live('click', function() {   
	toggleCategorias();
	obtenerCategorias($(this).attr("idCat"));
	toggleCategorias();    
	obtenerProductos($(this).attr("idCat"));
});


$('#vaciarWishlist').live('submit', function() {
    $.post("ServletWishlist", { limpiar: "y", user: usuario });
	alert("Wishlist vaciada");
	obtenerWishlist(usuario);
	return false;
}); 

$('#conectado').live('submit', function() {
conectarse(usuario);
regenerarRoot();
alert (usuario);
obtenerWishlist(usuario);
$(function() {
	$( "#tabs" ).tabs();
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
    $.post("ServletWishlist", { nombre: $(this).html(), link: $(this).attr("link"), user: usuario });
	alert("Producto agregado a la wishlist");
	obtenerWishlist(usuario);
}); 

$('#volverAlIndice').live('submit', function() {
	regenerarRoot();
	obtenerWishlist(usuario);
});	 



$(document).ready(function(){	
    FB.init({ 
        appId:'140959625981357', cookie:true, 
        status:true, xfbml:true 
     });
    alert ("facebook init");
	conectarse(usuario);

});
