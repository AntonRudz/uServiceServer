package service.dto;

import java.io.Serializable;
@SuppressWarnings("serial")
public class Communications implements Serializable{
	private String [] additionalEmails;
	private Address address;
	private Phone [] phones;
	private String skype;
	
	
	public Communications() {
	}


	public Communications(String[] additionalEmails, Address address, Phone[] phones, String skype) {
		super();
		this.additionalEmails = additionalEmails;
		this.address = address;
		this.phones = phones;
		this.skype = skype;
	}


	public String[] getAdditionalEmails() {
		return additionalEmails;
	}


	public void setAdditionalEmails(String[] additionalEmails) {
		this.additionalEmails = additionalEmails;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public Phone[] getPhones() {
		return phones;
	}


	public void setPhones(Phone[] phones) {
		this.phones = phones;
	}


	public String getSkype() {
		return skype;
	}


	public void setSkype(String skype) {
		this.skype = skype;
	}
	
}
