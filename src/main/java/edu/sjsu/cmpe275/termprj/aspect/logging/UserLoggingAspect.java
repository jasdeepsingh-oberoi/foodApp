package edu.sjsu.cmpe275.termprj.aspect.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import edu.sjsu.cmpe275.termprj.model.User;

@Component
@Aspect
public class UserLoggingAspect {

	/**
		 Pointcut for adding a user
	 */
	@Pointcut("execution(* edu.sjsu.cmpe275.termprj.controller.UserController.createUser(..))")
	public void createUser() {}
	/**
	 * Pointcut for user signin
	 */
	@Pointcut("execution(* edu.sjsu.cmpe275.termprj.controller.UserController.signInUser(..))")
	public void signInUser() {}
	
	/**
	 * Pointcut for verifying user by verification code 
	 */
	@Pointcut("execution(* edu.sjsu.cmpe275.termprj.controller.UserController.authenticateUser(..))")
	public void authenticateUser() {}
	
	
	
	/**
	 * @AfterReturning Advice after adding a user
	 * @param joinPoint
	 * @param response
	 */
	@AfterReturning(pointcut="createUser()",returning = "response")
	public void addUserAfterAdvice(JoinPoint joinPoint,ResponseEntity<User> response) {
		if(response.getStatusCode()==HttpStatus.CREATED){
			User user = response.getBody();
			System.out.println("User create request successfull");
			System.out.println("An mail with verification code has been sent to the "+user.getEmail());
		}
	}
	
	
	/**
	 * @AfterReturning Advice after user signin
	 * @param joinPoint
	 * @param response
	 */
	@AfterReturning(pointcut="signInUser()",returning = "response")
	public void signinUserAfterAdvice(JoinPoint joinPoint,ResponseEntity<User> response) {
		if(response.getStatusCode()==HttpStatus.OK){
			User user = response.getBody();
			System.out.println("User has signed in successfully with email id "+user.getEmail());
		}
	}
	
	
	/**
	 * @AfterReturning Advice after user verification
	 * @param joinPoint
	 * @param response
	 */
	@AfterReturning(pointcut="authenticateUser()",returning = "response")
	public void authenticateUserAfterAdvice(JoinPoint joinPoint,ResponseEntity<User> response) {
		if(response.getStatusCode()==HttpStatus.OK){
			User user = response.getBody();
			System.out.println("User has been verified with email "+user.getEmail());
		}
	}
}