package service.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import service.dto.ClientProfileDto;
import service.dto.NewProviderDto;
import service.dto.PatternOutputDto;
import service.dto.ProviderProfileDto;
import service.dto.ProviderUpdateDto;
import service.dto.RecordCreateDto;
import service.dto.RecordDto;
import service.dto.RecordUpdateDto;
import service.dto.Roles;
import service.dto.ScheduleDto;
import service.dto.SchedulePattern;
import service.dto.ServiceDto;
import service.dto.ServiceInputDto;
import service.dto.ServiceReturnCode;
import service.dto.UserDto;
import service.dto.UserProfileDto;
import service.dto.UserUpdateDto;
import service.entities.mongodb.ProviderCrud;
import service.entities.mongodb.RecordCrud;
import service.entities.mongodb.SchedulePatternCrud;
import service.entities.mongodb.ServiceCrud;
import service.entities.mongodb.UserCrud;
import service.exeptions.ServiceForbiddenExeption;
import service.exeptions.ServiceNotFoundExeption;
import service.mongodb.repo.ProvidersRepository;
import service.mongodb.repo.UsersRepository;
import service.security.IAccounts;

@Service
public class NikaionMongoDB implements INikaionCompany, IAccounts {

	@Autowired
	UsersRepository users;
	@Autowired
	ProvidersRepository providers;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public ClientProfileDto addUser(UserDto user) {
		if (users.existsById(user.getEmail())) {
			throw new ServiceForbiddenExeption(ServiceReturnCode.USER_EXIST);
		}

		UserCrud userCrud = new UserCrud(user);
		userCrud.setPassword(passwordEncoder.encode(user.getPassword()));
		users.save(userCrud);
		

		return userCrud.getClient();
	}

	@Override
	public ProviderProfileDto addProvider(NewProviderDto provider, String idUser) {
		if (providers.existsById(idUser)) {
			throw new ServiceForbiddenExeption(ServiceReturnCode.PROVIDER_EXIST);
		}
		UserCrud user = users.findById(idUser).orElse(null);
		ProviderCrud providerCrud = new ProviderCrud(provider, user);
		String[] roles = user.getRoles();
		roles = Arrays.copyOf(roles, 2);
		roles[1] = Roles.ROLE_PROVIDER.toString();
		user.setRoles(roles);
		providers.save(providerCrud);
		users.save(user);
		return providerCrud.getProvider();
	}

	@Override
	public ClientProfileDto getClient(String id) {
		return users.findById(id).orElseThrow(() -> new ServiceNotFoundExeption(ServiceReturnCode.USER_NOT_FOUND))
				.getClient();
	}

	@Override
	public String getPassword(String username) {
		UserCrud user = users.findById(username).orElse(null);
		String password = null;
		if (user != null)
			password = user.getPassword();
		return password;
	}

	@Override
	public String[] getRoles(String username) {
		UserCrud user = users.findById(username).orElse(null);
		String roles[] = null;
		if (user != null)
			roles = user.getRoles();
		return roles;
	}

	@Override
	public ProviderProfileDto getProvider(String id) {
		System.out.println(id);
		return providers.findById(id)
				.orElseThrow(() -> new ServiceNotFoundExeption(ServiceReturnCode.PROVIDER_NOT_FOUND)).getProvider();
	}

	@Override
	public List<ProviderProfileDto> getAllProviders() {
		return providers.findAll().stream().map(provider -> provider.getProvider()).collect(Collectors.toList());
	}

	@Override
	public ProviderProfileDto getProviderById(String id) {
		System.out.println(id);
		ProviderProfileDto provider = providers.findById(id)
				.orElseThrow(() -> new ServiceNotFoundExeption(ServiceReturnCode.PROVIDER_NOT_FOUND)).getProvider();
		System.out.println(provider.getFirstName());
		return provider;
	}

	@Override
	public UserProfileDto updateClient(UserUpdateDto dataUpdate, String id) {
		UserCrud userCrud = users.findById(id)
				.orElseThrow(() -> new ServiceNotFoundExeption(ServiceReturnCode.USER_NOT_FOUND));
		userCrud.setCommunications(dataUpdate.getCommunications());
		userCrud.setDateBirth(dataUpdate.getDateBirth());
		userCrud.setFirstName(dataUpdate.getFirstName());
		userCrud.setLastName(dataUpdate.getLastName());
		users.save(userCrud);

		return userCrud.getUser();
	}

