package telran.nikaion.dto;

import java.time.LocalTime;
import java.util.LinkedHashMap;

public class DayOfWeekPatternDto {
	LinkedHashMap<LocalTime, Boolean> intervals;

	public DayOfWeekPatternDto(LinkedHashMap<LocalTime, Boolean> intervals) {
		super();
		this.intervals = intervals;
	}

	public DayOfWeekPatternDto() {
	}

	public LinkedHashMap<LocalTime, Boolean> getIntervals() {
		return intervals;
	}

	public void setIntervals(LinkedHashMap<LocalTime, Boolean> intervals) {
		this.intervals = intervals;
	}

}
