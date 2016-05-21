package edu.sjsu.cmpe275.termprj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.termprj.model.User;
import edu.sjsu.cmpe275.termprj.service.EmailSendingService;
import edu.sjsu.cmpe275.termprj.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	EmailSendingService emailService;
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public ResponseEntity<User> createUser(@RequestBody User user){
		
		user.setIsAdmin("0");
		user.setIsVerified("0");
		
		
		/*if (userService.isUserExist(email)) {
            System.out.println("This id: " + email + " already exists");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT); //http status 409
        }
  */
		user = userService.saveUser(user);
		user.setPassword(null);
        if (user.getId()>0){
        	String subject = "Verification code from Takeout Services";
        	String message = "Enter the following verification code to sign in to the system: " + user.getVerification_code();
        	emailService.sendEmail(user.getEmail(), "takeoutservice18@gmail.com", subject, message);
        	return new ResponseEntity<User>(user, HttpStatus.CREATED); //http status 201
        }else if(user.getId()==0){
        	System.out.println("This id: " + user.getEmail() + " already exists");
            return new ResponseEntity<User>(HttpStatus.CONFLICT); //http status 409
        }
        else{
        	return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR); //http status 500
        }
	}
	
	@RequestMapping(value="/signin", method=RequestMethod.POST)
	public ResponseEntity<User> signInUser(@RequestBody User user){
		
		if(userService.checkUser(user)){
			user.setPassword(null);
			//user.setAdmin(user.isAdmin());
			System.out.println("in user controller");
			System.out.println(user.getIsAdmin());
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}else{
			user.setPassword(null);
			return new ResponseEntity<User>(user, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@RequestMapping(value="/authenticate", method=RequestMethod.POST)
	public ResponseEntity<User> authenticateUser(@RequestBody User user){
		if(userService.setUserVerified(user.getEmail(),user.getVerification_code()))
			return new ResponseEntity<User>(user, HttpStatus.OK);
		else
			return new ResponseEntity<User>(user, HttpStatus.FORBIDDEN);
		
	}
}
