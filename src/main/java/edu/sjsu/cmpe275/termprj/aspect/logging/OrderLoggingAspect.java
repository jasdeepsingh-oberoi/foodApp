package edu.sjsu.cmpe275.termprj.aspect.logging;

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
public class OrderLoggingAspect {

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
		
		if(response.getStatusCode()==HttpStatus.OK){
			Order order = (Order)response.getBody();
			System.out.println("Order has been placed by with id "+order.getOrder_id()+" by "+order.getEmail()+" for "+order.getOrder_date()+" "+order.getPickup_time()+" and will be fulfilled by chef with id "+order.getChef_id());
		}
	}
	
	
	/**
	 * @AfterReturning Advice after deleting an order
	 * @param joinPoint
	 * @param response
	 */
	@AfterReturning(pointcut="deleteOrder()",returning = "response")
	public void deleteOrderAfterAdvice(JoinPoint joinPoint,ResponseEntity<String> response) {
		if(response.getStatusCode()==HttpStatus.OK){
			System.out.println("Delete order request fulfilled successfully");
		}
	}
	
}