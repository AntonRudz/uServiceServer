package service.dto;

import java.time.DayOfWeek;
import java.util.LinkedHashMap;

public class PatternOutputDto {
	LinkedHashMap<DayOfWeek, DayOfWeekPatternDto> days;

	public PatternOutputDto(LinkedHashMap<DayOfWeek, DayOfWeekPatternDto> days) {
		super();
		this.days = days;
	}

	public PatternOutputDto() {
	}

	public LinkedHashMap<DayOfWeek, DayOfWeekPatternDto> getDays() {
		return days;
	}

	public void setDays(LinkedHashMap<DayOfWeek, DayOfWeekPatternDto> days) {
		this.days = days;
	}

	
	
}
