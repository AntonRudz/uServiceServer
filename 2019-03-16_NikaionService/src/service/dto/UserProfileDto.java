package service.dto;

import java.io.Serializable;
@SuppressWarnings("serial")
public class UserProfileDto implements Serializable{
	private CommunicationsDto communications;
	private String firstName;
	private String lastName;
	private String [] roles;
	public UserProfileDto(CommunicationsDto communications, String firstName, String lastName, String[] roles) {
		super();
		this.communications = communications;
		this.firstName = firstName;
		this.lastName = lastName;
		this.roles = roles;
	}
	public CommunicationsDto getCommunications() {
		return communications;
	}
	public void setCommunications(CommunicationsDto communications) {
		this.communications = communications;
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
	public String[] getRoles() {
		return roles;
	}
	public void setRoles(String[] roles) {
		this.roles = roles;
	}
	public UserProfileDto() {
	}
	
	
}
