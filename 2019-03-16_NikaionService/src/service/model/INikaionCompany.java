package service.model;

import java.time.LocalDate;
import java.util.List;

import service.dto.ClientProfileDto;
import service.dto.NewProviderDto;
import service.dto.PatternOutputDto;
import service.dto.ProviderProfileDto;
import service.dto.ProviderUpdateDto;
import service.dto.RecordCreateDto;
import service.dto.RecordDto;
import service.dto.RecordUpdateDto;
import service.dto.ScheduleDto;
import service.dto.SchedulePattern;
import service.dto.ServiceDto;
import service.dto.ServiceInputDto;
import service.dto.UserDto;
import service.dto.UserProfileDto;
import service.dto.UserUpdateDto;

public interface INikaionCompany {
	ClientProfileDto addUser(UserDto user); /* GUEST */

	List<ProviderProfileDto> getAllProviders(); /* GUEST */

	ProviderProfileDto getProviderById(String id); /* GUEST */

	List<ServiceDto> showAllServices(); /* GUEST */

	ServiceDto showServiceById(String serviceId); /* GUEST */

	ProviderProfileDto addProvider(NewProviderDto provider, String idUser); /* CLIENT */

	ClientProfileDto getClient(String id); /* CLIENT */

	ProviderProfileDto getProvider(String id); /* CLIENT */

	UserProfileDto updateClient(UserUpdateDto userUpdate, String id); /* CLIENT */

	ProviderProfileDto updateProvider(ProviderUpdateDto updateData, String providerId); /* PROVIDER */

	PatternOutputDto createSchedulePattern(SchedulePattern schedule, String providerId); /* PROVIDER */

	ServiceDto removeServiceById(String serviceId, String providerId); /* PROVIDER */

	PatternOutputDto showSchedulePattern(String providerId); /* PROVIDER */

	List<UserProfileDto> showAllUsers(); /* ADMIN */

	List<String> showAllAdminsIds();

	List<ProviderProfileDto> showAllProviders();

	List<ServiceDto> addService(ServiceInputDto newService, String ProviderId); /* PROVIDER */

	RecordDto createRecord(RecordCreateDto record, String userId); /* CLIENT */

	RecordDto showRecord(String recordId, String userId); /* CLIENT */

	RecordDto removeRecord(String recordId, String userId); /* CLIENT */

	List<String> showProviderFreeTimeById(String providerId, LocalDate date); /* CLIENT */

	UserProfileDto removeUser(String userId); /* ADMIN */
	
	RecordDto showRecord(String recordId); /*PROVIDER*/
	
	List<ClientProfileDto> showAllClients();
	
	RecordDto updateRecord(String recordId, String userId, RecordUpdateDto updateData);

	ScheduleDto showShedule(String providerId);
	
	ScheduleDto showRecordOnDate(String date, String providerId);
	
	List<ServiceDto> showAllServices(String providerId);
}
