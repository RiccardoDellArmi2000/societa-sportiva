package it.uniroma3.siw.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.spring.controller.validator.AllenatoreValidator;
import it.uniroma3.siw.spring.model.Allenatore;
import it.uniroma3.siw.spring.service.AllenatoreService;
import net.bytebuddy.asm.Advice.This;

@Controller
public class AllenatoreController {
	
	@Autowired
	private AllenatoreService allenatoreService;
	
	@Autowired
	private AllenatoreValidator allenatoreValidator;
	
	@PostMapping("/admin/allenatore")
	public String addAllenatore(@Validated @ModelAttribute(value="allenatore") Allenatore allenatore,
			BindingResult bindingResult, Model model) {
		this.allenatoreValidator.validate(allenatore, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.allenatoreService.save(allenatore);
			model.addAttribute("allenatore", allenatore);
			return "allenatore.html";
		}
		else {
			model.addAttribute("allenatore", allenatore);
			return "admin/allenatoreForm.html";
		}
	}
	
	@GetMapping("/allenatore/{id}")
	public String getAllenatore(@PathVariable("id") Long id, Model model) {
		Allenatore allenatore = allenatoreService.findById(id);
		model.addAttribute("allenatore", allenatore);
		return "allenatore.html";
	}
	
	@GetMapping("/allenatori")
	public String getAllenatori(Model model) {
		List<Allenatore> allenatori = allenatoreService.findAll();
		model.addAttribute("allenatori", allenatori);
		return "allenatori.html";
	}
	
	@GetMapping("/admin/allenatoreForm")
	public String allenatoreForm(Model model) {
		model.addAttribute("allenatore", allenatoreService.createAllenatore());
		return "admin/allenatoreForm.html";
	}
	
	@GetMapping("/admin/pacchetti/addPacchetto")
	public String addPacchetto(Model model) {
		model.addAttribute("allenatori", allenatoreService.findAll());
		return "admin/selezionaAllenatore.html";
	}
}
