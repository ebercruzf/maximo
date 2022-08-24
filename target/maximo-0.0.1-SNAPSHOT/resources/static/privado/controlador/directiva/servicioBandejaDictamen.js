angular.module('mHome')

.factory('Bandeja', [ '$http','$q', function($http, $q) {

	var datosBan ={
			"datos":[{
    			"matriculaUsuario" : 99887766,
				"nombreUsuario" : "JUAN PEREZ ",
				"puestoUsuario" : "Usuario"
    		}]
	};
	return datosBan;
}
]
)

