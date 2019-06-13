package telran.nikaion.dto;

import java.time.LocalTime;

public class Break {
	private int breakPeriod;
	private LocalTime breakStart;
	public Break(int breakPeriod, LocalTime breakStart) {
		super();
		this.breakPeriod = breakPeriod;
		this.breakStart = breakStart;
	}
	public Break() {
	}
	public int getBreakPeriod() {
		return breakPeriod;
	}
	public void setBreakPeriod(int breakPeriod) {
		this.breakPeriod = breakPeriod;
	}
	public LocalTime getBreakStart() {
		return breakStart;
	}
	public void setBreakStart(LocalTime breakStart) {
		this.breakStart = breakStart;
	}
	
	
}
