package telran.nikaion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import telran.nikaion.dto.NikaionApiConstants;
import telran.nikaion.dto.ProviderProfileDto;
import telran.nikaion.dto.ServiceDto;
import telran.nikaion.dto.UserDto;
import telran.nikaion.dto.UserProfileDto;
import telran.nikaion.model.INikaionCompany;
	@RestController
	@CrossOrigin(origins = "http://localhost:4200")
public class NikaionGuestController {
		@Bean
		PasswordEncoder getPasswordEncoder() {
			return new BCryptPasswordEncoder();
		}
		@Autowired
		INikaionCompany company;
		
		@PostMapping(value=NikaionApiConstants.CREATE_CLIENT)
		UserProfileDto addNewUser(@RequestBody UserDto userDto) {
			return company.addUser(userDto);
			
		}
		
		@RequestMapping(value=NikaionApiConstants.SHOW_PROVIDERS)
		List<ProviderProfileDto> showAllProviders(){
			return company.getAllProviders();
		}
		
		@RequestMapping(value=NikaionApiConstants.SHOW_PROVIDER_BY_ID)
		ProviderProfileDto showProviderById(@PathVariable(name="providerId") String providerId) {
			System.out.println("providerId"+providerId);	
			return company.getProviderById(providerId);
		}
		@RequestMapping(value=NikaionApiConstants.SHOW_ALL_SERVICES)
		List <ServiceDto> showAllServices(){
			return company.showAllServices();
		}
		@RequestMapping(value=NikaionApiConstants.SHOW_SERVICE_BY_ID)
		ServiceDto showServiceById(@PathVariable(name="serviceId") String serviceId) {
			return company.showServiceById(serviceId);
		}
}
