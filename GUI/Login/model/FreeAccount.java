package model;

public class FreeAccount extends Account{

	private String fName;
	private String lName;
	private String email;
	private String role;
	
	public FreeAccount(String username, String password, String fName,
			String lName, String email, String role) {
		super(username, password,false);
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.role = role;
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
	
	public String getUsername(){
		return super.getUsername();
	}
	public String getPassword(){
		return super.getPassword();
	}
	
	public void setUsername(String username){
		super.setUsername(username);
	}
	public void setPassword(String password){
		super.setPassword(password);
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
	

}
