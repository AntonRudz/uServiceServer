package service.controller;

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

import service.dto.PatternOutputDto;
import service.dto.ProviderProfileDto;
import service.dto.ProviderUpdateDto;
import service.dto.RecordDto;
import service.dto.ScheduleDto;
import service.dto.SchedulePattern;
import service.dto.ServiceApiConstants;
import service.dto.ServiceDto;
import service.dto.ServiceInputDto;
import service.model.INikaionCompany;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ServiceProviderController {
	@Autowired
	INikaionCompany company;

	@RequestMapping(value = ServiceApiConstants.SHOW_PROVIDER)
	ProviderProfileDto showProvider(Principal principal) {
		//System.out.println(principal.getName());
		return company.getProvider(principal.getName());
	}

	@PutMapping(value = ServiceApiConstants.UPDATE_PROVIDER)
	ProviderProfileDto updateProvider(@RequestBody ProviderUpdateDto updateData, Principal principal) {
		return company.updateProvider(updateData, principal.getName());
	}

	@PostMapping(value = ServiceApiConstants.CREATE_SHEDULE_PATTERN)
	PatternOutputDto createSchedulePattern(@RequestBody SchedulePattern schedule, Principal principal) {
		System.out.println("hi");
		return company.createSchedulePattern(schedule, principal.getName());
	}

	@DeleteMapping(value = ServiceApiConstants.REMOVE_SERVICE_BY_ID)
	ServiceDto removeService(@PathVariable(name = "serviceId") String serviceId, Principal principal) {
		return company.removeServiceById(serviceId, principal.getName());
	}

	@RequestMapping(value = ServiceApiConstants.SHOW_SCHEDULE_PATTERN)
	PatternOutputDto showSchedulePattern(Principal principal) {
		return company.showSchedulePattern(principal.getName());
	}

	@PutMapping(value = ServiceApiConstants.ADD_SERVICE)
	List<ServiceDto> addService(@RequestBody ServiceInputDto newService, Principal principal) {
		return company.addService(newService, principal.getName());
	}

	@RequestMapping(value = ServiceApiConstants.SHOW_RECORD_P)
	RecordDto showRecord(@PathVariable(name = "recordId") String recordId) {
		return company.showRecord(recordId);
	}

	@RequestMapping(value = ServiceApiConstants.SHOW_SCHEDULE)
	ScheduleDto showSchedule(Principal principal) {
		return company.showShedule(principal.getName());
	}

	@RequestMapping(value = ServiceApiConstants.SHOW_RECORD_ON_DATE)
	ScheduleDto schowRecordOnDate(Principal principal, @PathVariable(name = "date") String date) {
		return company.showRecordOnDate(date, principal.getName());
	}
	@RequestMapping()
	List<ServiceDto> showAllServices(Principal principal){
		return company.showAllServices(principal.getName());
	}

}
