package telran.nikaion.dto;

public class UserUpdateDto {
	Communications communications;
	private String dateBirth;
	private String firstName;
	private String lastName;
	public UserUpdateDto(Communications communications, String dateBirth, String firstName, String lastName) {
		super();
		this.communications = communications;
		this.dateBirth = dateBirth;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public UserUpdateDto() {

	}
	public Communications getCommunications() {
		return communications;
	}
	public String getDateBirth() {
		return dateBirth;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	
	
	
}
