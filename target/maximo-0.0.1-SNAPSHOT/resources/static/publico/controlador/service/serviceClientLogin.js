angular.module('mHome')

.service('apiServiceLogin', 
		['$window', function($window){

	var _usuario = null;

	//    **************si esta autenticado*************
	var obj = {};
	var obtenerToken = {};

	    
//	    ********************************************
	    this.isAuthenticatedActual = function() {
			this.payloads = this.getToken;
			if (this.payloads != null && this._getUser.nombre !=null && this._getUser.nombre.length > 0) {
				return true;
			}
			return false;
		};
//		***********************************************
//		this.e.status;
		this.isNoAutorizado = function (e){
		
		    if (e.status == 401) {

		      if (this.isAuthenticatedActual()) {
		    	  this.logout();
		      }

		      $window.location = "/maximo/login";
		      return true;
		    }
		    if (e.status == 400) {

			      if (this.isAuthenticatedActual()) {
			    	  this.logout();
			      }
			      $window.location = "/maximo/login";
			      return true;
			    }


		    if (e.status == 403) {
		      console.log('Acceso denegado no tienes acceso a este recurso!');
		      
		      var mensaje = 'Acceso denegado no tienes acceso a este recurso!';
				$('div#mensajesErrorFront').html(generarMensajeError(mensaje));
		        $('div#mensajesErrorFront').show();
		        
		      return true;
		    }

		    return false;
		  }
//	    ************************************************
	    this._usuario = null;
	    this._getUser = {};
	    this.getUsuario = function() {
			if (this._usuario != null) {
				return this._usuario;
			} else if (this._usuario == null && sessionStorage.getItem('usuario') != null) {
				this._usuario = JSON.parse(sessionStorage.getItem('usuario'));
				this._getUser = {
						"nombre": this._usuario.usuario.nombre,
						"primerApellido" : this._usuario.usuario.apellidoPaterno,
						"segundoApellido": this._usuario.usuario.apellidoMaterno,
						"usuario": this._usuario.usuario.cveUsuario
				};
				return this._usuario;
			}
			return null;
		};
//		******************************************************************
//	 	***********************************************
//		var salir = {};
		this.logout = function() {
			_token = null;
			_usuario = null;
			sessionStorage.clear();
			sessionStorage.removeItem('token');
			sessionStorage.removeItem('usuario');
             $window.location = "/maximo/login";
		}
//		return salir;
		// 	***********************************************
		
		this.getToken = function(tokenrecibido) {
			this.token = tokenrecibido;
			if (this.token != null) {
				return this.token;
			} else 
				if (this.token == null && sessionStorage.getItem('token') != null) {
//				this.token = JSON.parse(sessionStorage.getItem('token'));
				this.token = sessionStorage.getItem('token');
				return this.token;
			}
			return this.token;
		};

//           ************************************************FINALIZA CONTADOR***********************************
	}
//******************termina_servicio*********************
]
)

	;
//]);