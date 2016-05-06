package edu.sjsu.cmpe275.termprj.service;

import edu.sjsu.cmpe275.termprj.model.User;

public interface UserService {

	public User saveUser(User user);
    
    public boolean isUserExist(String email);
    
    public boolean checkUser(User user);

	public boolean setUserVerified(String email, String verification_code); 

}
