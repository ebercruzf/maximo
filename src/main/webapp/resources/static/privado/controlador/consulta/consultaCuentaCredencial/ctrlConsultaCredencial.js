//angular.module('mHome')
//
//
//.controller('ctrlConsultaCuentaIdCredencial',['$scope','$location','$routeParams',
//	'servicioREST','fValidacion','variableSistema', '$route',
//	function($scope, $location, $routeParams, servicioREST, fValidacion, variableSistema, $route) {
//
//							
//							$scope.cargando = false;
//							
//							$scope.controlModal = function (i){
//								$scope.modal.visible = i;
//							};
//							$scope.listaCredencialesUsuario = "";
//							$scope.idCredencialUsuario = "";
//							$scope.CredencialNombreUsuario = "";
//							
//							
//							
//							$scope.imprimeInfoCuenta = function(){
//								
//							
//							}
////							####################################################################################
//
//							
//							$scope.data = {};
//
//							
//							$scope.refreshData = function ()
//						    {
//						        $scope.data = [];
//						        
//
//						        
//						        $("#filtroBancoInput").val($scope.inputVacio);
//						        
//
//						        $("#filtroBancoInput").val('1');
//						        $("#filtroBancoInput").val('');
//						        document.getElementById('filtroCategoriaInput').focus();
//						        $scope.friendObj="";
//
//						        $scope.consultaCuentaPorCredencial();
//						        
//						        $scope.data= $scope.consultaCuentas;
//						    };
//						    $scope.reloadRoute = function () {
//						    	$route.reload();
//						    };
//						    
//						    $scope.refreshData2 = function ()
//						    {
//						        $scope.data = [];
//						    };
//						    
//
//							
////							variableSistema.numeroFolioDictamen
//							
//							$scope.modal = {			
//								visible : false,
//								modalMsg1 : "",
//								modalMsg2 : ""
//							}
//							$scope.api_access_token = sessionStorage.getItem('api_access_token');
//							
//							
//							 var _usuario = JSON.parse(sessionStorage.getItem('usuario'));
//							
//
//							$scope.apiGetMovimientoTotal = function(){
//								console.log('Entra en la función77-apiGetMovimiento -> imprimeInfo');
//
//								
//								$scope.api_access_token = sessionStorage.getItem('api_access_token');
//								
//
//								$scope.idCuenta = variableSistema.idCredencialCuenta;
//								console.log('################################### -> idCuenta', $scope.idCuenta);  
//								var parametros = {
//											idcuenta :  $scope.idCuenta, 
//											token : $scope.api_access_token
//								};
//								console.log('Entra en la función777999 -> imprimeInfo');  
//								$scope.cargando = true;
//
//								console.log('OBTENIENDO_MOVIMIENTOS_FARARONI');
//				
//					
//								
//
//							}
//						
////							++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//						} ]);