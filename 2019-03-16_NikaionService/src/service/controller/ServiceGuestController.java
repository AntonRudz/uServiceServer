package service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.dto.ClientProfileDto;
import service.dto.ProviderProfileDto;
import service.dto.ServiceApiConstants;
import service.dto.ServiceDto;
import service.dto.UserDto;
import service.dto.UserProfileDto;
import service.model.INikaionCompany;
	@RestController
	@CrossOrigin(origins = "http://localhost:4200")
public class ServiceGuestController {
		@Bean
		PasswordEncoder getPasswordEncoder() {
			return new BCryptPasswordEncoder();
		}
		@Autowired
		INikaionCompany company;
		
		@PostMapping(value=ServiceApiConstants.CREATE_CLIENT)
		ClientProfileDto addNewUser(@RequestBody UserDto userDto) {
			return company.addUser(userDto);
			
		}
		
		@RequestMapping(value=ServiceApiConstants.SHOW_PROVIDERS)
		List<ProviderProfileDto> showAllProviders(){
			return company.getAllProviders();
		}
		
		@RequestMapping(value=ServiceApiConstants.SHOW_PROVIDER_BY_ID)
		ProviderProfileDto showProviderById(@PathVariable(name="providerId") String providerId) {
			System.out.println("providerId"+providerId);	
			return company.getProvider(providerId);
		}
		
		@RequestMapping(value=ServiceApiConstants.SHOW_ALL_SERVICES)
		List <ServiceDto> showAllServices(){
			return company.showAllServices();
		}
		@RequestMapping(value=ServiceApiConstants.SHOW_SERVICE_BY_ID)
		ServiceDto showServiceById(@PathVariable(name="serviceId") String serviceId) {
			return company.showServiceById(serviceId);
		}
}
