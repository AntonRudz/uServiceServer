package telran.nikaion.dto;

import java.util.List;

public class ProviderProfileDto {
	private CommunicationsDto communications;
	private String firstName;
	private String lastName;
	private String[] professions;
	private int rating;
	List<ServiceDto> services;

	public ProviderProfileDto(CommunicationsDto communications, String firstName, String lastName, String[] professions,
			int rating, List<ServiceDto> services) {
		super();
		this.communications = communications;
		this.firstName = firstName;
		this.lastName = lastName;
		this.professions = professions;
		this.rating = rating;
		this.services = services;
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

	public String[] getProfessions() {
		return professions;
	}

	public void setProfessions(String[] professions) {
		this.professions = professions;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public List<ServiceDto> getServices() {
		return services;
	}

	public void setServices(List<ServiceDto>  services) {
		this.services = services;
	}

	public ProviderProfileDto() {

	}

}
