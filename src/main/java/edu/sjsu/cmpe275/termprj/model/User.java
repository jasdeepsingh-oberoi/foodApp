package edu.sjsu.cmpe275.termprj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

	@Entity
	@Table(name="user_details")
	public class User {
		@Id
		@Column(name="id")
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private long id;
		@Column
		private String email;
		@Column
		private String password;
		@Column(name="is_admin")
		private String isAdmin;
		public String getIsAdmin() {
			return isAdmin;
		}

		public void setIsAdmin(String isAdmin) {
			this.isAdmin = isAdmin;
		}

		public String getIsVerified() {
			return isVerified;
		}

		public void setIsVerified(String isVerified) {
			this.isVerified = isVerified;
		}

		@Column
		private String verification_code;
		@Column(name="is_verified")
		private String isVerified;
		
		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}
		
		public String getEmail() {
			return email;
		}
		
		public void setEmail(String email) {
			this.email = email;
		}
		
		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
		
		/*public String isAdmin() {
			return isAdmin;
		}
		
		public void setAdmin(String isAdmin) {
			this.isAdmin = isAdmin;
		}*/
		
		public String getVerification_code() {
			return verification_code;
		}
		
		public void setVerification_code(String verification_code) {
			this.verification_code = verification_code;
		}
		
		/*public String isVerified() {
			return isVerified;
		}
		
		public void setVerified(String isVerified) {
			this.isVerified = isVerified;
		}*/
		
		public User(){
			
		}
		
		public User(String email, String password, String isAdmin, String verification_code, String isVerified) {
			this.email = email;
			this.password = password;
			this.isAdmin = isAdmin;
			this.verification_code = verification_code;
			this.isVerified = isVerified;
		}
}
