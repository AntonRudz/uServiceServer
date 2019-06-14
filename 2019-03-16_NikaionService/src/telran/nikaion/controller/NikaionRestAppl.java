package telran.nikaion.controller;

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

import telran.nikaion.dto.ClientProfileDto;
import telran.nikaion.dto.NikaionApiConstants;
import telran.nikaion.dto.ProviderProfileDto;
import telran.nikaion.dto.UserProfileDto;
import telran.nikaion.model.INikaionCompany;

@SpringBootApplication
@ComponentScan({ "telran.nikaion.model", "telran.security", "telran.nikaion.controller" })
@EnableMongoRepositories("telran.nikaion.mongodb.repo")
@RestController
public class NikaionRestAppl {
	@Bean
	PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	INikaionCompany company;

	@PutMapping(value = NikaionApiConstants.SHOW_ALL_USERS)
	List<UserProfileDto> showAllUsers() {
		return company.showAllUsers();
	}

	@RequestMapping(value = NikaionApiConstants.SHOW_ALL_ADMIND_ID)
	List<String> showAdmonsIds() {
		return company.showAllAdminsIds();
	}

	@RequestMapping(value = NikaionApiConstants.SHOW_ALL_PROVIDERS)
	List<ProviderProfileDto> showAllProviders() {
		return company.showAllProviders();
	}
	
	@DeleteMapping(value =NikaionApiConstants.REMOVE_USER)
	UserProfileDto removeUser(@PathVariable (name="userId") String userId) {
		return company.removeUser(userId);
	}
	@GetMapping(value=NikaionApiConstants.SHOW_ALL_CLIENTS)
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
		SpringApplication.run(NikaionRestAppl.class, args);

	}

}
