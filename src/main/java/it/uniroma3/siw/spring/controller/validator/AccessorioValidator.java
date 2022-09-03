package it.uniroma3.siw.spring.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Accessorio;
import it.uniroma3.siw.spring.service.AccessorioService;

@Component
public class AccessorioValidator implements Validator {
	
	@Autowired
	private AccessorioService accessorioService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Accessorio.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		if(accessorioService.alreadyExists((Accessorio)o)) {
			errors.reject("accessorio duplicato");
		}
	}
}
