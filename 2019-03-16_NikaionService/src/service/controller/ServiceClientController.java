package service.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.dto.ClientProfileDto;
import service.dto.NewProviderDto;
import service.dto.ProviderProfileDto;
import service.dto.RecordCreateDto;
import service.dto.RecordDto;
import service.dto.RecordUpdateDto;
import service.dto.ServiceApiConstants;
import service.dto.UserProfileDto;
import service.dto.UserUpdateDto;
import service.model.INikaionCompany;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ServiceClientController {
	@Autowired
	INikaionCompany company;

	@RequestMapping(value = ServiceApiConstants.SHOW_CLIENT)
	ClientProfileDto showUser(Principal principal) {
		System.out.println(principal.getName());
		return company.getClient(principal.getName());

	}

	@PostMapping(value = ServiceApiConstants.CREATE_PROVIDER)
	ProviderProfileDto createProvider(@RequestBody NewProviderDto provider, Principal principal) {
		return company.addProvider(provider, principal.getName());
	}

	@PutMapping(value = ServiceApiConstants.UPDATE_CLIENT)
	UserProfileDto updateClient(@RequestBody UserUpdateDto updateData, Principal principal) {
		return company.updateClient(updateData, principal.getName());
	}

	@PostMapping(value = ServiceApiConstants.CREATE_RECORD)
	RecordDto createRecord(@RequestBody RecordCreateDto recordCreateDto, Principal principal) {
		return company.createRecord(recordCreateDto, principal.getName());
	}

	@RequestMapping(value = ServiceApiConstants.SHOW_RECORD)
	RecordDto showRecord(@PathVariable(name = "recordId") String recordId, Principal principal) {
		System.out.println(recordId);
		return company.showRecord(recordId, principal.getName());
	}

	@DeleteMapping(value=ServiceApiConstants.REMOVE_RECORD)
	RecordDto removeRecord(@PathVariable(name = "recordId") String recordId, Principal principal) {
		return company.removeRecord(recordId, principal.getName());
	}
	
	@RequestMapping(value=ServiceApiConstants.SHOW_PROVIDER_FREE_TIME_BY_ID)
	List<String> showProviderFreeTime(@PathVariable(name="providerId") String providerId,@PathVariable(name="date") String date){
		return company.showProviderFreeTimeById(providerId, LocalDate.parse(date));
	}
	
	@PutMapping(value=ServiceApiConstants.UPDATE_RECORD)
	RecordDto updateRecord(@PathVariable(name="recordId") String recordId, Principal principal, @RequestBody RecordUpdateDto updateData) {
		return company.updateRecord(recordId, principal.getName(), updateData);
	}
}