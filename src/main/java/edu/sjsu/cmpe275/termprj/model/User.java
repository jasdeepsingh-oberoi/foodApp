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
		private int isAdmin;
		@Column
		private String verification_code;
		@Column(name="is_verified")
		private int isVerified;
		
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
		
		public int isAdmin() {
			return isAdmin;
		}
		
		public void setAdmin(int isAdmin) {
			this.isAdmin = isAdmin;
		}
		
		public String getVerification_code() {
			return verification_code;
		}
		
		public void setVerification_code(String verification_code) {
			this.verification_code = verification_code;
		}
		
		public int isVerified() {
			return isVerified;
		}
		
		public void setVerified(int isVerified) {
			this.isVerified = isVerified;
		}
		
		public User(){
			
		}
		
		public User(String email, String password, int isAdmin, String verification_code, int isVerified) {
			this.email = email;
			this.password = password;
			this.isAdmin = isAdmin;
			this.verification_code = verification_code;
			this.isVerified = isVerified;
		}
}