	@Override
	public List<ServiceDto> showAllServices() {
		if (providers.count() == 0) {
			throw new ServiceNotFoundExeption(ServiceReturnCode.NO_SERVICES);
		}
		List<ServiceDto> servicesDto = new ArrayList<>();
		providers.findAll().stream().forEach(p -> p.getServices().forEach(s -> servicesDto.add(s.getService())));
		return servicesDto;
	}

	@Override
	public ServiceDto showServiceById(String serviceId) {
		String providerId = serviceId.substring(0, serviceId.lastIndexOf("@"));

		return providers.findById(providerId)
				.orElseThrow(() -> new ServiceNotFoundExeption(ServiceReturnCode.SERVICE_NOT_FOUND)).getServices()
				.stream().filter(s -> s.getId().equals(serviceId)).findFirst().get().getService();

	}

	@Override
	public ProviderProfileDto updateProvider(ProviderUpdateDto updateData, String providerId) {
		ProviderCrud provider = providers.findById(providerId)
				.orElseThrow(() -> new ServiceNotFoundExeption(ServiceReturnCode.PROVIDER_NOT_FOUND));
		if (updateData.getProfessions().length == 0 || updateData.getProfessions() == null) {
			throw new ServiceForbiddenExeption(ServiceReturnCode.WRONG_REQUEST);
		}
		provider.setProfessions(updateData.getProfessions());
		providers.save(provider);
		return provider.getProvider();
	}

	@Override
	public PatternOutputDto createSchedulePattern(SchedulePattern schedule, String providerId) {
		if (providers.findById(providerId).get().getSchedule() == null)
			throw new ServiceForbiddenExeption(ServiceReturnCode.SCHEDULE_ALREDY_EXIST);
		SchedulePatternCrud scheduleCrud = new SchedulePatternCrud(schedule, providerId);
		ProviderCrud provider = providers.findById(providerId).get();
		provider.setSchedule(scheduleCrud);
		provider.setAvailable(true);
		providers.save(provider);
		return scheduleCrud.getSchedulePattern();
	}

	@Override
	public ServiceDto removeServiceById(String serviceId, String providerId) {

		if (!serviceId.substring(0, serviceId.lastIndexOf("@")).equalsIgnoreCase(providerId)) {
			throw new ServiceForbiddenExeption(ServiceReturnCode.WRONG_REQUEST);
		}
		ProviderCrud providerCrud = providers.findById(providerId)
				.orElseThrow(() -> new ServiceNotFoundExeption(ServiceReturnCode.PROVIDER_NOT_FOUND));
		List<ServiceCrud> servicesCrud = providerCrud.getServices();

		ServiceDto deletedService = servicesCrud.stream().filter(s -> s.getId().equals(serviceId)).findFirst().get()
				.getService();
		servicesCrud = servicesCrud.stream().filter(s -> !s.getId().equals(serviceId)).collect(Collectors.toList());
		providerCrud.setServices(servicesCrud);
		providers.save(providerCrud);
		return deletedService;
	}

	@Override
	public PatternOutputDto showSchedulePattern(String providerId) {
		SchedulePatternCrud schedule = providers.findById(providerId).get().getSchedule();
		if (schedule == null)
			throw new ServiceNotFoundExeption(ServiceReturnCode.SCHEDULE_NOT_FOUND);
		return schedule.getSchedulePattern();
	}

	@Override
	public List<UserProfileDto> showAllUsers() {
		if (users.count() == 0) {
			throw new ServiceNotFoundExeption(ServiceReturnCode.USER_NOT_FOUND);
		}

		return users.findAll().stream()
				.filter(user -> !Arrays.asList(user.getRoles()).contains(Roles.ROLE_ADMIN.toString()))
				.map(user -> user.getUser()).collect(Collectors.toList());
	}

	@Override
	public List<String> showAllAdminsIds() {

		return users.findAll().stream()
				.filter(user -> Arrays.asList(user.getRoles()).contains(Roles.ROLE_ADMIN.toString()))
				.map(user -> user.getEmail()).collect(Collectors.toList());
	}

	@Override
	public List<ProviderProfileDto> showAllProviders() {

		return providers.findAll().stream().map(p -> p.getProvider()).collect(Collectors.toList());
	}

