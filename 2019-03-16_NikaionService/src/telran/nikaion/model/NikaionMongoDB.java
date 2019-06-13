package telran.nikaion.model;

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
import telran.nikaion.dto.ClientProfileDto;
import telran.nikaion.dto.NewProviderDto;
import telran.nikaion.dto.PatternOutputDto;
import telran.nikaion.dto.ProviderProfileDto;
import telran.nikaion.dto.ProviderUpdateDto;
import telran.nikaion.dto.RecordCreateDto;
import telran.nikaion.dto.RecordDto;
import telran.nikaion.dto.RecordUpdateDto;
import telran.nikaion.dto.Roles;
import telran.nikaion.dto.ScheduleDto;
import telran.nikaion.dto.SchedulePattern;
import telran.nikaion.dto.ServiceDto;
import telran.nikaion.dto.ServiceInputDto;
import telran.nikaion.dto.ServiceReturnCode;
import telran.nikaion.dto.UserDto;
import telran.nikaion.dto.UserProfileDto;
import telran.nikaion.dto.UserUpdateDto;
import telran.nikaion.entities.mongodb.ProviderCrud;
import telran.nikaion.entities.mongodb.RecordCrud;
import telran.nikaion.entities.mongodb.SchedulePatternCrud;
import telran.nikaion.entities.mongodb.ServiceCrud;
import telran.nikaion.entities.mongodb.UserCrud;
import telran.nikaion.exeptions.NikaionForbiddenExeption;
import telran.nikaion.exeptions.NikaionNotFoundExeption;
import telran.nikaion.mongodb.repo.ProvidersRepository;
import telran.nikaion.mongodb.repo.Schedulerepository;
import telran.nikaion.mongodb.repo.UsersRepository;
import telran.security.IAccounts;

@Service
public class NikaionMongoDB implements INikaionCompany, IAccounts {

	@Autowired
	UsersRepository users;
	@Autowired
	ProvidersRepository providers;
	@Autowired
	Schedulerepository schedulse;
	// @Autowired
	// AccountRepository accounts;
	@Autowired
	PasswordEncoder passwordEncoder;
	// @Autowired
	// RecordRepository records;

	@Override
	public UserProfileDto addUser(UserDto user) {
		if (users.existsById(user.getEmail())) {
			throw new NikaionForbiddenExeption(ServiceReturnCode.USER_EXIST);
		}

		UserCrud userCrud = new UserCrud(user);
		userCrud.setPassword(passwordEncoder.encode(user.getPassword()));
		users.save(userCrud);
		// accounts.save(new AccountMongo(userCrud.getEmail(),
		// passwordEncoder.encode(userCrud.getPassword()), userCrud.getRoles()));

		return userCrud.getUser();
	}

