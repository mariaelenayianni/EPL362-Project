package view;

import java.util.EventObject;

public class FreeRegistrationEvent extends EventObject{

	private String userName;
	private String password;
	private boolean member;
	private String fName;
	private String lName;
	private String email;
	private String role;
	
	public FreeRegistrationEvent(Object source) {
		super(source);
		
	}
	

	public FreeRegistrationEvent(Object source, String userName, String password) {
		super(source);
		this.userName = userName;
		this.password = password;
		fName = null;
		lName = null;
		email = null;
	}


	public FreeRegistrationEvent(Object source, String userName,
			String password, String fName, String lName, String email,String role) {
		super(source);
		this.userName = userName;
		this.password = password;
		this.member = false;//Free account
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.role = role;
	}


	public String getUserName() {
		return userName;
	}


	public String getPassword() {
		return password;
	}


	public boolean isMember() {
		return member;
	}


	public String getfName() {
		return fName;
	}


	public String getlName() {
		return lName;
	}


	public String getEmail() {
		return email;
	}
	
	public String getRole() {
		return role;
	}
	


	@Override
	public String toString() {
		return "FreeRegistrationEvent [userName=" + userName + ", password="
				+ password + ", member=" + member + ", fName=" + fName
				+ ", lName=" + lName + ", email=" + email + ", role="+ role+"]";
	}
	
	
	
	
	

}
