package service.dto;

import java.time.LocalDateTime;

public class RecordCreateDto {
	private String comment;
	private String providerId;
	private String serviceId;
	LocalDateTime startService;
	public RecordCreateDto() {
	}
	public RecordCreateDto(String comment, String providerId, String serviceId, LocalDateTime startService) {
		super();
		this.comment = comment;
		this.providerId = providerId;
		this.serviceId = serviceId;
		this.startService = startService;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getProviderId() {
		return providerId;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
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


	}
