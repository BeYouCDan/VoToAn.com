package hw6;

public class Record implements Comparable<Record> {

	private String process;
	private long deadline; 
	private long duration; 

	// constructor to create a new record
	// given the name of the process,
	// deadline and duration
	Record (String process, long deadline, long duration)
	{
		this.process = process;
		this.deadline = deadline;
		this.duration = duration;
	}

	// constructor to create a new record
	// from the esisting record and new
	// duration
	Record (Record r, long duration)
	{
		this.process=r.process;
		this.deadline=r.deadline;
		this.duration=duration;
	}
	
	public long GetDuration()
	{
		return duration;
	}
	
	public String toString()
	{
		return process+" with deadline "+deadline+" and duration "+duration;
	}
	public String toString(long current_time)
	{
		if(current_time > deadline) return process + " (late)";
		return process;
	}
	
	@Override
    public int compareTo(Record o) 
	{
		...//complete the method.
    }
}
