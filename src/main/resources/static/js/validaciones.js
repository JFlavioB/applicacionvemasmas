jQuery(document).ready(function ($) {
	alert("validaciones");
	
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
            document.getElementById("email").style.borderColor = '#e51313';
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
            alert("las contraseñas no coinciden");
            return false;
        }
        /*  if(!rfcValido(rfc)){
         alert("RFC invalido");
         document.getElementById("rfc").style.borderColor = '#e51313';
         }*/
        
        alert("valida registro");
        
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
        alert("valida login");
        return false;
    });
});