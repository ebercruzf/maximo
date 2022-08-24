angular
.module('mHome')
.controller('ctrlBuscaPeliculaPorNombreTodo',['$scope','$location','$routeParams',
	'servicioREST','fValidacion','variableSistema', '$route','apiServiceLogin', 'apiServiceLogin',
	function($scope, $location, $routeParams, servicioREST, fValidacion, variableSistema, $route, apiServiceLogin) {

	var datoUsuario = {};
	datoUsuario = apiServiceLogin.getUsuario();

	if(!apiServiceLogin.isAuthenticatedActual()){
			apiServiceLogin.logout();
		
	};
	 const nombreUsuarioS = datoUsuario.usuario.nombre;
	 const claveUsuarioS = datoUsuario.usuario.cveUsuario;
	 
	 $scope.usuarioClave = datoUsuario.usuario.cveUsuario;
	 
	 
		
							$scope.cargando = false;
							
							$scope.controlModal = function (i){
								$scope.modal.visible = i;
							};
							$scope.listaCredencialesUsuario = "";
							$scope.idCredencialUsuario = "";
					
							$scope.CredencialNombreUsuario = "";
							
							$scope.inputBuscarPelicula = "";
							$scope.tituloPeliculaEncontrada = ""; //jsonResultado.tituloPeliculaBuscarWS
							$scope.idOmdbapi = ""; //jsonResultado.imdbID
							$scope.posterPelicula = ""; //jsonResultado.poster
							$scope.puntos = ""; //jsonResultado.poster
							$scope.fechaCuandolavi = ""; //jsonResultado.poster
							
							
							
							$scope.comentarios = "";

							
							$scope.tituloPeliculaEncontradaEnviar = "";
					    	$scope.idOmdbapiEnviar = "";
					    	$scope.posterPeliculaEnviar = "";
					    	$scope.comentariosEnviar = "";
					    	$scope.puntuacionEnviar = "";
					    	$scope.fechaCuandolaviEnviar = "";
					    	
					    	
					    	$( function() {
					    	    $( "#datepicker" ).datepicker();
					    	  } );
					    	
					    	$("#idFecha").datepicker({

					        });	
					    	
					    	 $(".idFecha").datepicker({
//					 	        maxDate : "+12d",
					 	        minDate : new Date(),
					 	        
					 	        dateFormat : "dd/mm/yy",
					 	        changeYear : false,
					 			changeMonth: false
					 	    });
					    	 
					    	 $scope.parent = {checkOut:''};
					    	 
					    	 
			    			 $scope.datoBaseLocaimdbIDBd = "";
			    			 $scope.datoBaseLocatituloPelicula = "",
			    			 $scope.datoBaseLocapuntuacionRating = "";
			    			 $scope.datoBaseLocacomentarios = "" ,
			    			 $scope.datoBaseLocafechaCuandoLaVi = "";
			    			 $scope.datoBaseLocaposter = "";
			    			 $scope.datoBaseLocausuario = "";
					    	 
					    	 $scope.exitoConsultaWS = "1";
			    			 $scope.muestraCuadroAgregar = "1";
			    			 $scope.muestraCuadroExistenciaBDloca = "1";
							//###################################################################
							$scope.busquedaDosWSLocal = function(){
								

								$scope.consultaBusquedaPelicula();
								$scope.consultaBusquedaPeliculaBdLocal();
								
								
							}
//							####################################################################################
							$scope.data = {};

							
							$scope.refreshData = function ()
						    {
						        $scope.data = [];

						    };
						    $scope.reloadRoute = function () {
						    	$route.reload();
						    };
						    
						    $scope.refreshData2 = function ()
						    {
						        $scope.data = [];
						    };
						    
							
							$scope.modal = {			
								visible : false,
								modalMsg1 : "",
								modalMsg2 : ""
							}
							$scope.api_access_token = sessionStorage.getItem('api_access_token');
							
							
							 var _usuario = JSON.parse(sessionStorage.getItem('usuario'));
							 
							 $scope.banderaAgregar="0";
//								+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//								+++++++++++++++++++++++++++++++++++++++++++CONSULTAR PELICULA EN LA WEB++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//								+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//							$scope.imprimeInfoCuenta = function(){
							$scope.consultaBusquedaPelicula = function(){
								
//								$scope.datoBaseLocatituloPelicula = 
//								$("#datoBaseLocatituloPelicula").val("");
//								$scope.datoBaseLocapuntuacionRating = 
//									$("#datoBaseLocapuntuacionRating").val("");
//								$scope.datoBaseLocafechaCuandoLaVi = 
//									$("#datoBaseLocafechaCuandoLaVi").val("");
//								$scope.datoBaseLocacomentarios = 
//									$("#idcomentariosEnviar").val("");
//								$scope.datoBaseLocaimdbIDBd = 
//									$("#datoBaseLocaimdbIDBd").val("");
								
								
								$scope.banderaAgregar="0";
								$scope.cuadroAgregar = "0";
								console.log('##########BUSCANDO_PELICULAS###################: ',$scope.inputBuscarPelicula);
								
								let nombreBusqueda = $scope.inputBuscarPelicula;
								var parametros = {
										nombreBusqueda : $scope.inputBuscarPelicula
												};
								$scope.cargando = true; 
								
//								/api/cliente/consulta/pelicula/get/{avatar}
//								servicioREST_GET.get("/api/cliente/consulta/pelicula/get/"+nombreBusqueda).$promise.then(function(success){
								servicioREST.post("/api/cliente/consulta/pelicula",parametros).$promise.then(function(success){
									console.log(success);
											if (success.codE == 0) {
												$scope.exitoConsulta = success.codE;
												$scope.data = success.jsonResultado;
												$scope.consultaCuentas = success.jsonResultado;
												
												$scope.tituloPeliculaEncontrada = success.jsonResultado.tituloPeliculaBuscarWS;
												$scope.idOmdbapi = success.jsonResultado.imdbID;
												$scope.posterPelicula = success.jsonResultado.poster;
												
												$scope.exitoConsultaWS = "2";
											
											        $('div#mensajesError').hide();
											}else{
												var mensaje = "No se encontraron registros."
													$('div#mensajesError').html(generarMensajeError(mensaje));
											        $('div#mensajesError').show();
											        $('div#contenido').hide();
											        $scope.exitoConsulta = success.codE;
											        
												$scope.cargando = false;
												$scope.modal = {			
														visible : true,
														modalMsg1 : "Sin Datos",
														modalMsg2 : success.msgE
													}
												}
												$scope.cargando = false;
										}, function(error){
											var mensaje = "Servicio no disponible. Intente más tarde."
												$('div#mensajesError').html(generarMensajeError(mensaje));
										        $('div#mensajesError').show();
										        $('div#contenido').hide();
											$scope.cargando = false;
											$scope.modal = {			
												visible : true,
												modalMsg1 : "Servicio no disponible.",
												modalMsg2 : "Intente más tarde."
											}
										}
								)
								;
							}
//							+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//							+++++++++++++++++++++++++++++++++++++++++++AGREGAR PELICULA A BASE LOCAL++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//							+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
							
							
							$scope.agregarGuardaConsultaLimpia = function() {
								$scope.agregarBusquedaPelicula();
								$scope.muestraCuadroExistenciaBDloca = "2";
								
								$scope.consultaBusquedaPeliculaBdLocal();
								
							}
							
							
							
							$scope.agregarBusquedaFormulario = function() {
								$scope.muestraCuadroAgregar = "2";
								
						    	$scope.tituloPeliculaEncontradaEnviar = $scope.tituloPeliculaEncontrada;
						    	$scope.idOmdbapiEnviar = $scope.idOmdbapi;
						    	$scope.posterPeliculaEnviar = $scope.posterPelicula;
						    	
						    	
						    	
								
							}
							$scope.agregarBusquedaPelicula = function() {
								
								console.log('##########BUSCANDO_PELICULAS_$scope.usuarioClave44###################: ',$scope.usuarioClave);
								console.log('##########BUSCANDO_COMENTARIOS###################: ', $scope.comentariosEnviar);
								var parametros = {
										
									    	imdbID : $scope.idOmdbapiEnviar,
									    	tituloPelicula : $scope.tituloPeliculaEncontradaEnviar,
									    	puntuacionRating : $("#idpuntuacionEnviar").val(), //$scope.puntuacionEnviar,
									    	comentarios : $("#idcomentariosEnviar").val(),  // $scope.comentariosEnviar, 
									    	fechaCuandoLaVi : $("#idFecha").val(), //$scope.fechaCuandolaviEnviar,
									    	poster : $scope.posterPeliculaEnviar,
									    	usuario :  $scope.usuarioClave
									    	
												};
								console.log('##########SE_ENIVIAAAAAAAAAAAAAAAA67###################: ', parametros);
								
								$scope.cargando = true; 
								console.log('++++++++++++++++++++MANDO_AGREGO+++++++++++++++++++  ', parametros);
								
								servicioREST.post("/api/cliente/agrega/pelicula",parametros).$promise.then(function(success){
									console.log(success);
									console.log('ENTRANDO_A_CONSULTA_CUENTA_AGREGO: ',success);
											if (success.codE == 0) {
												$scope.exitoConsulta = success.codE;
												$scope.data = success.jsonResultado;
												
												var mensaje = success.msgE;
						                    	$('div#mensajesError').html(generarMensajeSucces(mensaje));
										        $('div#mensajesError').show();
										        $('div#contenido').hide();
										        
										        $scope.muestraCuadroAgregar = "1";
											        
											}else {
												var mensaje = success.msgE;
													$('div#mensajesError').html(generarMensajeError(mensaje));
											        $('div#mensajesError').show();
											        $('div#contenido').hide();
											        $scope.exitoConsulta = success.codE;
											        
												$scope.cargando = false;
												$scope.modal = {			
														visible : true,
														modalMsg1 : "Sin Datos",
														modalMsg2 : success.msgE
													}
												}
												$scope.cargando = false;
										}, function(error){
											var mensaje = "Servicio no disponible. Intente más tarde."
												$('div#mensajesError').html(generarMensajeError(mensaje));
										        $('div#mensajesError').show();
										        $('div#contenido').hide();
											$scope.cargando = false;
											$scope.modal = {			
												visible : true,
												modalMsg1 : "Servicio no disponible.",
												modalMsg2 : "Intente más tarde."
											}
										}
								)
								;
							}
							
//							+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//							+++++++++++++++++++++++++++++++++++++++++++CONSULTA PELICULA BD LOCA++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//							+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//							$scope.banderaAgregar="0";
							
							$scope.consultaBusquedaPeliculaBdLocal = function() {
								
								
								console.log('############CONSULTANDO_BUSQUEDA_LOCAL_BD_USUARIO###################: ',$scope.usuarioClave);
								console.log('############CONSULTANDO_BUSQUEDA_LOCAL_BD_TITULO###################:', $scope.inputBuscarPelicula);
								
								
								
								var parametros = {
										
										    tutuloPelicula : $("#inputBuscarPeli").val(), 
									    	usuario :  $scope.usuarioClave
									    	
												};
								console.log('##########SE_ENIVIAAAAAAAAAAAAAAAA67###################: ', parametros);
								
								$scope.cargando = true; 
								console.log('++++++++++++++++++++MANDO_CONSULTA_BASE_LOCAL+++++++++++++++++++  ', parametros);
//								console.log('ID_CREDENCIAL89_idCredencialCuenta_:  ', variableSistema.idCredencialCuenta);
								servicioREST.post("/api/cliente/consulta/pelicula/bdlocal",parametros).$promise.then(function(success){
									console.log(success);
									console.log('REGRESANDO_DATOS_LOCAL_pruefaENFA: ',success);
											if (success.codE == 0) {
												$scope.exitoConsulta = success.codE;
												$scope.data = success.jsonResultado;
												
//												$("#datoBaseLocatituloPelicula").val(success.jsonResultado.nombreBusqueda);
//												$("#datoBaseLocapuntuacionRating").val(success.jsonResultado.puntuacionRating);
//												$("#datoBaseLocafechaCuandoLaVi").val(success.jsonResultado.fechaCuandoLaVi);
//												$("#idcomentariosEnviar").val(success.jsonResultado.comentarios);
//												$("#datoBaseLocaimdbIDBd").val(success.jsonResultado.imdbID);
												
												 $scope.datoBaseLocaimdbIDBd = success.jsonResultado.imdbID;
												 $scope.datoBaseLocatituloPelicula = success.jsonResultado.nombreBusqueda ;
												 $scope.datoBaseLocapuntuacionRating = success.jsonResultado.puntuacionRating ;
												 $scope.datoBaseLocacomentarios = success.jsonResultado.comentarios ;
												 $scope.datoBaseLocafechaCuandoLaVi = success.jsonResultado.fechaCuandoLaVi,
												 $scope.datoBaseLocaposter = success.jsonResultado.poster;
												 $scope.datoBaseLocausuario = success.jsonResultado.usuario;
										    	
												 $scope.muestraCuadroExistenciaBDloca = "2";
												 
												var mensaje = success.msgE;
						                    	$('div#mensajesError').html(generarMensajeSucces(mensaje));
										        $('div#mensajesError').show();
										        $('div#contenido').hide();
											        
											}else {
												 $scope.muestraCuadroExistenciaBDloca = "1";
												var mensaje = success.msgE;
													$('div#mensajesError').html(generarMensajeError(mensaje));
											        $('div#mensajesError').show();
											        $('div#contenido').hide();
											        $scope.exitoConsulta = success.codE;
											        
												$scope.cargando = false;
												$scope.modal = {			
														visible : true,
														modalMsg1 : "Sin Datos",
														modalMsg2 : success.msgE
													}
												}
												$scope.cargando = false;
										}, function(error){
											var mensaje = "Servicio no disponible. Intente más tarde."
												$('div#mensajesError').html(generarMensajeError(mensaje));
										        $('div#mensajesError').show();
										        $('div#contenido').hide();
											$scope.cargando = false;
											$scope.modal = {			
												visible : true,
												modalMsg1 : "Servicio no disponible.",
												modalMsg2 : "Intente más tarde."
											}
										}
								)
								;
							}
							
//							+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//							+++++++++++++++++++++++++++++++++++++++++++EDITAR/ACTUALIZAR REGISTRO ACTUALIZAR BD LOCA++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//							+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

							$scope.banderaEditarActualizar="0";
					
							$scope.editarActualizarPelicula = function() {
								console.log('##########EDITANDO_imdbID###################: ',$scope.datoBaseLocaimdbIDBd);
								console.log('##########EDITANDO_tituloPelicula###########: ',$scope.datoBaseLocatituloPelicula);
								console.log('##########EDITANDO_puntuacionRating#########: ',$scope.datoBaseLocapuntuacionRating);
								console.log('##########EDITANDO_comentarios##############: ',$scope.datoBaseLocacomentarios);
								console.log('##########EDITANDO_fechaenlaquelaVi#########: ',$scope.datoBaseLocafechaCuandoLaVi);
								console.log('##########EDITANDO_usuario##################: ',$scope.datoBaseLocausuario);
				
								var parametros = {
						
									    	imdbID :  $("#datoBaseLocaimdbIDBd").val(),
									    	tituloPelicula :  $("#datoBaseLocatituloPelicula").val(),
									    	puntuacionRating :  $("#datoBaseLocapuntuacionRating").val(),
									    	comentarios :  $("#idcomentariosEnviar").val(), 
									    	fechaCuandoLaVi :  $("#datoBaseLocafechaCuandoLaVi").val(),
									    	usuario :   $scope.datoBaseLocausuario
									    	
												};
								console.log('##########SE_ENIVIAAAAAAAAAAAAAAAA6EDICION###################: ', parametros);
								
								$scope.cargando = true; 
								console.log('++++++++++++++++++++MANDO_AGREGO+++++++++++++++++++  ', parametros);
								console.log('ID_CREDENCIAL89_idCredencialCuenta_:  ', variableSistema.idCredencialCuenta);
								servicioREST.post("/api/cliente/editar/pelicula/bdlocal",parametros).$promise.then(function(success){
									console.log(success);
									console.log('ENTRANDO_A_CONSULTA_CUENTA_AGREGO: ',success);
											if (success.codE == 0) {
												$scope.exitoConsulta = success.codE;
												$scope.data = success.jsonResultado;
												
												var mensaje = success.msgE;
						                    	$('div#mensajesError').html(generarMensajeSucces(mensaje));
										        $('div#mensajesError').show();
										        $('div#contenido').hide();
											        
											}else {
												var mensaje = success.msgE;
													$('div#mensajesError').html(generarMensajeError(mensaje));
											        $('div#mensajesError').show();
											        $('div#contenido').hide();
											        $scope.exitoConsulta = success.codE;
											        
												$scope.cargando = false;
												$scope.modal = {			
														visible : true,
														modalMsg1 : "Sin Datos",
														modalMsg2 : success.msgE
													}
												}
												$scope.cargando = false;
										}, function(error){
											var mensaje = "Servicio no disponible. Intente más tarde."
												$('div#mensajesError').html(generarMensajeError(mensaje));
										        $('div#mensajesError').show();
										        $('div#contenido').hide();
											$scope.cargando = false;
											$scope.modal = {			
												visible : true,
												modalMsg1 : "Servicio no disponible.",
												modalMsg2 : "Intente más tarde."
											}
										}
								)
								;
							}

//							+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//							+++++++++++++++++++++++++++++++++++++++++++ELIMINARREGISTRO ACTUALIZAR BD LOCA++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//							+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

							$scope.banderaEditarActualizar="0";
					
							$scope.eliminarActualizarPelicula = function() {
				
								var parametros = {
										
										
										
										    imdbid :  $("#datoBaseLocaimdbIDBd").val(),
									    	usuario :   $scope.datoBaseLocausuario
									    	
												};
								console.log('##########SE_ELIMINAAAAAAAAAAN###################: ', parametros);
								
								$scope.cargando = true; 
								console.log('++++++++++++++++++++ELIMINO_AGREGO+++++++++++++++++++  ', parametros);
								console.log('ID_CREDENCIAL89_idCredencialCuenta_:  ', variableSistema.idCredencialCuenta);
								servicioREST.post("/api/cliente/eliminar/pelicula/bdlocal",parametros).$promise.then(function(success){
									console.log(success);
									console.log('ENTRANDO_A_CONSULTA_CUENTA_AGREGO: ',success);
											if (success.codE == 0) {
												$scope.exitoConsulta = success.codE;
												$scope.data = success.jsonResultado;
												
												var mensaje = success.msgE;
						                    	$('div#mensajesError').html(generarMensajeSucces(mensaje));
										        $('div#mensajesError').show();
										        $('div#contenido').hide();
											        
											}else {
												var mensaje = success.msgE;
													$('div#mensajesError').html(generarMensajeError(mensaje));
											        $('div#mensajesError').show();
											        $('div#contenido').hide();
											        $scope.exitoConsulta = success.codE;
											        
												$scope.cargando = false;
												$scope.modal = {			
														visible : true,
														modalMsg1 : "Sin Datos",
														modalMsg2 : success.msgE
													}
												}
												$scope.cargando = false;
										}, function(error){
											var mensaje = "Servicio no disponible. Intente más tarde."
												$('div#mensajesError').html(generarMensajeError(mensaje));
										        $('div#mensajesError').show();
										        $('div#contenido').hide();
											$scope.cargando = false;
											$scope.modal = {			
												visible : true,
												modalMsg1 : "Servicio no disponible.",
												modalMsg2 : "Intente más tarde."
											}
										}
								)
								;
							}

						
//							++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
						} ]);