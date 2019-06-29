package telran.nikaion.dto;

public interface NikaionApiConstants {
	String CREATE_CLIENT = "/main"; /* create new client GUEST */
	String SHOW_CLIENT = "/client"; /* show client CLIENT */
	String CREATE_PROVIDER = "/client"; /* create new provider CLIENT */
	String SHOW_PROVIDER = "/provider"; /* show_provider CLIENT */
	String SHOW_PROVIDERS = "/main/providers"; /* show providers GUEST */
	String SHOW_PROVIDER_BY_ID = "/main/providers/{providerId:.+}"; /* show provider by id GUEST */
	String UPDATE_CLIENT = "/client"; /* CLIENT */
	String SHOW_ALL_SERVICES = "/main/services"; /* GUEST */
	String SHOW_SERVICE_BY_ID = "/main/services/{serviceId}"; /* GUEST */
	String UPDATE_PROVIDER = "/provider"; /* PROVIDER */
	String CREATE_SHEDULE_PATTERN = "/provider/pattern"; /* PROVIDER */
	String REMOVE_SERVICE_BY_ID = "/provider/services/{serviceId}"; /* PROVIDER */
	String SHOW_SCHEDULE_PATTERN = "/provider/pattern"; /* PROVIDER */
	String SHOW_ALL_USERS = "/admin/users"; /* ADMIN */
	String SHOW_ALL_ADMIND_ID = "/admin/users/admins"; /* ADMIN */
	String SHOW_ALL_PROVIDERS = "/admin/users/providers"; /* ADMIN */
	String ADD_SERVICE="/provider/services"; /*PROVIDER*/
	String CREATE_RECORD="/client/records"; /*CLIENT*/
	String SHOW_RECORD="/client/records/{recordId}"; /*CLIENT*/
	String REMOVE_RECORD="/client/records/{recordId}"; /*CLIENT*/ 
	String SHOW_PROVIDER_FREE_TIME_BY_ID="/client/providers/{providerId}/{date}"; /*CLIENT*/
	/*---------------------------------------------------*/
	String REMOVE_USER="/admin/users/{userId}"; /*ADMIN*/
	String SHOW_ALL_CLIENTS="/admin/users/clients"; /*ADMIN*/
	
	String UPDATE_SERVICE="/provider/services/{serviceID}"; /*PROVIDER*/
	String SHOW_RECORD_ON_DATE="/provider/records/{date}";	/*PROVIDER*/
	String SHOW_RECORD_P="/provider/record/{recordId}";	/*PROVIDER*/
	String SHOW_SCHEDULE="/provider/records"; /*PROVIDER*/
	String UPDATE_SHEDULE_PATTERN="/provider/pattern"; /*PROVIDER*/
	String CHANGE_ACTIVITY="/provider/activity"; /*PROVIDER*/
	String SHOW_ALL_SERVICES_P="/provider/services";
	String UPDATE_RECORD="/client/records/{recordId}"; /*CLIENT*/
}