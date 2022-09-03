package it.uniroma3.siw.spring.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Allenatore;
import it.uniroma3.siw.spring.service.AllenatoreService;

@Component
public class AllenatoreValidator implements Validator {
	
	@Autowired
	private AllenatoreService allenatoreService;
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Allenatore.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		if(allenatoreService.alreadyExists((Allenatore)o)) {
			errors.reject("allenatore duplicato");
		}
	}
}
