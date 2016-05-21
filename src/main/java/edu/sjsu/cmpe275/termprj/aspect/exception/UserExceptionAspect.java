package edu.sjsu.cmpe275.termprj.aspect.exception;

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
public class UserExceptionAspect {

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
		if(response.getStatusCode()==HttpStatus.CONFLICT){
			System.out.println("User trying to create duplicate account");
		}else if(response.getStatusCode()==HttpStatus.INTERNAL_SERVER_ERROR){
			System.out.println("User creation gave internal server error");
		}
		System.out.println(response);
	}


	/**
	 * @AfterReturning Advice after user signin
	 * @param joinPoint
	 * @param response
	 */
	@AfterReturning(pointcut="signInUser()",returning = "response")
	public void signinUserAfterAdvice(JoinPoint joinPoint,ResponseEntity<User> response) {
		if(response.getStatusCode()==HttpStatus.UNAUTHORIZED){
			User user = response.getBody();
			System.out.println("User Signin Failed due to incorrect creds. Email "+user.getEmail());
		}
	}


	/**
	 * @AfterReturning Advice after user verification
	 * @param joinPoint
	 * @param response
	 */
	@AfterReturning(pointcut="authenticateUser()",returning = "response")
	public void authenticateUserAfterAdvice(JoinPoint joinPoint,ResponseEntity<User> response) {
		if(response.getStatusCode()==HttpStatus.FORBIDDEN){
			User user = response.getBody();
			System.out.println("User is trying to verify with incoorect verification code. Email "+user.getEmail());
		}
	}
}