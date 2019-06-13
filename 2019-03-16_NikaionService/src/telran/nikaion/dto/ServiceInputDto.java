package telran.nikaion.dto;

public class ServiceInputDto {
private String description;
private int duration;
private String name;
private double price;
private String type;
public ServiceInputDto(String description, int duration, String name, double price, String type) {
	super();
	this.description = description;
	this.duration = duration;
	this.name = name;
	this.price = price;
	this.type = type;
}
public ServiceInputDto() {
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public int getDuration() {
	return duration;
}
public void setDuration(int duration) {
	this.duration = duration;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}


}
