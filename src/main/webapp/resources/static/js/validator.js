function validarFormato(regla, campos) {
    var str = "¡Error! ";
    var errorFormulario = str.bold();
    var validacion = "";
    var valor = $("#" + campos.campo).val();
    generarEstiloError(campos.campo, false);
    switch (regla) {
        case ('obligatorio'):
            if ((valor === "" || valor === undefined || valor === null)) {
                validacion = "Debe captura el  campo " + campos.campo + "  marcado como obligatorio.";
                generarEstiloError(campos.campo, true);
            }
            break;
        case ('camposObligatorios'):
            if ((valor === "" || valor === undefined || valor === null)) {
                validacion = errorFormulario + "No ha llenado todos los campos requeridos. Por favor verifique.";
                generarEstiloError(campos.campo, true);
            }
            break;
        case ('formatoCurp'):
            valor = valor.toUpperCase();
            var expregCurp = /^[A-Z]{1}[A-Z]{1}[A-Z]{2}[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])[HM]{1}(AS|BC|BS|CC|CS|CH|CL|CM|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)[B-DF-HJ-NP-TV-Z]{3}[0-9A-Z]{1}[0-9]{1}$/;
            if (!expregCurp.test(valor)) {
                validacion =errorFormulario + campos.campo + " incorrecto(a). Por favor verifique.";
                generarEstiloError(campos.campo, true);
            }
            break;
        case ('formatoCorreo'):
            valor = valor.toLowerCase();
            var expregCorreo = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.([a-zA-Z]{2,4})+$/;
            if (!expregCorreo.test(valor)) {
                validacion =errorFormulario + "Correo electr&oacute;nico incorrecto(a). Por favor verifique.";
                generarEstiloError(campos.campo, true);
            }
            break;
        case ('formatoPassword'):
            var expregPassword = /^[a-zA-Z0-9�ѡ!_@#.,\$%&\*\?�\/]{8,20}$/;
            if (!expregPassword.test(valor)) {
                validacion = campos.campo + " incorrecto(a). Por favor verifique";
                generarEstiloError(campos.campo, true);
            }
            break;
        default:

            break;

    }
    return validacion;
}

function validarCampos(regla, campos) {

    var str = "¡Error! ";
    var errorFormulario = str.bold();
    var validacion = "";
    var valor = $("#" + campos.campo).val();
    var valor2 = $("#" + campos.campo2).val();
    switch (regla) {
        case ('passwordIgual'):
            if (valor !== valor2) {
                validacion = "Tu Contraseña no coincide vuelve a intentarlo.";
                generarEstiloError(campos.campo, true);
            }
            break;
        case ('correoIgual'):
            if (valor !== valor2) {
                validacion = "Tu dirección de correo electrónico no coincide vuelve a intentarlo.";
                generarEstiloError(campos.campo, true);
            }
            break;
        case ('igual'):
            if (valor === valor2) {
                validacion = "La contraseña nueva es igual a la contrase�a actual, favor de verificar.";
                generarEstiloError(campos.campo, true);
            }
            break;
        case ('combos'):
            if (valor === "" || valor === undefined || valor === null || valor === "0") {
                validacion = errorFormulario + "No ha llenado todos los campos requeridos. Por favor verifique.";
                generarEstiloError(campos.campo, true);
            }
            break;
        case ('tamanioNSS'):
            if ((valor.length < 11 || valor.length > 12) && valor.length != 0) {
                validacion = errorFormulario + "NSS incorrecto. Por favor verifique.";
                generarEstiloError(campos.campo, true);
            }
            break;
        case ('tamanioMatricula'):
            if ((valor.length < 8 || valor.length > 9) && valor.length != 0) {
                validacion = errorFormulario + "Matricula incorrecta. Por favor verifique.";
                generarEstiloError(campos.campo, true);
            }
            break;
        case ('tamanioClaveDepartamental'):
            if (valor.length > 10) {
                validacion = errorFormulario + "Clave Departamental incorrecta. Por favor verifique.";
                generarEstiloError(campos.campo, true);
            }
            break;
    }
    return validacion;
}

