package cz.jiripinkas.jba.annotation;

import java.lang.annotation.Annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import cz.jiripinkas.jba.repository.UserRepository;

public class UniqueUsernameValidator implements ConstraintValidator <UniqueUsername,String> {

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public void initialize(UniqueUsername constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if(userRepository ==null){
			return true;
		}
		return userRepository.findUserByName(username)==null;
	}


	

}
