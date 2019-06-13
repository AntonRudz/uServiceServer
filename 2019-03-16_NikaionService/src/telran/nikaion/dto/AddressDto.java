package telran.nikaion.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AddressDto implements Serializable {
	private String city;
	private String district;

	public AddressDto(String city, String district) {
		super();
		this.city = city;
		this.district = district;
	}

	public AddressDto() {
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

}
