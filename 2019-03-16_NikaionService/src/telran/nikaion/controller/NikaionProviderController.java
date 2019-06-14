package telran.nikaion.controller;

import java.security.Principal;
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

import telran.nikaion.dto.NikaionApiConstants;
import telran.nikaion.dto.PatternOutputDto;
import telran.nikaion.dto.ProviderProfileDto;
import telran.nikaion.dto.ProviderUpdateDto;
import telran.nikaion.dto.RecordDto;
import telran.nikaion.dto.ScheduleDto;
import telran.nikaion.dto.SchedulePattern;
import telran.nikaion.dto.ServiceDto;
import telran.nikaion.dto.ServiceInputDto;
import telran.nikaion.model.INikaionCompany;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class NikaionProviderController {
	@Autowired
	INikaionCompany company;

	@RequestMapping(value = NikaionApiConstants.SHOW_PROVIDER)
	ProviderProfileDto showProvider(Principal principal) {
		return company.getProvider(principal.getName());
	}

	@PutMapping(value = NikaionApiConstants.UPDATE_PROVIDER)
	ProviderProfileDto updateProvider(@RequestBody ProviderUpdateDto updateData, Principal principal) {
		return company.updateProvider(updateData, principal.getName());
	}

	@PostMapping(value = NikaionApiConstants.CREATE_SHEDULE_PATTERN)
	PatternOutputDto createSchedulePattern(@RequestBody SchedulePattern schedule, Principal principal) {
		System.out.println("hi");
		return company.createSchedulePattern(schedule, principal.getName());
	}

	@DeleteMapping(value = NikaionApiConstants.REMOVE_SERVICE_BY_ID)
	ServiceDto removeService(@PathVariable(name = "serviceId") String serviceId, Principal principal) {
		return company.removeServiceById(serviceId, principal.getName());
	}

	@RequestMapping(value = NikaionApiConstants.SHOW_SCHEDULE_PATTERN)
	PatternOutputDto showSchedulePattern(Principal principal) {
		return company.showSchedulePattern(principal.getName());
	}

	@PutMapping(value = NikaionApiConstants.ADD_SERVICE)
	List<ServiceDto> addService(@RequestBody ServiceInputDto newService, Principal principal) {
		return company.addService(newService, principal.getName());
	}

	@RequestMapping(value = NikaionApiConstants.SHOW_RECORD_P)
	RecordDto showRecord(@PathVariable(name = "recordId") String recordId) {
		return company.showRecord(recordId);
	}

	@RequestMapping(value = NikaionApiConstants.SHOW_SCHEDULE)
	ScheduleDto showSchedule(Principal principal) {
		return company.showShedule(principal.getName());
	}

	@RequestMapping(value = NikaionApiConstants.SHOW_RECORD_ON_DATE)
	ScheduleDto schowRecordOnDate(Principal principal, @PathVariable(name = "date") String date) {
		return company.showRecordOnDate(date, principal.getName());
	}
	@RequestMapping()
	List<ServiceDto> showAllServices(Principal principal){
		return company.showAllServices(principal.getName());
	}

}
