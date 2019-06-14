package telran.nikaion.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.nikaion.dto.NewProviderDto;
import telran.nikaion.dto.NikaionApiConstants;
import telran.nikaion.dto.ProviderProfileDto;
import telran.nikaion.dto.RecordCreateDto;
import telran.nikaion.dto.RecordDto;
import telran.nikaion.dto.RecordUpdateDto;
import telran.nikaion.dto.UserProfileDto;
import telran.nikaion.dto.UserUpdateDto;
import telran.nikaion.model.INikaionCompany;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class NikaionClientController {
	@Autowired
	INikaionCompany company;

	@RequestMapping(value = NikaionApiConstants.SHOW_CLIENT)
	UserProfileDto showUser(Principal principal) {
		return company.getUser(principal.getName());

	}

	@PostMapping(value = NikaionApiConstants.CREATE_PROVIDER)
	ProviderProfileDto createProvider(@RequestBody NewProviderDto provider, Principal principal) {
		return company.addProvider(provider, principal.getName());
	}

	@PutMapping(value = NikaionApiConstants.UPDATE_CLIENT)
	UserProfileDto updateClient(@RequestBody UserUpdateDto updateData, Principal principal) {
		return company.updateClient(updateData, principal.getName());
	}

	@PostMapping(value = NikaionApiConstants.CREATE_RECORD)
	RecordDto createRecord(@RequestBody RecordCreateDto recordCreateDto, Principal principal) {
		return company.createRecord(recordCreateDto, principal.getName());
	}

	@RequestMapping(value = NikaionApiConstants.SHOW_RECORD)
	RecordDto showRecord(@PathVariable(name = "recordId") String recordId, Principal principal) {
		return company.showRecord(recordId, principal.getName());
	}

	@DeleteMapping(value=NikaionApiConstants.REMOVE_RECORD)
	RecordDto removeRecord(@PathVariable(name = "recordId") String recordId, Principal principal) {
		return company.removeRecord(recordId, principal.getName());
	}
	
	@RequestMapping(value=NikaionApiConstants.SHOW_PROVIDER_FREE_TIME_BY_ID)
	List<String> showProviderFreeTime(@PathVariable(name="providerId") String providerId,@PathVariable(name="date") String date){
		return company.showProviderFreeTimeById(providerId, LocalDate.parse(date));
	}
	
	@PutMapping(value=NikaionApiConstants.UPDATE_RECORD)
	RecordDto updateRecord(@PathVariable(name="recordId") String recordId, Principal principal, @RequestBody RecordUpdateDto updateData) {
		return company.updateRecord(recordId, principal.getName(), updateData);
	}
}