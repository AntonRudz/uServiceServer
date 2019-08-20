package service.dto;

import java.time.LocalTime;

public class SchedulePattern {
	private int interval;
	private LocalTime start;
	private LocalTime end;
	private Break [] breaks;
	private boolean [] isWorkday;
	
	public SchedulePattern() {
	}

	public SchedulePattern(int interval, LocalTime start, LocalTime end, Break[] breaks, boolean[] isWorkday) {
		super();
		this.interval = interval;
		this.start = start;
		this.end = end;
		this.breaks = breaks;
		this.isWorkday = isWorkday;
	}

	public int getInterval() {
		return interval;
	}

	public LocalTime getStart() {
		return start;
	}

	public LocalTime getEnd() {
		return end;
	}

	public Break[] getBreaks() {
		return breaks;
	}

	public boolean[] getIsWorkday() {
		return isWorkday;
	}
	
	
	
}
