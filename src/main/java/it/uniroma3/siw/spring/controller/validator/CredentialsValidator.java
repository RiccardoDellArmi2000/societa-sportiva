package it.uniroma3.siw.spring.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Credentials;
import it.uniroma3.siw.spring.model.User;
import it.uniroma3.siw.spring.service.CredentialsService;

@Component
public class CredentialsValidator implements Validator {
	
	@Autowired
	private CredentialsService credentialsService;
	
	final Integer MAX_USERNAME_LENGHT = 20;
	final Integer MIN_USERNAME_LENGHT = 6;
	final Integer MAX_PASSWORD_LENGHT = 20;
	final Integer MIN_PASSWORD_LENGHT = 6;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		Credentials credentials = (Credentials)o;
		String username = credentials.getUsername().trim();
		String password = credentials.getPassword().trim();
		
		if(username.isEmpty())
			errors.rejectValue("username", "required");
		else if(username.length() < MIN_USERNAME_LENGHT || username.length() > MAX_USERNAME_LENGHT)
			errors.rejectValue("username", "size");
		else if(this.credentialsService.getCredentials(username) != null)
			errors.rejectValue("username", "duplicate");
		
		if(password.isEmpty())
			errors.rejectValue("password", "required");
		if(password.length() < MIN_PASSWORD_LENGHT || password.length() > MAX_PASSWORD_LENGHT)
			errors.rejectValue("password", "size");
	}

}
