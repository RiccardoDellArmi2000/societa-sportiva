package it.uniroma3.siw.spring.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Sport;
import it.uniroma3.siw.spring.service.SportService;

@Component
public class SportValidator implements Validator{
	
	@Autowired
	private SportService sportService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Sport.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		if(this.sportService.alreadyExists((Sport)o)) {
			errors.reject("sport duplicato");
		}
	}
}