function validarCamposCompletos(regla, campos) {

    var str = "¡Error! ";
    var errorFormulario = str.bold();
    var validacion = "";
    var valor = $("#" + campos.campo).val();
    try {
        var div = $("#" + campos.campo)["0"].parentElement.parentElement.firstElementChild.firstElementChild;
        var lab = $("#" + campos.campo)["0"].parentElement.parentElement.children[2];
    } catch (e) {

    }

    switch (regla) {
        case ('campoObligatorio'):
            if ((valor === "" || valor === undefined || valor === null)) {
                validacion = errorFormulario + "No ha llenado todos los campos requeridos. Por favor verifique.";
                generarEstiloErrorCampo(campos.campo, true);
                generarEstiloSpan(div, true);
                labelCampoObligatorio(lab, true);
            } else {
                generarEstiloErrorCampo(campos.campo, false);
                generarEstiloSpan(div, false);
                labelCampoObligatorio(lab, false);
            }
            break;
        case ('combosCompletos'):
            if (valor === "null"||valor === "" || valor === undefined || valor === null || valor === "0"|| valor === "-1") {
                validacion = errorFormulario + "No ha llenado todos los campos requeridos. Por favor verifique.";
                generarEstiloErrorCampo(campos.campo, true);
                generarEstiloSpan(div, true);
                labelCampoObligatorio(lab, true);
            } else {
                generarEstiloErrorCampo(campos.campo, false);
                generarEstiloSpan(div, false);
                labelCampoObligatorio(lab, false);
            }
            break;

    }
    return validacion;
}

function validarCampo(reglas) {

    $('div#mensajesError').html();
    var cont = 0;
    for (cont; cont < reglas.length; cont++) {
        var mensaje = validarReglaCampos(reglas[cont].validacion, reglas[cont].campos);
        if (mensaje !== "") {
            $('div#mensajesError').html(generarMensajeError(mensaje));
            return false;
        }
    }
    return true;
}

function validarReglaCampos(regla, campos) {

    var comprobacion = "";
    var contador = 0;
    for (contador = 0; contador < campos.length; contador++) {
        var validacion = validarFormato(regla, campos[contador]);
        var validacion2 = validarCampos(regla, campos[contador]);
        var validacion3 = validarCamposCompletos(regla, campos[contador]);
        if (comprobacion === "" && validacion !== "" && validarFormato !== "") {
            comprobacion = validacion;
        } else if (comprobacion === "" && validacion2 !== "" && validarCampos !== "") {
            comprobacion = validacion2;
        } else if (comprobacion === "" && validacion3 !== "" && validarCamposCompletos !== "") {
            comprobacion = validacion3;
        }
    }
    return comprobacion;
}
function generarMensajeError(mensaje) {

    var html = '<div class="alert alert-danger" id="error">';
    html += '<p>';
    html += mensaje;
    html += '</p></div>';
    return html;
}

function generarEstiloSpan(campo, estado) {

    if (campo != null || campo != undefined || campo != "") {
        if (estado) {

            campo.className = "red";
        } else {
            campo.className = "";
        }
    }

}

function labelCampoObligatorio(campo, estado) {

    if (campo != null || campo != undefined || campo != "") {
        if (estado) {

            campo.style.display = "block";
        } else {
            campo.style.display = "none";
        }
    }

}

function generarEstiloErrorCampo(campo, estado) {

    if (estado) {
        $("#" + campo).attr('style', 'border:1px solid red; resize: none;');

    } else {
        $("#" + campo).attr('style', 'border:1px solid #cccccc; resize: none;');

    }

}

function generarMensajeSucces(mensaje) {

    var html = '<div class="alert alert-success">';
    html += '<p>';
    html += mensaje;
    html += '</p></div>';
    return html;
}

function generarEstiloError(campo, estado) {

    if (estado) {
        $("#" + campo).prop('style', 'border:1px solid red');
    } else {
        $("#" + campo).prop('style', 'border:1px solid #cccccc');
    }
}

function allowOnlyNumbers(event) {

    $(document).trigger("keypress", "[onkeypress*='allowOnlyNumbers(']");
}

