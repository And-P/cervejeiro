package com.umbrella.cervejeiro.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.umbrella.cervejeiro.model.Usuario;
import com.umbrella.cervejeiro.repository.Grupos;
import com.umbrella.cervejeiro.service.CadastroUsuarioService;
import com.umbrella.cervejeiro.service.exception.EmailUsuarioJaCadastradoException;
import com.umbrella.cervejeiro.service.exception.SenhaObrigatoriaUsuarioException;


@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
	
	//Parametros/////////////////////////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
<<<<<<< HEAD
	@Autowired 
=======
	@Autowired
>>>>>>> 8ff219625608587fecc7d009552241817f9d8816
	private Grupos grupos; 

	//Funções//////////////////////////////////////////////////////////////////////////////////////////////
	
	@RequestMapping("/novo")
	public ModelAndView novo(Usuario usuario) {
		ModelAndView mv = new ModelAndView("usuario/CadastroUsuario");
<<<<<<< HEAD
		mv.addObject("grupos", grupos.findAll());
=======
			mv.addObject("grupos", grupos.findAll());
>>>>>>> 8ff219625608587fecc7d009552241817f9d8816
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes ){
		if(result.hasErrors()) {
			return novo(usuario);
		}
		
		try {
			cadastroUsuarioService.salvar(usuario);
		}catch(EmailUsuarioJaCadastradoException e) {
			result.rejectValue("email", e.getMessage(), e.getMessage());
			return novo(usuario);
		}catch(SenhaObrigatoriaUsuarioException e) {
			result.rejectValue("senha", e.getMessage(), e.getMessage());
			return novo(usuario);
		}
		
		
		attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso");
		return new ModelAndView("redirect:/usuarios/novo");
	}
}









