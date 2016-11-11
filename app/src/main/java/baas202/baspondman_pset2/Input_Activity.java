package baas202.baspondman_pset2;

/**
 * Created by Bas on 8-11-2016.
 */
import android.content.DialogInterface;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class Input_Activity extends AppCompatActivity {

    private TextView words_left;
    private TextView type_word;
    private Story story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_activity);


        InputStream inputStream;
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
            // initialise variables
            int remaining_placeholders = story.getPlaceholderRemainingCount();
            words_left = (TextView) findViewById(R.id.words_left);
            type_word = (TextView) findViewById(R.id.type_word);



            // get the input from the user
            EditText user_input = (EditText) findViewById(R.id.user_input);
            String input = user_input.getText().toString();

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
                Intent show_story = new Intent(this, Show_story.class);
                show_story.putExtra("story", story);
                startActivityForResult(show_story, 1);
            }


            type_word.append(" " + story.getNextPlaceholder());
            words_left.append(" " + remaining_placeholders);


            // placeholder is filled in, reset the input text
            user_input.setText("");
        }
    }
}

