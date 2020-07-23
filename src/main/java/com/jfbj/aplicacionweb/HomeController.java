package com.jfbj.aplicacionweb;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import model.Palabra;
import model.Usuario;

@Controller
public class HomeController {

	ArrayList<Usuario> usuarios = new ArrayList<>();
	Usuario usuarioSesion = new Usuario();
	int usuarioregistrados = 0;

	@GetMapping("/registro") // 1
	public String registro() {
		return "registro";
	}

	@PostMapping("/registrar")
	public String agregar(@ModelAttribute Usuario usuario, Model model) {
		Usuario u = new Usuario();
		u.setNombre(usuario.getNombre());
		u.setCorreo(usuario.getCorreo());
		u.setRfc(usuario.getRfc());
		u.setPassword(usuario.getPassword());

		boolean existeRFC = false;
		for (int i = 0; i < usuarios.size(); i++) {
			if (u.getRfc().equals(usuarios.get(i).getRfc())) {
				existeRFC = true;
			}
		}
		if (!existeRFC) {
			model.addAttribute("exito", "Registro Exitoso");
			usuarioregistrados = usuarioregistrados + 1;
			model.addAttribute("tamano", usuarioregistrados);
			usuarios.add(usuario);
			return "login";
		} else {
			model.addAttribute("error", "Ya existe RFC");
			return "index";
		}
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/acceder")
	public String acceder(@ModelAttribute Usuario usuario, Model model) {

		for (int i = 0; i < usuarios.size(); i++) {
			if (usuario.getCorreo().equals(usuarios.get(i).getCorreo())
					&& usuario.getPassword().equals(usuarios.get(i).getPassword())) {
				usuarioSesion.setNombre(usuarios.get(i).getNombre());
				usuarioSesion.setCorreo(usuarios.get(i).getCorreo());
				usuarioSesion.setPassword(usuarios.get(i).getPassword());
				usuarioSesion.setRfc(usuarios.get(i).getRfc());
				usuarioSesion.setDireccion("Sin Asignar");
				usuarioSesion.setTelefono("Sin Asignar");
				usuarioSesion.setWeb("Sin Asignar");
				model.addAttribute("sesionusuario", usuarioSesion);
				return "menu";
			}
		}
		model.addAttribute("error", "Los valores introducidos no coinciden con el sistema.");
		return "login";

	}

	@GetMapping("/menu")
	public String menu(Model model, Usuario usuario, Palabra palabra) {
		model.addAttribute("tamano", usuarioregistrados);
		model.addAttribute("sesionusuario", usuarioSesion);
		return "menu";
	}

	@PostMapping("/actualizar")
	public String actualizar(@ModelAttribute Usuario usuario, Model model) {
		if (usuario != null) {

			usuarioSesion.setNombre(usuario.getNombre());
			usuarioSesion.setCorreo(usuario.getCorreo());
			usuarioSesion.setPassword(usuario.getPassword());
			usuarioSesion.setRfc(usuario.getRfc());
			usuarioSesion.setDireccion(usuario.getDireccion());
			usuarioSesion.setTelefono(usuario.getTelefono());
			usuarioSesion.setWeb(usuario.getWeb());
			model.addAttribute("sesionusuario", usuarioSesion);
			return "menu";
		} else {
			return "registro";
		}
	}

	@GetMapping("/palabras")
	public String palabras() {
		return "palabras";
	}
	@PostMapping("/palindromo")
	public String palindomo(@ModelAttribute Palabra palabra, Model model, Usuario usuario) {
		
		String  frase = palabra.getPalabra();
		String[]  palabras = frase.split(" ");
        ArrayList<Palabra> arraypalabraspalindromas = new ArrayList<>();
        ArrayList<Palabra> arraypalabrasnopalindromas = new ArrayList<>();
        
		if(frase != null) {
			
			  for (int i = 0; i < palabras.length; i++) {
                  Palabra pnp = new Palabra();
                  for (int j = 0; j < palabras[i].length(); j++) {
                      if (palabras[i].charAt(j) != palabras[i].charAt(palabras[i].length() - j - 1)) {
                          System.out.println("la palabra no es " + palabras[i]);
                           pnp.setPalabra(palabras[i]);
                          arraypalabrasnopalindromas.add(pnp);
                          break;
                      } else {
                          Palabra palabraspalidromas = new Palabra();
                          palabraspalidromas.setPalabra(palabras[i]);
                          arraypalabraspalindromas.add(palabraspalidromas);
                          System.out.println("la palabra  es " + palabras[i]);
                          break;
                      }
                  }                     
              }
			  model.addAttribute("palabrasp", arraypalabraspalindromas);
			  model.addAttribute("palabrasnp", arraypalabrasnopalindromas);
			  
			return "palabras";
		}		
		
		
		
		model.addAttribute("palabras", "no hay");
		return "menu";
		
		
		
		//return "palabras"; 
	}

	
	@GetMapping("/logout")
	public String logout() {
		return "login";
	}
	@GetMapping(path = "/singup") // 1
	public String getInfoForm() {
		return "registro";
	}
	
	
	/*
	 * 
	 * @GetMapping("/actualizar") public String greeting(@PathVariable String
	 * nombre, @PathVariable String correo, @PathVariable String rfc,
	 * 
	 * @PathVariable String direccion, @PathVariable String telefono, @PathVariable
	 * String web,
	 * 
	 * @PathVariable String password) { Usuario u = new Usuario();
	 * 
	 * usuarioSesion.setNombre(nombre); usuarioSesion.setCorreo(correo);
	 * usuarioSesion.setPassword(password); usuarioSesion.setRfc(rfc);
	 * usuarioSesion.setDireccion(direccion); usuarioSesion.setTelefono(telefono);
	 * usuarioSesion.setWeb(web);
	 * 
	 * return "menu"; }
	 */

	@PostMapping(path = "/singup-submit") // 2
	public String submit(@ModelAttribute Usuario usuario, Model model) {
		model.addAttribute("usuario", usuario);
		return "index";
	}

}