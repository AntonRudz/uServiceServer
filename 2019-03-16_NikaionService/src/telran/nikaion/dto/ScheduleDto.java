package telran.nikaion.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class ScheduleDto {
	private HashMap<LocalDate, List<RecordDto>> days;

	public ScheduleDto() {
	
	}

	public ScheduleDto(HashMap<LocalDate, List<RecordDto>> days) {
		super();
		this.days = days;
	}

	public HashMap<LocalDate, List<RecordDto>> getDays() {
		return days;
	}

	public void setDays(HashMap<LocalDate, List<RecordDto>> days) {
		this.days = days;
	}
	
	
}
