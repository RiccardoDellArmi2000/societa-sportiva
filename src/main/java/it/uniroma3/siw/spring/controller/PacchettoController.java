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

import it.uniroma3.siw.spring.controller.validator.PacchettoValidator;
import it.uniroma3.siw.spring.model.Allenatore;
import it.uniroma3.siw.spring.model.Pacchetto;
import it.uniroma3.siw.spring.service.AllenatoreService;
import it.uniroma3.siw.spring.service.PacchettoService;

@Controller
public class PacchettoController {
	
	@Autowired
	private PacchettoService pacchettoService;
	
	@Autowired
	private AllenatoreService allenatoreService;
	
	@Autowired
	private PacchettoValidator pacchettoValidator;
	
	@PostMapping("/admin/allenatore/{allenatoreId}/pacchetto")
	public String addPacchetto(@Validated @ModelAttribute(value="pacchetto") Pacchetto pacchetto,
			@PathVariable("allenatoreId") Long allenatoreId,
			BindingResult bindingResult, Model model) {
		
		this.pacchettoValidator.validate(pacchetto, bindingResult);
		
		if(!bindingResult.hasErrors()) {
			this.pacchettoService.save(pacchetto, allenatoreService.findById(allenatoreId));
			model.addAttribute("pacchetto", pacchetto);
			model.addAttribute("sportAssenti", pacchettoService.findSportNotInPacchetto(pacchetto.getId()));
			return "admin/addSportToPacchetto.html";
		}
		else {
			model.addAttribute("pacchetto", pacchetto);
			return "admin/pacchettoForm.html";
		}
	}
	
	@GetMapping("/admin/allenatore/{allenatoreId}/newPacchetto")
	public String newPacchetto(@PathVariable("allenatoreId") Long allenatoreId, Model model) {
		model.addAttribute("allenatore", allenatoreService.findById(allenatoreId));
		model.addAttribute("pacchetto", pacchettoService.createPacchetto());
		return "admin/pacchettoForm.html";
	}
	
	@GetMapping("/pacchetto/{pacchettoId}")
	public String getPacchetto(@PathVariable("pacchettoId") Long pacchettoId, Model model) {
		model.addAttribute("pacchetto", pacchettoService.findById(pacchettoId));
		return "pacchetto.html";
	}
	
	@GetMapping("/allenatore/{allenatoreId}/pacchetti")
	public String getPacchetti(@PathVariable("allenatoreId") Long allenatoreId, Model model) {
		Allenatore allenatore = allenatoreService.findById(allenatoreId);
		model.addAttribute("pacchetti", pacchettoService.findAllByAllenatore(allenatore));
		model.addAttribute("allenatore", allenatore);
		return "pacchetti.html";
	}
	
	@GetMapping("/pacchetti")
	public String getPacchetti(Model model) {
		model.addAttribute("pacchetti", pacchettoService.findAll());
		return "pacchetti.html";
	}
	
	@GetMapping("/admin/pacchetto/{pacchettoId}/{sportId}")
	public String addSportToNewPacchetto(@PathVariable("pacchettoId") Long pacchettoId, 
			@PathVariable("sportId") Long sportId, Model model) {
		this.pacchettoService.addSportToPacchetto(pacchettoId, sportId);
		model.addAttribute(pacchettoService.findById(pacchettoId));
		model.addAttribute("sportAssenti", pacchettoService.findSportNotInPacchetto(pacchettoId));
		return "admin/addSportToPacchetto.html";
	}
	
	@GetMapping("/admin/pacchetto/{pacchettoId}/add/{sportId}")
	public String addSportToPacchetto(@PathVariable("pacchettoId") Long pacchettoId, 
			@PathVariable("sportId") Long sportId, Model model) {
		this.pacchettoService.addSportToPacchetto(pacchettoId, sportId);
		model.addAttribute(pacchettoService.findById(pacchettoId));
		model.addAttribute("sportAssenti", pacchettoService.findSportNotInPacchetto(pacchettoId));
		return "admin/editPacchetto.html";
	}
	
	@GetMapping("/admin/pacchetto/{pacchettoId}/removeSport/{sportId}")
	public String removeSportFromPacchetto(@PathVariable("pacchettoId") Long pacchettoId,
			@PathVariable("sportId") Long sportId, Model model) {
		this.pacchettoService.removeSportFormPacchetto(pacchettoId, sportId);
		model.addAttribute(pacchettoService.findById(pacchettoId));
		model.addAttribute("sportAssenti", pacchettoService.findSportNotInPacchetto(pacchettoId));
		return "admin/editPacchetto.html";
	}
	
	@GetMapping("/admin/editPacchetto")
	public String choosePacchettoToEdit(Model model) {
		model.addAttribute("pacchetti", pacchettoService.findAll());
		return "admin/selectPacchettoToEdit.html";
	}
	
	@GetMapping("/admin/editPacchetto/{pacchettoId}")
	public String editPacchetto(@PathVariable("pacchettoId") Long pacchettoId, Model model) {
		model.addAttribute("pacchetto", pacchettoService.findById(pacchettoId));
		model.addAttribute("sportAssenti", pacchettoService.findSportNotInPacchetto(pacchettoId));
		return "admin/editPacchetto.html";
	}
	
	@GetMapping("/admin/removePacchetto")
	public String choosePacchettoToRemove(Model model) {
		model.addAttribute("pacchetti", pacchettoService.findAll());
		return "admin/selectPacchettoToRemove.html";
	}

	@GetMapping("/admin/removePacchetto/{pacchettoId}")
	public String removePacchetto(@PathVariable("pacchettoId")Long pacchettoId, Model model) {
		model.addAttribute("pacchetto", pacchettoService.findById(pacchettoId));
		return "admin/removePacchetto.html";
	}
	
	@GetMapping("/admin/confermaRemovePacchetto/{pacchettoId}")
	public String confermaRemovePacchetto(@PathVariable("pacchettoId")Long pacchettoId, Model model) {
		pacchettoService.removePacchetto(pacchettoId);
		model.addAttribute("pacchetti", pacchettoService.findAll());
		return "admin/selectPacchettoToRemove.html";
	}
}
