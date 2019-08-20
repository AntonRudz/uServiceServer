package service.entities.mongodb;

import org.springframework.data.annotation.Id;

import service.dto.ServiceDto;
import service.dto.ServiceInputDto;

public class ServiceCrud {
	@Id
	String id;
	private String description;
	private int duration;
	private String name;
	private double price;
	private String type;

	public ServiceCrud() {

	}

	public ServiceCrud(ServiceInputDto service, String providerId) {
		id = providerId + "@" + service.getName();
		duration = service.getDuration();
		name = service.getName();
		price = service.getPrice();
		type = service.getType();
		description = service.getDescription();

	}

	public ServiceDto getService() {
		return new ServiceDto(id, name, price);
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public int getDuration() {
		return duration;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public String getType() {
		return type;
	}

}
