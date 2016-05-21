package edu.sjsu.cmpe275.termprj.dao;

import edu.sjsu.cmpe275.termprj.model.User;

public interface UserDao {
	public User insertUser(User user);
	public int getUser(String email);
	public User getVerifiedUser(String email);
	public User setUserVerified(String email, String verification_code);
	public String authenticateUser(User user);
}
