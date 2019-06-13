package telran.nikaion.dto;

import java.util.List;

public class ClientProfileDto {
	CommunicationsDto communications;
	private String firstName;
	private String lastName;
	private List<RecordDto> records;
	public ClientProfileDto(CommunicationsDto communications, String firstName, String lastName, List<RecordDto> records) {
		super();
		this.communications = communications;
		this.firstName = firstName;
		this.lastName = lastName;
		this.records = records;
	}
	public ClientProfileDto() {
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
	public List<RecordDto> getRecords() {
		return records;
	}
	public void setRecords(List<RecordDto> records) {
		this.records = records;
	}
	
	
}
