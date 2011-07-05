

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
	$.each(data, function(key, val) 
	{
		appendDivAListaCategorias(val.id, val.name, divAUsar);
	});
};

var obtenerWishlist = function(idUsuario)
{
	$.getJSON("ServletWishlist?user=" + idUsuario, function(data) 
	{
		regenerarWishlist(data.listaDeProductos);
	});
};

var regenerarProductos = function(data)
{
	$("#productos").empty();
	$.each(data, function(key, val) 
	{
		appendDivAListaProductos(val, "#productos");
	});
};

var regenerarWishlist = function(data)
{   
	$('#vaciarWishlist').appendTo('body');
	$("#wishlist").empty();
	$.each(data, function(key, val) 
	{
		$("<li> <div class=\"productoWishlist\" " + "link=\"" + val.link + "\"> " + val.nombre + "</div></li>").appendTo("#wishlist");
	});
	$('#vaciarWishlist').appendTo('#wishlist');
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
	$.getJSON("https://api.mercadolibre.com/sites/MLA/categories?callback=?", function(data) 
	{
		regenerarCategorias(data[2], "#listaDeCategorias");
	});
	obtenerWishlist(usuario);
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

function conectarse(){
	if(FB.getSession() != null) 
	{   
		 FB.api('/me', function(response) 
		{		if(response && !response.error){
				document.getElementById("conectado").title=response.id;
				$("#login").empty();
				$("#login").append("Loggeado como " + response.name);
		};
	    });
	    
			}; 	
};

function publicar(body){

	if(FB.getSession() != null) 
	{   
		FB.api('/me/feed', 'post', { message: body }, function(response){
			  if (!response || response.error) {
				    alert('No se pudo postear en tu muro');
				  } else {
				    alert('Le contaste a tus amigos (en tu muro)');
				  };
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
    $.post("ServletWishlist", { limpiar: "y", user: usuario }, function(data)
		{
			alert("Wishlist vaciada");
			obtenerWishlist(usuario);
		});
	return false;
});

$('#Amigos').live('submit', function(){
		publicar("Estoy disfrutando de la app de wishlist del Grupo 4 de TACS, mirala. http://apps.facebook.com/tptacsgrupob/");
		  return false;}
);


$('#busqueda').live('submit', function() {
	limpiar();
	$.getJSON("https://api.mercadolibre.com/sites/MLA/search?q=" + $("input:first").val() + "&callback=?", function(data) 
			{ 
				busqueda(data); 
			});
	return false;
}); 



$('.producto').live('click', function() {
    if (usuario!="notLoggedIn" )
    	{	
    	$.post("ServletWishlist", { nombre: $(this).html(), link: $(this).attr("link"), user: usuario }, function(data)
    		{
    		alert("Producto agregado a la wishlist");
    		obtenerWishlist(usuario);
    		return false;
    		}
    	);}
    	else { 
    		conectarse();
    		usuario = document.getElementById("conectado").title;
    		if (FB.getSession() == null)
    			{
    				alert("No estás conectado a Facebook");
    			};
    	}
    return false;}
); 

$('#volverAlIndice').live('submit', function() {
	regenerarRoot();
	obtenerWishlist(usuario);
	return false;
});	 



$(document).ready(function(){	

	 if (document.URL.substring(0,21) == "http://localhost:8080")	
	{FB.init({ 
        appId:'140959625981357', cookie:true, //Para localhost
        status:true, xfbml:true 
     });}
	 else {FB.init({
	        appId:'140721799338580', cookie:true, //Para GAE
	        status:true, xfbml:true 
	 });
	 };
	conectarse();
	usuario =document.getElementById("conectado").title;
	if(usuario=="notLoggedIn")
	{
		$("#login").append("<fb:login-button perms=\"email,user_checkins,publish_stream\">Login with Facebook</fb:login-button>");
	}
	else
	{
		$("#login").empty();
		$("#login").append("Loggeado como " + usuario);
	}
	regenerarRoot();
	$(function() {
		$( "#tabs" ).tabs();
}	);

});
