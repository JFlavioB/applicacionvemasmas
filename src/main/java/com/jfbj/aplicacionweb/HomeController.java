package com.jfbj.aplicacionweb;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

	@GetMapping(path = "/singup") // 1
	public String getInfoForm() {
		return "registro";
	}

	@PostMapping(path = "/singup-submit") // 2
	public String submit(@ModelAttribute Usuario usuario, Model model) {
		model.addAttribute("usuario", usuario);
		return "index";
	}

	@GetMapping("/menu")
	public String menu(Model model) {
		model.addAttribute("tamano", usuarioregistrados);
		return "menu";
	}

}