package service.entities.mongodb;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.catalina.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import service.dto.AddressDto;
import service.dto.ClientProfileDto;
import service.dto.Communications;
import service.dto.CommunicationsDto;
import service.dto.RecordDto;
import service.dto.Roles;
import service.dto.UserDto;
import service.dto.UserProfileDto;

@Document(collection="users")
public class UserCrud {
	Communications communications;
	String dateBirth;
	@Id
	String email;
	String firstName;
	String lastName;
	String password;
	String [] roles;
	HashMap <String , RecordCrud> records;
	
	public HashMap <String,RecordCrud> getRecords() {
		return records;
	}

	public void setRecords(HashMap <String,RecordCrud> records) {
		this.records = records;
	}

	public UserCrud(UserDto user) {
		if(user.getCommunications()==null) {
			communications=new Communications();
		}else {communications=user.getCommunications();
		}
		
		email=user.getEmail();
		dateBirth=user.getDateBirth();
		firstName=user.getFirstName();
		lastName=user.getLastName();
		password=user.getPassword();
		roles=new String[1];
		roles[0]=Roles.ROLE_CLIENT.toString();
		records=new HashMap<String,RecordCrud>();
	}

	public UserCrud() {
	}
	public UserProfileDto getUser() {
		CommunicationsDto resCommunications= new CommunicationsDto();
		if(communications.getAddress()==null) {
			resCommunications.setAddress(new AddressDto());
			String[] phones = new String[1];
			phones[0]=email;
			resCommunications.setEmails(phones);
			resCommunications.setEmails(new String[0]);
		}
		else {
			AddressDto resAddress=new AddressDto(communications.getAddress().getCity(), communications.getAddress().getDistrict());
			String[] phones= new String[communications.getPhones().length]; 
			for(int i=0;i<communications.getPhones().length;i++) {
				phones[i]=communications.getPhones()[i].getNumber();
			}
			resCommunications.setAddress(resAddress);
			resCommunications.setEmails(communications.getAdditionalEmails());
			resCommunications.setPhones(phones);
		}
		
		
		UserProfileDto res=new UserProfileDto(resCommunications, firstName, lastName, roles);
		return res;
	}
	public ClientProfileDto getClient() {
		UserProfileDto user=getUser();
		List<RecordDto> recordsDto=records.values().stream().map(r->r.getRecord()).collect(Collectors.toList());
		return new ClientProfileDto(user.getCommunications(), user.getFirstName(), user.getLastName(), recordsDto);
	}

	public Communications getCommunications() {
		return communications;
	}

	public void setCommunications(Communications communications) {
		this.communications = communications;
	}

	public String getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(String dateBirth) {
		this.dateBirth = dateBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String [] roles) {
		this.roles = roles;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public void addRecord(RecordCrud record) {
		records.put(record.id, record);
	}
	
	public void removeRecord(RecordCrud record) {
		records.remove(record.id);
	}
	public void updateRecord(RecordCrud record) {
		records.remove(record.id);
		records.put(record.id, record);
	}
	
	
	
}