	@Override
	public List<ServiceDto> addService(ServiceInputDto newService, String providerId) {
		ProviderCrud provider = providers.findById(providerId)
				.orElseThrow(() -> new ServiceNotFoundExeption(ServiceReturnCode.PROVIDER_NOT_FOUND));
		ServiceCrud serviceCrud = new ServiceCrud(newService, providerId);
		List<ServiceCrud> services = provider.getServices();
		if (provider.getServices().stream().map(s -> s.getId()).collect(Collectors.toList())
				.contains(serviceCrud.getId())) {
			throw new ServiceForbiddenExeption(ServiceReturnCode.SERVICE_EXIST);
		}
		services.add(serviceCrud);
		providers.save(provider);
		return services.stream().map(s -> s.getService()).collect(Collectors.toList());
	}

	@Override
	public RecordDto createRecord(RecordCreateDto record, String userId) {
		ProviderCrud provider = providers.findById(record.getProviderId())
				.orElseThrow(() -> new ServiceNotFoundExeption(ServiceReturnCode.PROVIDER_NOT_FOUND));
		if (!provider.isAvailable()) {
			throw new ServiceForbiddenExeption(ServiceReturnCode.WRONG_REQUEST);
		}

		String providerFreeTime = showProviderFreeTimeById(record.getProviderId(),
				record.getStartService().toLocalDate()).stream()
						.filter(t -> t.equalsIgnoreCase(record.getStartService().toLocalTime().toString())).findFirst()
						.orElse(null);
		if (providerFreeTime == null) {
			throw new ServiceForbiddenExeption(ServiceReturnCode.TIME_NOT_EMPTY);
		}
		RecordCrud recordCrud = new RecordCrud(record, userId);
		UserCrud userCrud = users.findById(userId).get();
		userCrud.addRecord(recordCrud);
		users.save(userCrud);
		return recordCrud.getRecord();
	}

	@Override
	public RecordDto showRecord(String recordId, String userId) {
		RecordCrud recordCrud = users.findById(userId).get().getRecords().get(recordId);
		if (recordCrud == null) {
			throw new ServiceNotFoundExeption(ServiceReturnCode.RECORD_NOT_FOUND);
		}
		return recordCrud.getRecord();
	}

	@Override
	public RecordDto removeRecord(String recordId, String userId) {
		UserCrud userCrud = users.findById(userId).get();
		RecordCrud recordCrud = userCrud.getRecords().get(recordId);
		if (recordCrud != null) {
			userCrud.removeRecord(recordCrud);
		} else
			throw new ServiceNotFoundExeption(ServiceReturnCode.RECORD_NOT_FOUND);
		users.save(userCrud);
		return recordCrud.getRecord();
	}

	@Override
	public List<String> showProviderFreeTimeById(String providerId, LocalDate date) {
		ProviderCrud provider = providers.findById(providerId)
				.orElseThrow(() -> new ServiceNotFoundExeption(ServiceReturnCode.PROVIDER_NOT_FOUND));
		if (!provider.isAvailable())
			throw new ServiceForbiddenExeption(ServiceReturnCode.PROVIDER_NOT_AVAIBALE);
		List<String> providerFreeTime = new ArrayList<>();
		List<RecordCrud> recordsCrud = new ArrayList<>();
		users.findAll().stream().forEach(u -> u.getRecords().values().forEach(r -> recordsCrud.add(r)));
		List<LocalTime> providerBusyTime = recordsCrud.stream()
				.filter(r -> r.getProviderId().equals(providerId) & r.getStartService().toLocalDate().equals(date))
				.map(r -> r.getStartService().toLocalTime()).collect(Collectors.toList());

		LinkedHashMap<LocalTime, Boolean> providerSchedule = provider.getSchedule()
				.getSchedulePattern().getDays().get(date.getDayOfWeek()).getIntervals();
		providerSchedule.entrySet().stream().filter(t -> !providerBusyTime.contains(t.getKey()) && t.getValue() == true)
				.forEach(s -> providerFreeTime.add(s.getKey().toString()));
		return providerFreeTime;
	}

	@Override
	public UserProfileDto removeUser(String userId) {
		UserCrud userCrud = users.findById(userId)
				.orElseThrow(() -> new ServiceNotFoundExeption(ServiceReturnCode.USER_NOT_FOUND));
		users.deleteById(userId);
		providers.deleteById(userId);
		return userCrud.getUser();
	}

