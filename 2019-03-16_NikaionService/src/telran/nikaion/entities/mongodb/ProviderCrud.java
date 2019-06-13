package telran.nikaion.entities.mongodb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.annotation.Id;
import telran.nikaion.dto.AddressDto;
import telran.nikaion.dto.Communications;
import telran.nikaion.dto.CommunicationsDto;
import telran.nikaion.dto.NewProviderDto;
import telran.nikaion.dto.ProviderProfileDto;
import telran.nikaion.dto.ServiceDto;

public class ProviderCrud {
	@Id
	String id;
	String[] professions;
	List<ServiceCrud> services;
	int rating;
	Communications communications;
	String firstName;
	String lastName;
	boolean isAvailable;

	public ProviderCrud(NewProviderDto newProvider, UserCrud user) {
		id = user.getEmail();
		professions = newProvider.getProfessions();
		services = new ArrayList<>();
		Arrays.asList(newProvider.getServices()).forEach(s -> services.add(new ServiceCrud(s, id)));
		rating = 0;
		communications = user.communications;
		firstName = user.firstName;
		lastName = user.lastName;
		isAvailable=false;

	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public ProviderCrud() {
	}

	public ProviderProfileDto getProvider() {
		List<ServiceDto> servicesDto = services.stream().map(s -> s.getService()).collect(Collectors.toList());

		AddressDto resAddress = new AddressDto(communications.getAddress().getCity(),
				communications.getAddress().getDistrict());
		String[] phones = new String[communications.getPhones().length];
		for (int i = 0; i < communications.getPhones().length; i++) {
			phones[i] = communications.getPhones()[i].getNumber();
		}
		CommunicationsDto resCommunications = new CommunicationsDto(resAddress, communications.getAdditionalEmails(),
				phones);
		ProviderProfileDto res = new ProviderProfileDto(resCommunications, firstName, lastName, professions, rating,
				servicesDto);
		return res;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getProfessions() {
		return professions;
	}

	public void setProfessions(String[] professions) {
		this.professions = professions;
	}

	public List<ServiceCrud> getServices() {
		return services;
	}

	public void setServices(List<ServiceCrud> services) {
		this.services = services;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Communications getCommunications() {
		return communications;
	}

	public void setCommunications(Communications communications) {
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

}
