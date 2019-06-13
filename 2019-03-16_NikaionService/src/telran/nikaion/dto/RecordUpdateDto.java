package telran.nikaion.dto;

import java.time.LocalDateTime;

public class RecordUpdateDto {
private String comment;
private LocalDateTime startService;
public RecordUpdateDto(String comment, LocalDateTime startService) {
	super();
	this.comment = comment;
	this.startService = startService;
}
public RecordUpdateDto() {

}
public String getComment() {
	return comment;
}
public void setComment(String comment) {
	this.comment = comment;
}
public LocalDateTime getStartService() {
	return startService;
}
public void setStartService(LocalDateTime startService) {
	this.startService = startService;
}


}
