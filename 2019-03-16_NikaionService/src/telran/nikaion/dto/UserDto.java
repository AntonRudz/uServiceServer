package telran.nikaion.dto;

import java.io.Serializable;
@SuppressWarnings("serial")
public class UserDto implements Serializable{
	private Communications communications;
	private String dateBirth;
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	public UserDto() {
	}
	public UserDto(Communications communications, String dateBirth, String email, String firstName, String lastName,
			String password) {
		super();
		this.communications = communications;
		this.dateBirth = dateBirth;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		
	}
	public Communications getCommunications() {
		return communications;
	}
	public void setCommunications(Communications communications) {
		this.communications = communications;
	}
	public String getDateBirth() {
		return dateBirth;
	}
	public void setDateBirth(String dateBirth) {
		this.dateBirth = dateBirth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	
}
