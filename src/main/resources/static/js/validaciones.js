jQuery(document).ready(function ($) {
	
	
         $("#validar_registro").click(function () {  	
      var  nombre = document.getElementById("nombre").value;
       var email = document.getElementById("correo").value;
        var rfc = document.getElementById("rfc").value;
       var  password = document.getElementById("password").value;
       var  c_password = document.getElementById("c_password").value;

        if (nombre === "") {
            document.getElementById("nombre").style.borderColor = '#e51313';
            return false;
        } else if (email === "") {
            document.getElementById("correo").style.borderColor = '#e51313';
            return false;
        } else if (rfc === "") {
            document.getElementById("rfc").style.borderColor = '#e51313';
            return false;
        } else if (password === "") {
            document.getElementById("password").style.borderColor = '#e51313';
            return false;
        } else if (c_password === "") {
            document.getElementById("c_password").style.borderColor = '#e51313';
            return false;
        }
        
        if (password !== c_password) {
            document.getElementById("password").style.borderColor = '#e51313';
            document.getElementById("c_password").style.borderColor = '#e51313';
            document.getElementById("nombre").style.borderColor = '#FFFFFF';
            document.getElementById("correo").style.borderColor = '#FFFFFF';
            document.getElementById("rfc").style.borderColor = '#FFFFFF';
            alert("las contraseñas no coinciden");
            return false;
        }
          if(!rfcValido(rfc)){
         alert("RFC invalido");
         
         document.getElementById("rfc").style.borderColor = '#e51313';
         return false;
         }
         });
 

    $("#validar_login").click(function () {
        var email = document.getElementById("correo").value;
        var password = document.getElementById("password").value;
        if (email === "") {
            document.getElementById("correo").style.borderColor = '#e51313';
            return false;
        } else if (password === "") {
            document.getElementById("password").style.borderColor = '#e51313';
            return false;
        }              
        
    });
    
    
    $("#ejecutar").click(function () {
        var palabra = document.getElementById("palabra").value;
        
        if (palabra === "") {
            document.getElementById("palabra").style.borderColor = '#e51313';
            return false;
        }
  
    });
    
    $("#actualizar_datos").click(function () {
        var  nombre = document.getElementById("nombre").value;
        var email = document.getElementById("correo").value;
         var rfc = document.getElementById("rfc").value;
        var  password = document.getElementById("password").value;
        var  c_password = document.getElementById("c_password").value;
        var direccion = document.getElementById("direccion").value;
        var telefono = document.getElementById("telefono").value;
        var web = document.getElementById("web").value;
        
        if (nombre === "") {
            document.getElementById("nombre").style.borderColor = '#e51313';
            return false;
        } else if (direccion === "") {
            document.getElementById("direccion").style.borderColor = '#e51313';
            return false;
        } else if (rfc === "") {
            document.getElementById("rfc").style.borderColor = '#e51313';
            return false;
        } else if (password === "") {
            document.getElementById("password").style.borderColor = '#e51313';
            return false;
        } else if (c_password === "") {
            document.getElementById("c_password").style.borderColor = '#e51313';
            return false;
        }
        else if (telefono === "") {
            document.getElementById("telefono").style.borderColor = '#e51313';
            return false;
        }
        
        else if (web === "") {
            document.getElementById("web").style.borderColor = '#e51313';
            return false;
        }
         if(!rfcValido(rfc)){
        alert("RFC invalido");
        document.getElementById("rfc").style.borderColor = '#e51313';
        return false;
        }
        if (password !== c_password) {
            document.getElementById("password").style.borderColor = '#e51313';
            document.getElementById("c_password").style.borderColor = '#e51313';
            document.getElementById("telefono").style.borderColor = '#FFFFFF';
            alert("las contraseñas no coinciden");
            return false;
        }
        
        
        

    });
});



function rfcValido(rfc) {

    var aceptarGenerico = true
    const re = /^([A-ZÑ&]{3,4}) ?(?:- ?)?(\d{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[12]\d|3[01])) ?(?:- ?)?([A-Z\d]{2})([A\d])$/;
    var validado = rfc.match(re);

    if (!validado){  //Coincide con el formato general del regex?
        return false;
    }
    //Separar el dígito verificador del resto del RFC
    const digitoVerificador = validado.pop(),
            rfcSinDigito = validado.slice(1).join(''),
            len = rfcSinDigito.length,
            //Obtener el digito esperado
            diccionario = "0123456789ABCDEFGHIJKLMN&OPQRSTUVWXYZ Ñ",
            indice = len + 1;
    var suma,digitoEsperado;

    if (len == 12){
        suma = 0
    }
    else{
        suma = 481; //Ajuste para persona moral
    }
    for (var i = 0; i < len; i++){
        suma += diccionario.indexOf(rfcSinDigito.charAt(i)) * (indice - i);
    }
    digitoEsperado = 11 - suma % 11;
    if (digitoEsperado == 11){
        digitoEsperado = 0;
        }  else if (digitoEsperado == 10){
        digitoEsperado = "A";
        }
    //El dígito verificador coincide con el esperado?
    // o es un RFC Genérico (ventas a público general)?
    if ((digitoVerificador != digitoEsperado)
            && (!aceptarGenerico || rfcSinDigito + digitoVerificador != "XAXX010101000")){
        return false;
    }
    else if (!aceptarGenerico && rfcSinDigito + digitoVerificador == "XEXX010101000"){
        return false;

    }
    return rfcSinDigito + digitoVerificador;

}
