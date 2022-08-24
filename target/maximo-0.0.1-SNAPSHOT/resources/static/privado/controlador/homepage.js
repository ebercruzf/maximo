angular.module('mHome', ['ngResource', 'ngRoute', 'ngCookies','angular-jwt', 'ngGrid', 'ngSanitize', 'selectize']).run(function() {

}).config(function($routeProvider, $locationProvider) {
	// use the HTML5 History API
	$locationProvider.html5Mode(true);
	$routeProvider.when('/index', {
		templateUrl :'/maximo/resources/static/views/consulta/informacion/_bandejaprincipal.html' //_bandejaMovimientos.html' //movimientos--peliculas
	})

	.when('/busquedapeliculapornombre', {
		templateUrl :'/maximo/resources/static/views/consulta/informacion/_consultaBusquedaPeliculaPorNombre.html',  //_consultaTodosMovimientos.html
		controller:'ctrlBuscaPeliculaPorNombreTodo'
			
	})

	.when('/logout', {
		templateUrl :'/maximo/resources/static/templates/login.html',
		controller: 'ctrlLogout'
	})
	.otherwise({
	    template : "/index"
	})
})

.factory('servicioREST', [ '$resource', function($resource) {
	return {
		post : function(ruta, parametros, headerFn) {
			var resource = $resource('/maximo' + ruta, {}, {
				consumir : {
					method : 'POST', isArray: false,
					headers: headerFn,
					transformRequest : function(datos, headerFn) {
						return JSON.stringify(datos);
					},
					transformResponse : function(datos, headerFn) {
						return JSON.parse(datos);
					},
				}
			});
			return resource.consumir(parametros);
		}
	}
}])

.factory('servicioREST_GET', [ '$resource', function($resource) {
	return {
		post : function(ruta, parametros, headerFn) {
			var resource = $resource('/maximo' + ruta, {}, {
				consumir : {
					method : 'GET', isArray: false,
					headers: headerFn,
					transformRequest : function(datos, headerFn) {
						return JSON.stringify(datos);
					},
					transformResponse : function(datos, headerFn) {
						return JSON.parse(datos);
					},
				}
			});
			return resource.consumir(parametros);
		}
	}
}])

.controller('aboutController', function($scope) {
	$scope.message = 'Esta es la página "Acerca de"';
})

.controller('ctrlHome', ['$scope', '$log', '$location', 'servicioREST', '$rootScope', 'variableSistema',  '$window', '$http','apiServiceLogin',
	'$timeout',function($scope, $log, $location, servicioREST, $rootScope, variableSistema,  $window, $http, apiServiceLogin, $timeout) {

	
	var datoUsuario = {};
	datoUsuario = apiServiceLogin.getUsuario();

	if(!apiServiceLogin.isAuthenticatedActual()){
			apiServiceLogin.logout();
		
	};
	 const nombreUsuarioS = datoUsuario.usuario.nombre;
	 const claveUsuarioS = datoUsuario.usuario.cveUsuario;

	 const rolS = datoUsuario.usuario.rol;

	var usuarioS = datoUsuario.usuario.nombre;
	 
	var usuario = usuarioS;	
	$scope.nombreUsuario = nombreUsuarioS;


	

//	  ************************CERRAR_SESSION_BORRAR_ESTA_LINEA****************************
	let tokenAcceso = apiServiceLogin.getToken();
	$scope.logout = function(){

		cierraSession();
		apiServiceLogin.logout();
	}
	
	 $timeout( function(){
		 cierraSession();
			apiServiceLogin.logout();
        console.log('Cessón cerrada>> ');
     }, 14400000 );
//	############################################################################################
//	##############################Servicio Cierre de Session####################################
//	############################################################################################
	  var contadorIntentos = 0;
      var contadorAcum = 0;
	cierraSession = function(){

		
//		#################comienza agregado cambios#####################
		var config = {
        		headers: {
   				 'Authorization': 'Bearer ' + tokenAcceso
   			    }
        	   };


		var mat = {
			usuario: claveUsuarioS,	
		};
		$scope.cargando = true;

		servicioREST.post("/public/api/cliente/interno/cerrar/session/bd",mat, config.headers).$promise.then(function(success){
		
			if (success.codE == 0) {
				$scope.respuestaCierreSession = success.jsonResultado;
				console.log('RESPUESTA_EXITO_CONTROLLER_BACK_CERRAR_SESSION : ', success);
			}
			else if (success.codE == 1) {
				var mensaje = success.msgE;
					$('div#mensajesErrorConexionBd').html(generarMensajeError(mensaje));
			        $('div#mensajesErrorConexionBd').show();
			        $('div#contenido').hide();
			}
			$scope.cargando = false;
		}, function(e){
			if(e.status == 400){
				var mensaje = "Usuario y/o constraseña invalida."
					$('div#mensajesErrorFront').html(generarMensajeError(mensaje));
			        $('div#mensajesErrorFront').show();
			        
			        var contadorIntentos = 0;
			        var contadorAcum = 0;
			        $('div#contenido').hide();
			}if(e.status == 417){
				var mensaje = "Session Invalida. No se pudo cerrar la sesión."
					$('div#mensajesErrorFront').html(generarMensajeError(mensaje));
			        $('div#mensajesErrorFront').show();
			        $('div#contenido').hide();
			}
			else{
				apiServiceLogin.isNoAutorizado(e);
			}
			$scope.cargando = false;
		});
	}
	
//	#############################################InicioObservale###############################################
	$scope.variableAMonitorear = "prueba";
	$scope.$watch('variableAMonitorear', function(valorNuevo, valorViejo){
		console.log(valorNuevo, valorViejo)
	})
//	#############################################Fin_InicioObservale###############################################
}]);