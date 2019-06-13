package telran.nikaion.dto;

import java.time.LocalDateTime;

public class RecordDto {
	private String comment;
	private String id;
	private String serviceId;
	private LocalDateTime startService;
	private String userId;
	public RecordDto() {
	}
	public RecordDto(String comment, String id, String serviceId, LocalDateTime startService, String userId) {
		super();
		this.comment = comment;
		this.id = id;
		this.serviceId = serviceId;
		this.startService = startService;
		this.userId = userId;
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
	
	
}