	@Override
	public RecordDto showRecord(String recordId) {
		String userId = recordId.substring(0, recordId.lastIndexOf('@'));
		return users.findById(userId).get().getRecords().get(recordId).getRecord();
	}

	@Override
	public List<ClientProfileDto> showAllClients() {
		List<ClientProfileDto> clients = users.findAll().stream().filter(u -> !u.getRecords().isEmpty())
				.map(c -> c.getClient()).collect(Collectors.toList());

		return clients;
	}

	@Override
	public RecordDto updateRecord(String recordId, String userId, RecordUpdateDto updateData) {
		UserCrud userCrud = users.findById(userId).get();
		RecordCrud recordCrud = userCrud.getRecords().get(recordId);
		if (recordCrud == null) {
			throw new ServiceNotFoundExeption(ServiceReturnCode.RECORD_NOT_FOUND);
		}

		String providerFreeTime = showProviderFreeTimeById(recordCrud.getProviderId(),
				updateData.getStartService().toLocalDate()).stream()
						.filter(t -> t.equalsIgnoreCase(updateData.getStartService().toLocalTime().toString()))
						.findFirst().orElse(null);
		if (providerFreeTime == null) {
			throw new ServiceForbiddenExeption(ServiceReturnCode.TIME_NOT_EMPTY);
		}
		userCrud.removeRecord(recordCrud);
		recordCrud.setId(userId + '@' + updateData.getStartService());
		recordCrud.setStartService(updateData.getStartService());
		recordCrud.setComment(updateData.getComment());
		userCrud.addRecord(recordCrud);
		users.save(userCrud);

		return recordCrud.getRecord();
	}

	@Override
	public ScheduleDto showShedule(String providerId) {
		HashMap<LocalDate, List<RecordDto>> schedule = new HashMap<>();
		users.findAll().stream().forEach(u -> u.getRecords().values().stream()
				.filter(r -> r.getProviderId().equalsIgnoreCase(providerId)).forEach(v -> {
					List<RecordDto> records = new ArrayList<>();
					if (schedule.containsKey(v.getStartService().toLocalDate())) {
						records = schedule.get(v.getStartService().toLocalDate());
						records.add(v.getRecord());
						schedule.put(v.getStartService().toLocalDate(), records);
					} else {
						records.add(v.getRecord());
						schedule.put(v.getStartService().toLocalDate(), records);
					}

				}

		));

		return new ScheduleDto(schedule);
	}

	@Override
	public ScheduleDto showRecordOnDate(String date, String providerId) {
		HashMap<LocalDate, List<RecordDto>> schedule = new HashMap<>();
		users.findAll().stream()
				.forEach(
						u -> u.getRecords().values().stream()
								.filter(r -> r.getProviderId().equalsIgnoreCase(providerId)
										&& r.getStartService().toLocalDate().isEqual(LocalDate.parse(date)))
								.forEach(v -> {
									List<RecordDto> records = new ArrayList<>();
									if (schedule.containsKey(v.getStartService().toLocalDate())) {
										records = schedule.get(v.getStartService().toLocalDate());
										records.add(v.getRecord());
										schedule.put(v.getStartService().toLocalDate(), records);
									} else {
										records.add(v.getRecord());
										schedule.put(v.getStartService().toLocalDate(), records);
									}

								}

						));
		return new ScheduleDto(schedule);
	}

	@Override
	public List<ServiceDto> showAllServices(String providerId) {

		return providers.findById(providerId).get().getServices().stream().map(ServiceCrud::getService)
				.collect(Collectors.toList());
	}

	// @SuppressWarnings("serial")
	// @ResponseStatus(HttpStatus.NOT_FOUND)
	// class UserNotFoundException extends RuntimeException {
	//
	// public UserNotFoundException(ServiceReturnCode message) {
	// super(message.toString());
	// }
	// }

}

// @Override
// public boolean removeAccount(String username) {
// if(!accounts.existsById(username))
// return false;
// accounts.deleteById(username);
// return true;
// }
//
// @Override
// public boolean updatePassword(String username, String newPassword) {
// AccountMongo account=accounts.findById(username).orElse(null);
// if(account==null)
// return false;
// account.setPassword(passwordEncoder.encode(newPassword));
// accounts.save(account);
// return true;
// }
