package telran.nikaion.dto;

public class NewProviderDto {
	private String [] professions;
	ServiceInputDto [] services;
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
