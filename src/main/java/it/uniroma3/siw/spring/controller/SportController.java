package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.spring.controller.validator.SportValidator;
import it.uniroma3.siw.spring.model.Sport;
import it.uniroma3.siw.spring.service.SportService;

@Controller
public class SportController {
	
	@Autowired
	private SportService sportService;
	
	@Autowired
	private SportValidator sportValidator;
	
	@GetMapping("sport/{sportId}")
	public String getSport(@PathVariable("piattoId") Long id, Model model) {
		model.addAttribute("sport", this.sportService.findById(id));
		return "sport.html";
	}
	
	@GetMapping("/admin/sportForm")
	public String sportForm(Model model) {
		Sport s = new Sport();
		model.addAttribute("sport", s);
		return "admin/sportForm.html";
	}
	
	@PostMapping("/admin/sport")
	public String addSport(@Validated @ModelAttribute(value="allenatore") Sport sport,
			BindingResult bindingResult, Model model) {
		this.sportValidator.validate(sport, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.sportService.save(sport);
			model.addAttribute("sport", sport);
			return "sport.html";
		}
		else {
			model.addAttribute("sport", sport);
			return "admin/allenatoreForm.html";
		}
	}
}
