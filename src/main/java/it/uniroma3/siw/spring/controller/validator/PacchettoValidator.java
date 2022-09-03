package it.uniroma3.siw.spring.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Pacchetto;
import it.uniroma3.siw.spring.service.PacchettoService;

@Component
public class PacchettoValidator implements Validator {
	
	@Autowired
	private PacchettoService pacchettoService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Pacchetto.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		if(pacchettoService.alreadyExists((Pacchetto)o)) {
			errors.reject("pacchetto duplicato");
		}
	}

}
