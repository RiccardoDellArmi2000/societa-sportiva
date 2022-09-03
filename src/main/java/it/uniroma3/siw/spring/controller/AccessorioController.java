package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.spring.service.AccessorioService;

@Controller
public class AccessorioController {
	
	@Autowired
	private AccessorioService accessorioService;
	
	@GetMapping("accessorio/{accessorioId}") 
	public String getAccessorio(@PathVariable("accessorioId") Long id, Model model) {
		model.addAttribute("accessorio", accessorioService.findById(id));
		return "accessorio.html";
	}
}
