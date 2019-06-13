package telran.nikaion.dto;

public class ProviderUpdateDto {
	String [] professions;

	public ProviderUpdateDto() {
	}

	public ProviderUpdateDto(String[] professions) {
		super();
		this.professions = professions;
	}

	public String[] getProfessions() {
		return professions;
	}

	public void setProfessions(String[] professions) {
		this.professions = professions;
	}
	
	
}
