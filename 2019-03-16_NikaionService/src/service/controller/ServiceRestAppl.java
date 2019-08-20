package service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.dto.ClientProfileDto;
import service.dto.ProviderProfileDto;
import service.dto.ServiceApiConstants;
import service.dto.UserProfileDto;
import service.model.INikaionCompany;

@SpringBootApplication
@ComponentScan({ "service.model", "service.security", "service.controller" })
@EnableMongoRepositories("service.mongodb.repo")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ServiceRestAppl {
	@Bean
	PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	INikaionCompany company;

	@PutMapping(value = ServiceApiConstants.SHOW_ALL_USERS)
	List<UserProfileDto> showAllUsers() {
		return company.showAllUsers();
	}

	@RequestMapping(value = ServiceApiConstants.SHOW_ALL_ADMIND_ID)
	List<String> showAdmonsIds() {
		return company.showAllAdminsIds();
	}

	@RequestMapping(value = ServiceApiConstants.SHOW_ALL_PROVIDERS)
	List<ProviderProfileDto> showAllProviders() {
		return company.showAllProviders();
	}
	
	@DeleteMapping(value =ServiceApiConstants.REMOVE_USER)
	UserProfileDto removeUser(@PathVariable (name="userId") String userId) {
		return company.removeUser(userId);
	}
	@GetMapping(value=ServiceApiConstants.SHOW_ALL_CLIENTS)
	List<ClientProfileDto> showAllClients(){
		return company.showAllClients();
	}

	// @PostMapping(value=AccountsApi.ADD_ACCOUNT)
	// boolean addAccount(@RequestBody Account account) {
	// return accounts.addAccount(account);
	// }
	// @PostMapping(value=AccountsApi.REMOVE_ACCOUNT)
	// boolean removeAccount(@RequestBody String username) {
	// return accounts.removeAccount(username.trim());
	// }
	// @PostMapping(value=AccountsApi.UPDATE_PASSWORD)
	// boolean updatePassword(@RequestBody Map<String,String> namePassword) {
	// String username=namePassword.get(AccountsApi.USERNAME);
	// if(username==null)
	// return false;
	// String newPassword=namePassword.get(AccountsApi.PASSWORD);
	// if(newPassword==null)
	// return false;
	// return accounts.updatePassword(username,newPassword );
	// }

	public static void main(String[] args) {
		SpringApplication.run(ServiceRestAppl.class, args);
	}

}
