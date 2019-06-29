package telran.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import static telran.nikaion.dto.NikaionApiConstants.*;

@Configuration
public class AccountingAuthorization extends WebSecurityConfigurerAdapter {
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.httpBasic();
		http.cors();
				
		http.authorizeRequests().antMatchers(SHOW_CLIENT).hasRole("CLIENT");
		http.authorizeRequests().antMatchers(CREATE_PROVIDER).hasAnyRole("ADMIN", "CLIENT");
		http.authorizeRequests().antMatchers(SHOW_PROVIDER).hasRole("PROVIDER");
		http.authorizeRequests().antMatchers(UPDATE_CLIENT).hasAnyRole("CLIENT", "PROVIDER");
		http.authorizeRequests().antMatchers(UPDATE_PROVIDER).hasRole("PROVIDER");
		http.authorizeRequests().antMatchers(CREATE_SHEDULE_PATTERN).hasRole("PROVIDER");
		http.authorizeRequests().antMatchers(REMOVE_SERVICE_BY_ID).hasRole("PROVIDER");
		http.authorizeRequests().antMatchers(SHOW_SCHEDULE_PATTERN).hasRole("PROVIDER");
		http.authorizeRequests().antMatchers(SHOW_ALL_USERS).hasRole("ADMIN");
		http.authorizeRequests().antMatchers(SHOW_ALL_ADMIND_ID).hasRole("ADMIN");
		http.authorizeRequests().antMatchers(SHOW_ALL_PROVIDERS).hasRole("ADMIN");
		http.authorizeRequests().antMatchers(ADD_SERVICE).hasRole("PROVIDER");
		http.authorizeRequests().antMatchers(CREATE_RECORD).hasRole("CLIENT");
		http.authorizeRequests().antMatchers(SHOW_RECORD).hasRole("CLIENT");
		http.authorizeRequests().antMatchers(REMOVE_RECORD).hasRole("CLIENT");
		http.authorizeRequests().antMatchers(SHOW_PROVIDER_FREE_TIME_BY_ID).hasRole("CLIENT");
		http.authorizeRequests().antMatchers(REMOVE_USER).hasRole("ADMIN");
		
	
		
		http.authorizeRequests().antMatchers(SHOW_RECORD_P).hasRole("PROVIDER");
		http.authorizeRequests().antMatchers(UPDATE_RECORD).hasRole("CLIENT");
		http.authorizeRequests().antMatchers(SHOW_ALL_CLIENTS).hasRole("ADMIN");
		
		http.authorizeRequests().antMatchers(SHOW_SCHEDULE).hasRole("PROVIDER");
		http.authorizeRequests().antMatchers(SHOW_RECORD_ON_DATE).hasRole("PROVIDER");
		http.authorizeRequests().antMatchers(SHOW_ALL_SERVICES_P).hasRole("PROVIDER");
	}
}