$(document).on('keypress', "[onkeypress*='allowOnlyNumbers(']", function(event) {

    var key = (typeof event.which == 'number') ? event.which : event.keyCode;
    if ((event.ctrlKey)) {
        if (key == 118)
            return true;
    } else {
        if (!((key >= 48 && key <= 57) || key == 0 || key == 8 || key == 45))
            return false

    }

}).on('paste', "[onkeypress*='allowOnlyNumbers(']", function(event) {

    var pastedText = event.originalEvent.clipboardData ? event.originalEvent.clipboardData
            .getData("text/plain") : window.clipboardData.getData("text");

    if (!pastedText.match(/^[0-9]*$/)) {
        return false;
    }

    return true;

}).on('drop', "[onkeypress*='allowOnlyNumbers(']", function(event) {

  
    var data = event.originalEvent.dataTransfer.getData("Text");

    if (!data.match(/^[0-9]*$/)) {
        return false;
    }

    return true;

})

function allowNumbersOnly(event) {

    console.log("which: " + event.which + " key: " + event.keyCode);
    var key = event.which || event.keyCode;
    if (!((key >= 48 && key <= 57) || key == 0 || key == 8 || (event.keyCode >= 35 && event.keyCode <= 39) || key == 9
            || key == 13 || event.keyCode == 46)) {
        return false;
    }
    return true;
}
// funcion para permtir alfanumericos con caracteres especiales
function alfanumerico50(event) {

    $(document).trigger("keypress", "[onkeypress*='alfanumerico50(']");
}

$(document).on(
        'keypress',
        "[onkeypress*='alfanumerico50(']",
        function(event) {

            var key = (typeof event.which == 'number') ? event.which : event.keyCode;
            if ((event.ctrlKey)) {
                if (key == 64 || key == 92 || key == 94 || key == 96 || key == 126 || key == 172)
                    return false;
            } else {
                if ((key == 60 || key == 62 || key == 91 || key == 93 || key == 123 || key == 125 || key == 43
                        || key == 42 || key == 94 || key == 124 || key == 172 || key == 176 || key == 191 || key == 63
                        || key == 39 || key == 168 || key == 33 || key == 161 || key == 180 || key == 64 || key == 92
                        || key == 94 || key == 96 || key == 126 || key == 172 || key == 61 || key == 35|| key == 36|| key == 38 ))
                    return false

            }

        }).on('paste', "[onkeypress*='alfanumerico50(']", function(event) {

            var pastedText = event.originalEvent.clipboardData ? event.originalEvent.clipboardData
                    .getData("text/plain") : window.clipboardData.getData("text");

    if (pastedText.match(/[^0-9A-Za-z\sñÑáéíóúÁÉÍÓÚÄËÏÖÜäëïöü\/\(\)\%\"\-\_\.\,\:\;]/gi)) {
        return false;
    }

    return true;

}).on('drop', "[onkeypress*='alfanumerico50(']", function(event) {

    var data = event.originalEvent.dataTransfer.getData("Text");

    if (data.match(/[^0-9A-Za-z\sñÑáéíóúÁÉÍÓÚÄËÏÖÜäëïöü\/\(\)\%\"\-\_\.\,\:\;]/gi)) {
        return false;
    }

    return true;

});

// funcion para permtir alfanumericos sin caracteres especiales
function allowAlfanumericoTxt(event) {

    $(document).trigger("keypress", "[onkeypress*='allowAlfanumericoTxt(']");
}

$(document).on(
        'keypress',
        "[onkeypress*='allowAlfanumericoTxt(']",
        function(event) {

            var key = (typeof event.which == 'number') ? event.which : event.keyCode;
            if ((event.ctrlKey)) {
                if (key == 64 || key == 92 || key == 94 || key == 96 || key == 126 || key == 172)
                    return false;
            } else {
                if (!((key >= 48 && key <= 57) || (key >= 65 && key <= 90) || (key >= 97 && key <= 122) || (key == 209)
                        || (key == 241) || (key == 0) || (key == 8)))
                    return false

            }

        }).on('paste', "[onkeypress*='allowAlfanumericoTxt(']", function(event) {

            var pastedText = event.originalEvent.clipboardData ? event.originalEvent.clipboardData
                    .getData("text/plain") : window.clipboardData.getData("text");

    if (pastedText.match(/[^0-9A-Za-z]/gi)) {
        return false;
    }

    return true;

}).on('drop', "[onkeypress*='allowAlfanumericoTxt(']", function(event) {

  
    var data = event.originalEvent.dataTransfer.getData("Text");

    if (data.match(/[^0-9A-Za-z]/gi)) {
        return false;
    }

    return true;

})
