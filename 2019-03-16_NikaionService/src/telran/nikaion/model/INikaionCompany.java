package telran.nikaion.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import telran.nikaion.dto.ClientProfileDto;
import telran.nikaion.dto.NewProviderDto;
import telran.nikaion.dto.PatternOutputDto;
import telran.nikaion.dto.ProviderProfileDto;
import telran.nikaion.dto.ProviderUpdateDto;
import telran.nikaion.dto.RecordCreateDto;
import telran.nikaion.dto.RecordDto;
import telran.nikaion.dto.RecordUpdateDto;
import telran.nikaion.dto.ScheduleDto;
import telran.nikaion.dto.SchedulePattern;
import telran.nikaion.dto.ServiceDto;
import telran.nikaion.dto.ServiceInputDto;
import telran.nikaion.dto.UserDto;
import telran.nikaion.dto.UserProfileDto;
import telran.nikaion.dto.UserUpdateDto;

public interface INikaionCompany {
	UserProfileDto addUser(UserDto user); /* GUEST */

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
