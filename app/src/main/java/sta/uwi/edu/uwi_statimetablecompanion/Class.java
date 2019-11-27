package sta.uwi.edu.uwi_statimetablecompanion;

public class Class
{
    private Venue venue;
    private Course course;
    private int startTime;
    private int endTime;
    //private SomeType timePeriod;

    public Class(Course course, Venue venue, int start, int end)
    {
        this.course = course;
        this.venue = venue;
        this.startTime = start;
        this.endTime = end;
    }

    public int getStartTime(){
        return this.startTime;
    }
    public int getEndTime(){

        return this.endTime;
    }
    public String getName(){
        return this.course.getName();
    }
    public Venue getVenue(){
        return this.venue;

    }
}
