package edu.sjsu.cmpe275.termprj.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import java.io.FileNotFoundException;
import java.util.Random;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.sjsu.cmpe275.termprj.dao.UserDao;
import edu.sjsu.cmpe275.termprj.model.User;
import edu.sjsu.cmpe275.termprj.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	private static SecretKeySpec keySpec;
	private int isVerified;


	public boolean isUserExist(String email) {
		
		return false;
	}

	public User saveUser(User user) {
		
		Random randomKey = new Random( System.currentTimeMillis() );
		String verificationCode = Integer.toString((1 + randomKey.nextInt(2)) * 10000 + randomKey.nextInt(10000));
		user.setVerification_code(verificationCode);

		try{
			ClassPathResource cpResource = new ClassPathResource("key.key");
			if (cpResource!=null){
				File file = cpResource.getFile();
				FileInputStream fiStream = new FileInputStream(file);
				byte[] bs = new byte[(int)file.length()];
				fiStream.read(bs);
				keySpec = new SecretKeySpec(bs, "AES");
				fiStream.close();
				String encryptedPassword = new String (encryptPassword(user.getPassword()));
				user.setPassword(encryptedPassword);
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException i) {
			i.printStackTrace();
		}catch (NoSuchPaddingException n) {
			n.printStackTrace();
		} catch (GeneralSecurityException g) {
			g.printStackTrace();
		}
		return userDao.insertUser(user);
	}

	public boolean checkUser(User user) {
		byte[] dbPassword = userDao.authenticateUser(user).getBytes();
		try {
			ClassPathResource cpResource = new ClassPathResource("key.key");
			if (cpResource!=null){
				File file = cpResource.getFile();
				FileInputStream fiStream = new FileInputStream(file);
				byte[] bs = new byte[(int)file.length()];
				fiStream.read(bs);
				keySpec = new SecretKeySpec(bs, "AES");
				fiStream.close();
				String decryptedPassword = decryptPassword(dbPassword);
				if(decryptedPassword.equals(user.getPassword())){
					User dbUserData = userDao.getVerifiedUser(user.getEmail());
					System.out.println("is verified? " +dbUserData.getIsVerified());
					System.out.println("is admin? " +dbUserData.getIsAdmin());
					if(dbUserData.getIsVerified().equals("1")){
						user.setIsAdmin(dbUserData.getIsAdmin());
						System.out.println("in userservice");
						System.out.println(user.getIsAdmin());
						return true;
					}else{
						//user.setVerification_code(dbUserData.getVerification_code());
						user.setIsVerified(dbUserData.getIsVerified());
						return false;
					}
				}else{
					return false;
				}
			}
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean setUserVerified(String email, String verification_code) {
		User user = userDao.setUserVerified(email, verification_code);
		if(user!= null)
			return true;
		else 
			return false;

	}

	private static byte[] encryptPassword(String password) throws GeneralSecurityException, NoSuchPaddingException{
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, keySpec);
		return cipher.doFinal(password.getBytes());
	}

	private static String decryptPassword(byte[] dbPassword) throws GeneralSecurityException, NoSuchPaddingException{
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, keySpec);
		return new String(cipher.doFinal(dbPassword));
	}
}
