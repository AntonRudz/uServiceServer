package service.entities.mongodb;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import service.dto.RecordCreateDto;
import service.dto.RecordDto;

public class RecordCrud {
	String comment;
	@Id
	String id;
	String serviceId;
	LocalDateTime startService;
	String userId;
	String providerId;

	public RecordCrud() {

	}

	public RecordCrud(RecordCreateDto record, String userId) {
		super();
		this.comment = record.getComment();
		this.id = userId + "@" + record.getStartService().toString();
		this.serviceId = record.getServiceId();
		this.startService = record.getStartService();
		this.userId = userId;
		this.providerId = record.getProviderId();
	}

	public RecordDto getRecord() {

		return new RecordDto(comment, id, serviceId, startService, userId);
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public LocalDateTime getStartService() {
		return startService;
	}

	public void setStartService(LocalDateTime startService) {
		this.startService = startService;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	

}
