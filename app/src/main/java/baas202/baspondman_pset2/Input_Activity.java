package baas202.baspondman_pset2;

/**
 * Created by Bas on 8-11-2016.
 */
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class Input_Activity extends AppCompatActivity {

    private TextView words_left;
    private TextView type_word;
    private EditText user_input;
    private Button submit_button;
    private Story story;
    private InputStream inputStream;
    private int total_placeholders;
    private String input;
    private String fill_placeholder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_activity);

        Button button = (Button) findViewById(R.id.submit_button);

        InputStream inputStream = null;
        try {
            inputStream = getAssets().open("madlib1_tarzan.txt");
            story = new Story(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* from adarshr
    http://stackoverflow.com/questions/5238491/check-if-string-contains-only-letters
     */
    public boolean isAlpha(String input) {
        char[] chars = input.toCharArray();

        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }


    public void submitWord(View view) {
        // save the input in the placeholder when button is clicked
        {

            int remaining_placeholders = story.getPlaceholderRemainingCount();


            // get the input from the user
            EditText user_input = (EditText) findViewById(R.id.user_input);
            input = user_input.getText().toString();

            /* from John Tapley
            https://www.youtube.com/watch?v=XyFsUGEaaio
             */
            if (!isAlpha(input)) {
                AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
                myAlert.setMessage("Please type a string!")
                        .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setTitle("Error!")
                        .create();
                myAlert.show();
            }
            else if (input.length() == 0){
                AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
                myAlert.setMessage("You haven't entered anything!")
                        .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setTitle("Error!")
                        .create();
                myAlert.show();

            }
            else {
                story.fillInPlaceholder(input);
                Toast.makeText(this, "Succesfull!", Toast.LENGTH_LONG).show();
            }

            // go to 3th screen when all placeholders are filled in by the user
            if (remaining_placeholders == 0) {
                final int result = 1;
                Intent show_story = new Intent(this, Show_story.class);
                show_story.putExtra("story", story);
                startActivityForResult(show_story, result);
            }

            // placeholder is filled in, reset the input text
            user_input.setText("");

        }
    }
}

    /*
    // determine how many placeholders need to be filled.
    public void fill_the_placeholders (Story story){

        // check if story's placeholders are already filled in or not
        if (!story.isFilledIn())
        {
            remain_placeholder = story.getPlaceholderRemainingCount();
            words_left = (TextView)findViewById(R.id.texto);
            words_left = (TextView)


        }

        // story is filled in and should continue to next screen
        else
        {





        }



    } */
