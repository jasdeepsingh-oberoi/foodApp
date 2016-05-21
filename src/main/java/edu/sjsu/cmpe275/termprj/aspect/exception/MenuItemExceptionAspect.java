package edu.sjsu.cmpe275.termprj.aspect.exception;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MenuItemExceptionAspect {

	/**
	 Pointcut for adding a menu item
	 */
	@Pointcut("execution(* edu.sjsu.cmpe275.termprj.controller.MenuItemController.postMenuItem(..))")
	public void postMenuItem() {}
	/**
	 * Pointcut for deleting a menu item
	 */
	@Pointcut("execution(* edu.sjsu.cmpe275.termprj.controller.MenuItemController.deleteMenuItem(..))")
	public void deleteMenuItem() {}
	
	
	/**
	 * @AfterReturning Advice after adding a menu item
	 * @param joinPoint
	 * @param response
	 */
	@AfterReturning(pointcut="postMenuItem()",returning = "response")
	public void addMenuItemAfterAdvice(JoinPoint joinPoint,ResponseEntity<Void> response) {
		if(response.getStatusCode()!=HttpStatus.OK){
			System.out.println("Error while adding new menu item");
		}
	}
	
	/**
	 * @AfterReturning Advice after deleting a menu item
	 * @param joinPoint
	 * @param response
	 */
	@AfterReturning(pointcut="deleteMenuItem()",returning = "response")
	public void deleteMenuItemAfterAdvice(JoinPoint joinPoint,ResponseEntity<Void> response) {
		if(response.getStatusCode()==HttpStatus.NOT_MODIFIED){
			System.out.println("Menu Item not deleted ");
		}else if(response.getStatusCode()!=HttpStatus.OK){
			System.out.println("Error while deleting menu item");
		}
	}
}