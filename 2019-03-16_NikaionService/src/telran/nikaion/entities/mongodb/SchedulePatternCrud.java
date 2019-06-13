package telran.nikaion.entities.mongodb;


import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import org.springframework.data.annotation.Id;
import telran.nikaion.dto.Break;
import telran.nikaion.dto.DayOfWeekPatternDto;
import telran.nikaion.dto.PatternOutputDto;
import telran.nikaion.dto.SchedulePattern;

public class SchedulePatternCrud {
	@Id
	String id;
	int interval;
	 LocalTime start;
	 LocalTime end;
	 Break [] breaks;
	 boolean [] isWorkday;
	public SchedulePatternCrud() {

	}
	public SchedulePatternCrud(SchedulePattern schedule,String providerId) {
		id=providerId;
		interval=schedule.getInterval();
		start=schedule.getStart();
		end=schedule.getEnd();
		breaks=schedule.getBreaks();
		isWorkday=schedule.getIsWorkday();
	}
	
	public PatternOutputDto getSchedulePattern() {
		
		LinkedHashMap<LocalTime, Boolean> day=new LinkedHashMap<>();
		LocalTime startInt=start;
		if(breaks.length!=0) {
			for(int i=0;i<breaks.length;i++) {
			while(startInt.isBefore(breaks[i].getBreakStart())) {
				day.put(startInt, true);
//				System.out.println("before breake"+startInt);
				startInt=startInt.plusMinutes(interval);
			} 
			startInt=startInt.plusMinutes(breaks[i].getBreakPeriod());
			//System.out.println("after breake "+startInt);
			}
		}
		
		while(startInt.isBefore(end)) {
			day.put(startInt, true);
			//System.out.println("before breake"+startInt);
			startInt=startInt.plusMinutes(interval);
		}
		
		
		return new PatternOutputDto(getWorkDays(isWorkday, day));
	}
	
	private LinkedHashMap<DayOfWeek, DayOfWeekPatternDto> getWorkDays(boolean [] isWorkday, LinkedHashMap<LocalTime, Boolean> day){
		LinkedHashMap<DayOfWeek, DayOfWeekPatternDto> days=new LinkedHashMap<>();
		for (int i = 0; i < isWorkday.length; i++) {
			if(isWorkday[i]) {
				days.put(DayOfWeek.of(i+1), new DayOfWeekPatternDto(day));
			}
		}
		return days;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}
	public LocalTime getStart() {
		return start;
	}
	public void setStart(LocalTime start) {
		this.start = start;
	}
	public LocalTime getEnd() {
		return end;
	}
	public void setEnd(LocalTime end) {
		this.end = end;
	}
	public Break[] getBreaks() {
		return breaks;
	}
	public void setBreaks(Break[] breaks) {
		this.breaks = breaks;
	}
	public boolean[] getIsWorkday() {
		return isWorkday;
	}
	public void setIsWorkday(boolean[] isWorkday) {
		this.isWorkday = isWorkday;
	}
	
	 
}
