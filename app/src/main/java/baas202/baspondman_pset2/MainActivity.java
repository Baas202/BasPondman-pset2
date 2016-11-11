package baas202.baspondman_pset2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void getStarted(View view)
    {
        Intent second_screen = new Intent(this, Input_Activity.class);
        startActivity(second_screen);

    }
}
