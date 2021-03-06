package com.umbrella.cervejeiro.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.umbrella.cervejeiro.controller.page.PageWrapper;
import com.umbrella.cervejeiro.model.Cerveja;
import com.umbrella.cervejeiro.model.Origem;
import com.umbrella.cervejeiro.model.Sabor;
import com.umbrella.cervejeiro.repository.Cervejas;
import com.umbrella.cervejeiro.repository.Estilos;
import com.umbrella.cervejeiro.repository.filter.CervejaFilter;
import com.umbrella.cervejeiro.service.CadastroCervejaService;


@Controller
@RequestMapping("/cervejas")
public class CervejasController {
	
	//PARAMETROS
	
	@Autowired
	private Estilos estilos;

	@Autowired
	private CadastroCervejaService cadastroCervejaService;
	
	@Autowired
	private Cervejas cervejas;
	
	//	MÉTODOS 
	
	@RequestMapping("/novo")
	 public ModelAndView novo(Cerveja cerveja) {
	 	 ModelAndView mv = new ModelAndView("cerveja/CadastroCerveja");
			 mv.addObject("sabores", Sabor.values());
			 mv.addObject("estilos", estilos.findAll());
			 mv.addObject("origens", Origem.values());
		 return mv;
	 }
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)				//parametro sem uso
	public ModelAndView cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return novo(cerveja);
		}
		
		// Salvar no banco de dados...
		cadastroCervejaService.salvar(cerveja);
		attributes.addFlashAttribute("mensagem", "Cerveja salva com sucesso!");
		
		return new ModelAndView("redirect:/cervejas/novo");
	}
	
	
	@GetMapping
	public ModelAndView pesquisar(CervejaFilter cervejaFilter, BindingResult result,
			@PageableDefault(size = 4) Pageable pageable, HttpServletRequest httpServletRequest) {
		
		ModelAndView mv = new ModelAndView("cerveja/PesquisaCervejas");
		mv.addObject("estilos", estilos.findAll());
		mv.addObject("sabores", Sabor.values());
		mv.addObject("origens", Origem.values());
		
		PageWrapper<Cerveja> paginaWrapper = new PageWrapper<>(cervejas.filtrar(cervejaFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		
		return mv;
	}
}