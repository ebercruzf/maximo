(function () {
    'use strict';

angular.module('mHome')


.factory('AuthenticationServiceLog', Service);

    function Service($http, $rootScope) {
        var service = {};

        service.Login = Login;

        return service;

        const _token = "";
    	const _usuario = "";
    	 
        function Login(loginData, callback) {
        	 const credenciales = btoa('angularapp' + ':' + '12345');
          
            	  let params = new URLSearchParams();
            	    params.set('grant_type', 'password');
            	    params.set('username', loginData.username);
            	    params.set('password', loginData.password);
            	    

            	var config = {
            		method: 'POST',
            		url: 'http://localhost:7005/maximo/oauth/token',
            		headers: {
        				 'Content-Type': 'application/x-www-form-urlencoded',
        				 'Authorization': 'Basic ' + credenciales
        			 },
            		data: params.toString()
            	   };

                 $http(config).then(function(response) {
            		
            		if (response.data) {
            			if(response.data.respuesta.codE==1){
            				var respuestaSessionActiva = response.data.respuesta.msgE;
            				sessionStorage.setItem('mensajeSessionActiva', respuestaSessionActiva);
            				
            				var mensaje =  response.data.respuesta.msgE;
        						$('div#mensajesErrorLogin').html(generarMensajeError(mensaje));
        				        $('div#mensajesErrorLogin').show();
            				$window.location = "/maximo/login";
            				
            			}
            			else{
            				sessionStorage.setItem('mensajeSessionActiva', "");
                            var payload = response.data;

                            var accessToken = payload.access_token;
                            
                            sessionStorage.setItem('token', accessToken);
                            sessionStorage.setItem('refresh_token', response.data.refresh_token);
                            sessionStorage.setItem('usuario', JSON.stringify(response.data.respuesta.jsonResultado));
                            sessionStorage.setItem('listaCredencial', JSON.stringify(response.data.respuesta.jsonResultado.listaCredencialUsuarioFinerioDTO));
                            callback(true);
            				
            			}
            		
            		
                	  } else {
                		  callback(false);
                	  }
                	  
                  })
                  .catch(function(response) {
                	  console.log('response: '+response.status)
                	  if(response.status == 401){
                  		var mensaje = "No tiene permisos para este recursos o no tiene conexión de red. ";
  						$('div#mensajesErrorLogin').html(generarMensajeError(mensaje));
  				        $('div#mensajesErrorLogin').show();
      				}
                	if(response.status == 400){
                		var mensaje = "Usuario y/o constraseña invalida. si realiza mas de 5 intentos fallidos, su cuenta sera bloqueada. ";
						$('div#mensajesErrorLogin').html(generarMensajeError(mensaje));
				        $('div#mensajesErrorLogin').show();
    				}
                	if(response.status == 403){
    					var mensaje = "No tiene permisos para ver este recurso."
    						$('div#mensajesErrorLogin').html(generarMensajeError(mensaje));
    				        $('div#mensajesErrorLogin').show();
    				}
                	 if (response.status == 404) {
       			      console.log('Recurso no encontrado o inaccesible, verique su coneccion de red!');
       			      
       			      var mensaje = 'Recurso no encontrado o inaccesible, verique su coneccion de red';
       					$('div#mensajesErrorFront').html(generarMensajeError(mensaje));
       			        $('div#mensajesErrorFront').show();
       			      return true;
       			    }
        		});
            	

        }
         

// *************************************************************

// fin servicio************
    }
    

//	  }

//}])

})();