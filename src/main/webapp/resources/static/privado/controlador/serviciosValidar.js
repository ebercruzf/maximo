angular.module('mHome').
        factory("fValidacion", [function () {
        	
        	
        	
                var isNumEntero = function (valor, lonInt) {
                    var soloEntero = "^[0-9]{1," + lonInt + "}$";
                    valor = String(valor).replace(" ", "");
                    if (valor.length > 0 && valor.match(soloEntero)) {
                        return true;
                    } 
                    return false;                   
                };
                
                var isNumDecimal = function (valor, lonInt, lonDec) {
                    var decimal = "^[0-9]{1," + lonInt + "}[.]{1}[0-9]{1," + lonDec + "}$";
                    var soloEnteroPunto = "^[0-9]{1," + lonInt + "}[.]$";
                    var soloEntero = "^[0-9]{1," + lonInt + "}$"
                    valor = String(valor).replace(" ", "");
                    if (valor.length > 0 && (valor.match(decimal) || valor.match(soloEnteroPunto) || valor.match(soloEntero))) {
                        return true;
                    }
                    return false;   
                }; 
                
                var isLetras = function (valor, lonInt) {
                    var soloEntero = "^[a-zA-ZñÑáéíóúÁÉÍÓÚáéíóúÁÉÍÓÚäëïöüÄËÏÖÜ´ ]{1," + lonInt + "}$";
                    if (valor.length > 0 && valor.match(soloEntero)) {
                        return true;
                    } 
                    return false;
                };

                var isAlfaNumerico = function (valor, lonInt) {
                    var alfaNumerico = "^[a-zA-z0-9ñÑáéíóúÁÉÍÓÚáéíóúÁÉÍÓÚäëïöüÄËÏÖÜ´., ]{1," + lonInt + "}$";
                    if (valor.length > 0 && valor.match(alfaNumerico)) {
                        return true;
                    } 
                    return false;
                }; 

                var isVacio = function (valor) {
                    valor = valor+"";
                    if ((valor.replace(" ", "") == "") || (valor == undefined) || (valor == null) || (valor == 'null') || (valor == 'undefined'))
                        return true;
                    return false;
                };
                
                var isFecha = function(valor){
                    
                    var formatoFecha = "^[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}$";
                    if (valor.length > 0 && valor.match(formatoFecha)) {
                        return true;
                    } 
                    return false;
                };
               
                                
                var isRFC = function (valor) {                   
                    var sinHomoclabePF = "^[a-zA-z]{4}[0-9]{6}$";
                    var conHomoclabePF = "^[a-zA-z]{4}[0-9]{6}[a-zA-z0-9]{3}$";
                    var conHomoclabePM = "^[a-zA-z]{4}[0-9]{6}[a-zA-z0-9]{2}$";
                    if ((valor.length == 13 && valor.match(conHomoclabePF)) || 
                        (valor.length == 12 && valor.match(conHomoclabePM))) {
                        return true;
                    } else {
                        return false;
                    }
                };
                
                var isRFCMoral = function (valor) {                   
                    var conHomoclabePM = "^[a-zA-z]{3}[0-9]{6}[a-zA-z0-9]{3}$";
                    if ((valor.length == 12 && valor.match(conHomoclabePM))) {
                        return true;
                    } else {
                        return false;
                    }
                };
                
                var isCURP = function (valor) {                   
                    var sinHomoclabe = "^[a-zA-z]{4}[0-9]{6}[mMhH]{1}[a-zA-z]{5}$";
                    var conHomoclabe = "^[a-zA-z]{4}[0-9]{6}[mMhH]{1}[a-zA-z]{5}[0-9a-zA-z]{2}$";
                    if ((valor.length == 18 && valor.match(sinHomoclabe)) || //nunca se usa a menos que la lon cambia a 16
                        (valor.length == 18 && valor.match(conHomoclabe))) {
                        return true;
                    } else {
                        return false;
                    }
                };
                
                var isEmail = function(valor){
                    var email ="^[_a-zA-z0-9-.]{1,}[@]{1}[_a-zA-z0-9-]{1,}[.]{1}[_a-zA-z0-9-.]{1,}$";
                    if (valor.match(email)){
                        return true;
                    }
                    return false;
                };

                return {
                    isEmail:isEmail,
                    isRFCMoral:isRFCMoral,
                    isFecha:isFecha,
                    isNumEntero:isNumEntero,
                    isNumDecimal:isNumDecimal,
                    isLetras:isLetras,
                    isAlfaNumerico:isAlfaNumerico,
                    isVacio:isVacio,
                    isCURP:isCURP,
                    isRFC:isRFC,
                };
            }]);



function alfanumericoespecial(event) {

    $(document).trigger("keypress", "[onkeypress*='alfanumericoespecial(']");
}

$(document).on(
        'keypress',
        "[onkeypress*='alfanumericoespecial(']",
        function(event) {

            var key = (typeof event.which == 'number') ? event.which : event.keyCode;
            if ((event.ctrlKey)) {
                if (key == 64 || key == 92 || key == 94 || key == 96 || key == 126 || key == 172)
                    return false;
            } else {
                if ((key == 60 || key == 62 || key == 91 || key == 93 || key == 123 || key == 125 
                        || key == 42 || key == 94 || key == 124 || key == 172 || key == 176 || key == 191 || key == 63
                        || key == 39 || key == 168 || key == 33 || key == 161 || key == 180 || key == 64 || key == 92
                        || key == 94 || key == 96 || key == 126 || key == 172 || key == 61))
                    return false

            }

        }).on('paste', "[onkeypress*='alfanumericoespecial(']", function(event) {

            var pastedText = event.originalEvent.clipboardData ? event.originalEvent.clipboardData
                    .getData("text/plain") : window.clipboardData.getData("text");

    if (pastedText.match(/[^0-9A-Za-z\sñÑáéíóúÁÉÍÓÚÄËÏÖÜäëïöü\#\&\/\(\)\$\"\-\_\.\,\:\+\;]/gi)) {
        return false;
    }

    return true;

}).on('drop', "[onkeypress*='alfanumericoespecial(']", function(event) {

    var data = event.originalEvent.dataTransfer.getData("Text");

    if (data.match(/[^0-9A-Za-z\sñÑáéíóúÁÉÍÓÚÄËÏÖÜäëïöü\#\&\/\(\)\$\%\"\-\_\.\,\:\+\;]/gi)) {
        return false;
    }

    return true;

});


