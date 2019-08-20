package service.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CommunicationsDto implements Serializable{
	private AddressDto address;
	private String [] emails;
	private String [] phones;
	public CommunicationsDto() {
	}
	public CommunicationsDto(AddressDto address, String[] emails, String[] phones) {
		super();
		this.address = address;
		this.emails = emails;
		this.phones = phones;
	}
	public AddressDto getAddress() {
		return address;
	}
	public void setAddress(AddressDto address) {
		this.address = address;
	}
	public String[] getEmails() {
		return emails;
	}
	public void setEmails(String[] emails) {
		this.emails = emails;
	}
	public String[] getPhones() {
		return phones;
	}
	public void setPhones(String[] phones) {
		this.phones = phones;
	}
	
	
}