	@Override
	public ProviderProfileDto addProvider(NewProviderDto provider, String idUser) {
		if (providers.existsById(idUser)) {
			throw new NikaionForbiddenExeption(ServiceReturnCode.PROVIDER_EXIST);
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
	public UserProfileDto getUser(String id) {
		return users.findById(id).orElseThrow(() -> new NikaionNotFoundExeption(ServiceReturnCode.USER_NOT_FOUND))
				.getUser();
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

		return providers.findById(id).orElseThrow(()->new NikaionNotFoundExeption(ServiceReturnCode.PROVIDER_NOT_FOUND)).getProvider();
	}

	@Override
	public List<ProviderProfileDto> getAllProviders() {
		return providers.findAll().stream().map(provider -> provider.getProvider()).collect(Collectors.toList());
	}

	@Override
	public ProviderProfileDto getProviderById(String id) {
		return providers.findById(id)
				.orElseThrow(() -> new NikaionNotFoundExeption(ServiceReturnCode.PROVIDER_NOT_FOUND)).getProvider();
	}

	@Override
	public UserProfileDto updateClient(UserUpdateDto dataUpdate, String id) {
		UserCrud userCrud = users.findById(id)
				.orElseThrow(() -> new NikaionNotFoundExeption(ServiceReturnCode.USER_NOT_FOUND));
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
			throw new NikaionNotFoundExeption(ServiceReturnCode.NO_SERVICES);
		}
		List<ServiceDto> servicesDto = new ArrayList<>();
		providers.findAll().stream().forEach(p -> p.getServices().forEach(s -> servicesDto.add(s.getService())));
		return servicesDto;
	}

	@Override
	public ServiceDto showServiceById(String serviceId) {
		String providerId = serviceId.substring(0, serviceId.lastIndexOf("@"));

		return providers.findById(providerId)
				.orElseThrow(() -> new NikaionNotFoundExeption(ServiceReturnCode.SERVICE_NOT_FOUND)).getServices()
				.stream().filter(s -> s.getId().equals(serviceId)).findFirst().get().getService();

	}

	@Override
	public ProviderProfileDto updateProvider(ProviderUpdateDto updateData, String providerId) {
		ProviderCrud provider = providers.findById(providerId)
				.orElseThrow(() -> new NikaionNotFoundExeption(ServiceReturnCode.PROVIDER_NOT_FOUND));
		if (updateData.getProfessions().length == 0 || updateData.getProfessions() == null) {
			throw new NikaionForbiddenExeption(ServiceReturnCode.WRONG_REQUEST);
		}
		provider.setProfessions(updateData.getProfessions());
		providers.save(provider);
		return provider.getProvider();
	}

	@Override
	public PatternOutputDto createSchedulePattern(SchedulePattern schedule, String providerId) {
		if (schedulse.existsById(providerId))
			throw new NikaionForbiddenExeption(ServiceReturnCode.SCHEDULE_ALREDY_EXIST);
		SchedulePatternCrud scheduleCrud = new SchedulePatternCrud(schedule, providerId);
		schedulse.save(scheduleCrud);
		ProviderCrud provider = providers.findById(providerId).get();
		provider.setAvailable(true);
		providers.save(provider);
		return scheduleCrud.getSchedulePattern();
	}

	@Override
	public ServiceDto removeServiceById(String serviceId, String providerId) {

		if (!serviceId.substring(0, serviceId.lastIndexOf("@")).equalsIgnoreCase(providerId)) {
			throw new NikaionForbiddenExeption(ServiceReturnCode.WRONG_REQUEST);
		}
		ProviderCrud providerCrud = providers.findById(providerId)
				.orElseThrow(() -> new NikaionNotFoundExeption(ServiceReturnCode.PROVIDER_NOT_FOUND));
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
		return schedulse.findById(providerId)
				.orElseThrow(() -> new NikaionNotFoundExeption(ServiceReturnCode.SCHEDULE_NOT_FOUND))
				.getSchedulePattern();
	}

	@Override
	public List<UserProfileDto> showAllUsers() {
		if (users.count() == 0) {
			throw new NikaionNotFoundExeption(ServiceReturnCode.USER_NOT_FOUND);
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
				.orElseThrow(() -> new NikaionNotFoundExeption(ServiceReturnCode.PROVIDER_NOT_FOUND));
		ServiceCrud serviceCrud = new ServiceCrud(newService, providerId);
		List<ServiceCrud> services = provider.getServices();
		if (provider.getServices().stream().map(s -> s.getId()).collect(Collectors.toList())
				.contains(serviceCrud.getId())) {
			throw new NikaionForbiddenExeption(ServiceReturnCode.SERVICE_EXIST);
		}
		services.add(serviceCrud);
		providers.save(provider);
		return services.stream().map(s -> s.getService()).collect(Collectors.toList());
	}

	@Override
	public RecordDto createRecord(RecordCreateDto record, String userId) {
		ProviderCrud provider = providers.findById(record.getProviderId())
				.orElseThrow(() -> new NikaionNotFoundExeption(ServiceReturnCode.PROVIDER_NOT_FOUND));
		if (!provider.isAvailable()) {
			throw new NikaionForbiddenExeption(ServiceReturnCode.WRONG_REQUEST);
		}

		String providerFreeTime = showProviderFreeTimeById(record.getProviderId(),
				record.getStartService().toLocalDate()).stream()
						.filter(t -> t.equalsIgnoreCase(record.getStartService().toLocalTime().toString())).findFirst()
						.orElse(null);
		if (providerFreeTime == null) {
			throw new NikaionForbiddenExeption(ServiceReturnCode.TIME_NOT_EMPTY);
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
			throw new NikaionNotFoundExeption(ServiceReturnCode.RECORD_NOT_FOUND);
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
			throw new NikaionNotFoundExeption(ServiceReturnCode.RECORD_NOT_FOUND);
		users.save(userCrud);
		return recordCrud.getRecord();
	}

	@Override
	public List<String> showProviderFreeTimeById(String providerId, LocalDate date) {
		List<String> providerFreeTime = new ArrayList<>();
		List<RecordCrud> recordsCrud = new ArrayList<>();
		users.findAll().stream().forEach(u -> u.getRecords().values().forEach(r -> recordsCrud.add(r)));
		List<LocalTime> providerBusyTime = recordsCrud.stream()
				.filter(r -> r.getProviderId().equals(providerId) & r.getStartService().toLocalDate().equals(date))
				.map(r -> r.getStartService().toLocalTime()).collect(Collectors.toList());

		LinkedHashMap<LocalTime, Boolean> providerSchedule = schedulse.findById(providerId)
				.orElseThrow(() -> new NikaionNotFoundExeption(ServiceReturnCode.SCHEDULE_NOT_FOUND))
				.getSchedulePattern().getDays().get(date.getDayOfWeek()).getIntervals();
		providerSchedule.entrySet().stream().filter(t -> !providerBusyTime.contains(t.getKey()) && t.getValue() == true)
				.forEach(s -> providerFreeTime.add(s.getKey().toString()));
		return providerFreeTime;
	}

	@Override
	public UserProfileDto removeUser(String userId) {
		UserCrud userCrud = users.findById(userId)
				.orElseThrow(() -> new NikaionNotFoundExeption(ServiceReturnCode.USER_NOT_FOUND));
		users.deleteById(userId);
		providers.deleteById(userId);
		schedulse.deleteById(userId);
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
			throw new NikaionNotFoundExeption(ServiceReturnCode.RECORD_NOT_FOUND);
		}
	
		String providerFreeTime = showProviderFreeTimeById(recordCrud.getProviderId(),
				updateData.getStartService().toLocalDate()).stream()
						.filter(t -> t.equalsIgnoreCase(updateData.getStartService().toLocalTime().toString()))
						.findFirst().orElse(null);
		if (providerFreeTime == null) {
			throw new NikaionForbiddenExeption(ServiceReturnCode.TIME_NOT_EMPTY);
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
		users.findAll().stream().forEach(u -> u.getRecords().values().stream()
				.filter(r -> r.getProviderId().equalsIgnoreCase(providerId) && r.getStartService().toLocalDate().isEqual(LocalDate.parse(date))).forEach(v -> {
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
		
		return providers.findById(providerId).get().getServices().stream().map(ServiceCrud::getService).collect(Collectors.toList());
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
