package telran.security.dto;

public class Account {
public String username;
public String password;
public String[]roles;
public Account() {}
public Account(String username, String password, String[] roles) {
	super();
	this.username = username;
	this.password = password;
	this.roles = roles;
}
public String getUsername() {
	return username;
}
public String getPassword() {
	return password;
}
public String[] getRoles() {
	return roles;
}

}
