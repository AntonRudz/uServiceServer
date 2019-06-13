package telran.nikaion.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Address implements Serializable{

	private String apartment;
	private String building;
	private String city;
	private String description;
	private String district;
	private int postcode;
	private String street;
	public Address(String apartment, String building, String city, String description, String district, int postcode,
			String street) {
		super();
		this.apartment = apartment;
		this.building = building;
		this.city = city;
		this.description = description;
		this.district = district;
		this.postcode = postcode;
		this.street = street;
	}
	public Address() {
	}
	public String getApartment() {
		return apartment;
	}
	public void setApartment(String apartment) {
		this.apartment = apartment;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public int getPostcode() {
		return postcode;
	}
	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
	
}
