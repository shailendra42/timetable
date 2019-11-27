package sta.uwi.edu.uwi_statimetablecompanion;

import java.util.ArrayList;

public class Course
{
    private String name;
    private String dept;
    private String code;
    private boolean selected;

    public Course(String dept,String code, String name)
    {
        this.dept = dept;
        this.name = name;
        this.code = code;
        selected = false;
    }



    public void setSelected(boolean b)
    {
        selected = b;
    }
    public boolean getSelected()
    {
        return selected;
    }
    public String getDept(){
        return dept;
    }
    public String getCourseCode(){
        return code;
    }
    public String getName(){
        return name;
    }
    @Override
    public String toString() {
        return this.code +" ("+ this.name+")";
    }
}
