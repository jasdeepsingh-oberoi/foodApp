package edu.sjsu.cmpe275.termprj.aspect.exception;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import edu.sjsu.cmpe275.termprj.model.Order;

@Component
@Aspect
public class OrderExceptionAspect {

	/**
	 Pointcut for adding an order
	 */
	@Pointcut("execution(* edu.sjsu.cmpe275.termprj.controller.OrderController.postOrder(..))")
	public void postOrder() {}
	
	
	/**
	 Pointcut for deleting an order
	 */
	@Pointcut("execution(* edu.sjsu.cmpe275.termprj.controller.OrderController.deleteOrder(..))")
	public void deleteOrder() {}
	
	
	
	/**
	 * @AfterReturning Advice after adding an order
	 * @param joinPoint
	 * @param response
	 */
	@AfterReturning(pointcut="postOrder()",returning = "response")
	public void addOrderAfterAdvice(JoinPoint joinPoint,ResponseEntity<Object> response) {
		
		if(response.getStatusCode()==HttpStatus.NOT_FOUND){
			Order order = (Order)response.getBody();
			System.out.println("Order made by "+order.getEmail()+" is not allowed because the items were deleted by admin");
		}else if(response.getStatusCode()==HttpStatus.FORBIDDEN){
			Order order = (Order)response.getBody();
			System.out.println("Order made by "+order.getEmail()+" is forbidden");
		}
	}
	
	
	/**
	 * @AfterReturning Advice after deleting an order
	 * @param joinPoint
	 * @param response
	 */
	@AfterReturning(pointcut="deleteOrder()",returning = "response")
	public void deleteOrderAfterAdvice(JoinPoint joinPoint,ResponseEntity<String> response) {
		if(response.getStatusCode()!=HttpStatus.OK){
			System.out.println("Delete order request failed");
		}
	}
}