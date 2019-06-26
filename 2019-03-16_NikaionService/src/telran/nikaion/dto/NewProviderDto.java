package telran.nikaion.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class NewProviderDto implements Serializable{
	private String [] professions;
	private ServiceInputDto [] services;
	public NewProviderDto() {
	}
	public NewProviderDto(String[] professions, ServiceInputDto[] services) {
		super();
		this.professions = professions;
		this.services = services;
	}
	public String[] getProfessions() {
		return professions;
	}
	public void setProfessions(String[] professions) {
		this.professions = professions;
	}
	public ServiceInputDto[] getServices() {
		return services;
	}
	public void setServices(ServiceInputDto[] services) {
		this.services = services;
	}
	
	
}
