package sta.uwi.edu.uwi_statimetablecompanion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

   private ImageButton imgbtn_creatett, imgbtn_viewtt, imgbtn_viewmap, imgbtn_viewcalender;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgbtn_creatett =  findViewById(R.id.imgbtn_creatett);
        imgbtn_creatett.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,SelectCourses.class);
                startActivity(i);
            }
        });

        imgbtn_viewtt = findViewById(R.id.imgbtn_viewtt);
        imgbtn_viewtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"View/Edit TimeTable yet to be implemented",
                        Toast.LENGTH_SHORT).show();
            }
        });
        imgbtn_viewmap = findViewById(R.id.imgbtn_viewmap);
        imgbtn_viewmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Navigation map yet to be implemented",
                        Toast.LENGTH_SHORT).show();
            }
        });
        imgbtn_viewcalender = findViewById(R.id.imgbtn_viewcalender);
        imgbtn_viewcalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Events Calender yet to be implemented",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
