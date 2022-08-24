angular.module('mHome', ["ui.bootstrap","ngResource","ngRoute","ngCookies","angular-jwt", "ngStorage", "mHome"]) //;

.run(function ($rootScope) {

    
    })

	.config(function($routeProvider, $locationProvider) {
			// use the HTML5 History API
	        $locationProvider.html5Mode(true);
			$routeProvider.
			when('/login', {
				url: '/login',
				controller:'mainCtrl'
			})
			.
			when('/index', {
				templateUrl : './index',

			})

		})
		

.factory('servicioREST', [ '$resource', function($resource) {
			return {
				post : function(ruta, parametros) {
					var resource = $resource(ruta, {}, {
						consumir : {
							method : 'POST', isArray: false, 
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
		}
		
		])
.controller('mainCtrl',['$scope','$uibModal','$resource','$http','$httpParamSerializer','$cookies','jwtHelper','$timeout', '$rootScope', 'AuthenticationServiceLog','$window','$location','apiServiceLogin',
		        function($scope, $uibModal, $resource,$http,$httpParamSerializer,$cookies,jwtHelper,$timeout, $rootScope, AuthenticationServiceLog, $window, $location, apiServiceLogin) {

	  $scope.openSession = function() {
			
		    var modalInstance =  $uibModal.open({
		      templateUrl: "/maximo/resources/static/views/modal/modalSessionActivaMismoEquipo.html",
		      controller: "ModalContentCtrl",
		      size: '',
		    });
		    
				
		    modalInstance.result.then(function(response){
		        $scope.result = `${response} button hitted`;
		    });
		    
		  };
//		###################################PARA_MODAL################################
	$scope.loginData = {
			username : "",
			password : ""
	};
	
	var vm = {};
    
    $scope.encoded = btoa('angularapp' + ':' + '12345');
    
//    *****************************************************************************
    $scope.isNoAutorizado = function(e){
    	if(e.status == 401 || e.status == 403){
    		 window.location.href = "login";
    		return true;
    	}
    	return false;
    }
    
    $scope.organiztion = "";
    $scope.isLoggedIn = false;
    
    $scope.cargando = false;
    
    $scope.getFoo = function(){
        $scope.foo = $scope.foos.get({fooId:$scope.foo.id});
    }
    $scope.loginData.username = "";
    $scope.loginData.password = "";
    $scope.loginData
    $scope.refreshData = {grant_type:"refresh_token"};
    
    $scope.logoutLogin = function(){
		apiServiceLogin.logout();

	}

    $scope.login = function() { 
    	
    	console.log('DATOS_USUARIO: ',$scope.loginData)
    	$scope.cargando = true;
    	AuthenticationServiceLog.Login($scope.loginData, function (result) {
             if (result === true) {

            	 if(sessionStorage.getItem('token') != null){

            			 apiServiceLogin.getToken(sessionStorage.getItem('token'));
            			 
                         $window.location = "/maximo/index";
                         
                         $scope.cargando = false;
                         

            	 }
            	 

             } else {
            	 $window.location = "/maximo/login";
            	 console.log('Username or password is incorrect');
                 vm.error = 'Username or password is incorrect';
                 vm.loading = false;
             }
         });

    }
    


  
  
    
} //fin controlador

])
//########################################CONTROLADOR_MODAL###########################################
.controller('ModalContentCtrl', function($scope, $uibModalInstance) {

  $scope.ok = function(){
    $uibModalInstance.close("Ok");
  }
   
  $scope.cancel = function(){
    $uibModalInstance.dismiss();
  } 
})
//########################################FIN_CONTROLADOR_MODAL###########################################

;



