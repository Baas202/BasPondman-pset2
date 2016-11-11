package baas202.baspondman_pset2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Bas on 11-11-2016.
 */

public class Show_story extends AppCompatActivity {

    private Story story;
    private TextView storyView;
    private String currentStory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.output_activity);

        storyView = (TextView) findViewById(R.id.show_text);

        // call the story from the second screen
        Intent second_screen = getIntent();;
        currentStory = story.toString();
        storyView.setText(currentStory);
    }
}
