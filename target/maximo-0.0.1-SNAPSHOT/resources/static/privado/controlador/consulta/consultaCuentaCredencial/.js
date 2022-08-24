angular.module('mHome').controller('ctrlConsultaCuentaIdCredencial',['$scope','$location','$routeParams',
	'servicioREST','fValidacion','variableSistema',
	function($scope, $location, $routeParams, servicioREST, fValidacion, variableSistema) {

//							$scope.folioDictamenRTparaSt1 = $scope.$parent.folioDictamenRiesgoTrabajo;
//							variableSistema.numeroFolioDictamen = $scope.$parent.folioDictamenRiesgoTrabajo;
//							$scope.folioDictamenRTparaSt1 = '338190369';
							console.log('folioRevibeControllelParaSt1: ', variableSistema.numeroFolioDictamen);
							$scope.cargando = false;
							
							$scope.controlModal = function (i){
								$scope.modal.visible = i;
							};
							
							$scope.modal = {			
								visible : false,
								modalMsg1 : "",
								modalMsg2 : ""
							}

							$scope.consultaDictamenSt1 = function() {
								var par = {
										folioDictamenRiesgoTrabajo : variableSistema.numeroFolioDictamen
								};
								$scope.cargando = true;                
								servicioREST.post("/consulta/dictamen/st1/bd",par).$promise.then(function(success){
									console.log(success);
									console.log('ENTRANDO_A_CONSULTA_DICTAMEN_ST1: ',success);
											if (success.codE == 0) { 
												$scope.dictamenRiesgoTrabajoSt1 = success.jsonResultado;
											}else{
												var mensaje = "No se encontraron registros."
													$('div#mensajesError').html(generarMensajeError(mensaje));
											        $('div#mensajesError').show();
											        $('div#contenido').hide();
											        
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
							
//							+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

						
//							++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
						} ]);