angular.module('mHome')
.factory('variableSistema',['$http','$q', function($http, $q){

	var folio = "";

	var mensaje = "";
	var codigo = "";
	var numeroFolioDictamen = "";
//  #################################################################
	var numeroCuenta = "";
	
	var idCredencialCuenta = "";
	var idCredencialBanco = "";
	var credencialNombreUsuario = "";
	
	var resultadoListaDeCuentas = [];
	
	
//  #################################################################	
	var variablesSist = {				
			    "datos":[{
    			"numeroFolioDictamen" : numeroFolioDictamen,
				"accion" : "",
				"tipoDictamen" : ""
    		}]
	};
	return variablesSist;
	
}])