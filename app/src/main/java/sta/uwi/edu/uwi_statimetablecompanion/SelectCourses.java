package sta.uwi.edu.uwi_statimetablecompanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.app.SearchManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SelectCourses extends AppCompatActivity {

    ListView lv_courses;
    ArrayList<Course> courses;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_courses);
        lv_courses = findViewById(R.id.courselist);
        lv_courses.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        lv_courses.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckedTextView v = (CheckedTextView) view;
                boolean selected = v.isChecked();
                Course selectedCourse = (Course)lv_courses.getItemAtPosition(position);
                selectedCourse.setSelected(selected);
                Toast.makeText(getApplicationContext(),""+selectedCourse.getCourseCode()+" selected: "+ selected,
                        Toast.LENGTH_SHORT).show();
            }
        });

        //Some sample courses(just enough to scroll on my test device) until we have a full database to read from
        Course temp[] =
        {
            new Course("Department of Computing and Information Technology","COMP3602", "Theory of Computing"),
            new Course("Department of Computing and Information Technology","COMP3603","Human Computer Interaction"),
            new Course("Department of Computing and Information Technology", "COMP3609", "Game Programming"),
            new Course("Department of Computing and Information Technology","COMP3606","Wireless and Mobile Computing"),
            new Course("Department of Computing and Information Technology","COMP3613", "Software Engineering II"),
            new Course("Department of Computing and Information Technology","INFO3601", "Platform Technologies"),
            new Course("Department of Chemical Engineering","CHNG3004", "Chemical Reaction Engineering I"),
            new Course("Department of Chemical Engineering","CHNG3007", "Separation Processes II"),
            new Course("Department of Mathematics and Statistics","MATH2276", "Discrete Mathematics"),
            new Course("Department of Agriculture, Economics and Extension","AGEX2003", "Investigative Tools & Techniques for Extension"),
            new Course("Department of Physics","PHYS1222", "Intro. to Optics, Oscillations & Waves"),
            new Course("Department of Sports & Physical Education","PYED1003", "Anatomy & Physiology"),
            new Course("DDepartment of Electrical and Computer Engineering","ECNG3023", "Software Engineering"),
            new Course("Department of Electrical and Computer Engineering","ECNG3029", "Robotics"),
            new Course("Department of Electrical and Computer Engineering","ECNG2011", "Signals & Systems"),

        };


        courses = new ArrayList<Course>();
        for (int i = 0; i < temp.length; i++) {
            courses.add(temp[i]);
        }

        Collections.sort(courses, new CourseComparator());
        ContextThemeWrapper lv_themedContext = new ContextThemeWrapper(this, R.style.lv_AdapterTheme);
        final ArrayAdapter<Course> aa_courses
                = new ArrayAdapter<Course>(lv_themedContext, android.R.layout.simple_list_item_multiple_choice, courses);

        lv_courses.setAdapter(aa_courses);

        RadioGroup rg_departments = findViewById(R.id.rg_departments);
        String[] deptArray = getResources().getStringArray(R.array.department_array);
        for (int i = 0; i < deptArray.length; i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(deptArray[i]);
            radioButton.setId(i);
            radioButton.setPadding(20, 5, 60, 5);
            radioButton.setGravity(Gravity.CENTER_VERTICAL);
            rg_departments.addView(radioButton);

            if(Build.VERSION.SDK_INT>=21)
            {

                ColorStateList colorStateList = new ColorStateList(
                        new int[][]{new int[]{-android.R.attr.state_enabled},
                                new int[]{android.R.attr.state_enabled}},
                        new int[] {Color.BLACK,Color.parseColor("#009688")});

                radioButton.setButtonTintList(colorStateList);

            }
        }
        rg_departments.clearCheck();
        ((RadioButton)rg_departments.getChildAt(0)).setChecked(true);

        rg_departments.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton checkedRadioButton = (RadioButton)group.findViewById(checkedId);
                boolean isChecked = checkedRadioButton.isChecked();
                if (isChecked)
                {
                    if(!checkedRadioButton.getText().toString().toLowerCase().equals("all"))
                    {
                        ArrayList<Course> filterList = new ArrayList<>();
                        for (Course filteredCourses: courses)
                            if(filteredCourses.getDept().toLowerCase().equals(checkedRadioButton.getText().toString().toLowerCase()))
                            {
                                filterList.add(filteredCourses);
                            }
                        ArrayAdapter<Course> aa_filteredCourses
                                = new ArrayAdapter<Course>(getApplicationContext(), android.R.layout.simple_list_item_multiple_choice, filterList);
                        lv_courses.setAdapter(aa_filteredCourses);
                        for(int i=0 ; i<aa_filteredCourses.getCount() ; i++)
                        {
                            Course fc = aa_filteredCourses.getItem(i);
                            if(fc.getSelected()==true)lv_courses.setItemChecked(i,true);
                        }

                    }
                    else lv_courses.setAdapter(aa_courses);
                }
            }
        });
    }

    public class CourseComparator implements Comparator<Course> {
        @Override
        public int compare(Course o1, Course o2) {
            return o1.getCourseCode().compareTo(o2.getCourseCode());
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.deptFilter:
                RelativeLayout depthFilterRG = findViewById(R.id.deptFilterRG);
                if(depthFilterRG.getVisibility() != View.VISIBLE)depthFilterRG.setVisibility(View.VISIBLE);
                else depthFilterRG.setVisibility(View.GONE);
                return true;
            case R.id.confirm:
                Toast.makeText(getApplicationContext(),"That is all that was done thus far. Further activity will be implemented later.",
                        Toast.LENGTH_SHORT).show();
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.search_filter_menu, menu);
        MenuItem courseSearch = menu.findItem(R.id.courseSearch);
        SearchView sv = (SearchView) MenuItemCompat.getActionView(courseSearch);
        final SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        sv.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        sv.setIconifiedByDefault(false);
        sv.setFocusable(true);
        sv.setIconified(false);
        sv.requestFocus();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                ArrayList<Course> filterList = new ArrayList<>();
                for (Course filteredCourses: courses)
                    if(filteredCourses.toString().toLowerCase().contains(newText.toLowerCase()))
                    {
                        filterList.add(filteredCourses);
                    }
                ArrayAdapter<Course> aa_filteredCourses
                        = new ArrayAdapter<Course>(getApplicationContext(), android.R.layout.simple_list_item_multiple_choice, filterList);
                lv_courses.setAdapter(aa_filteredCourses);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
