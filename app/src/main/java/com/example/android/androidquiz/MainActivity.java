package com.example.android.androidquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * method that calls other methods for calculating and displaying score
     * @param view is the submit button
     */
    public void submitScore(View view)
    {
        int score = calculateTotal();

        displayScore(score);
    }

    /**
     * method that calculates the user's total score
     * @return the total score from user's quiz answers
     */
    public int calculateTotal()
    {
        int score = 0;

        // logic handling points for first question
        RadioButton questionOneChoiceD = (RadioButton)findViewById(R.id.q1_choiceC);
        boolean questionOneIsCorrect = questionOneChoiceD.isChecked();
        if (questionOneIsCorrect)
            score++;

        // logic handling points for second question
        EditText questionTwoEditText = (EditText)findViewById(R.id.q2_editText);
        String quesTwoStr = questionTwoEditText.getText().toString();
        if (quesTwoStr.toLowerCase().contains("linear"))
            score++;

        // logic handling points for the third question
        CheckBox biscuitCheckBox = (CheckBox)findViewById(R.id.biscuit);
        boolean biscuitChecked = biscuitCheckBox.isChecked();

        CheckBox nougatCheckBox = (CheckBox)findViewById(R.id.nougat);
        boolean nougatChecked = nougatCheckBox.isChecked();

        CheckBox eclairCheckBox = (CheckBox)findViewById(R.id.eclair);
        boolean eclairChecked = eclairCheckBox.isChecked();

        if (biscuitChecked && nougatChecked && eclairChecked)
            score++;

        // logic handling points for the fourth question
        if (apiVer == 15)
            score++;

        return score;
    }

    int apiVer = 10;

    /**
     * increments the number for the 4th question's response
     * @param view is the + button
     */
    public void increment(View view) {
        if (apiVer == 20)
            return;
        apiVer += 1;
        displayQuesFour(apiVer);
    }

    /**
     * decrements the number for the 4th question's response
     * @param view is the - button
     */
    public void decrement(View view) {
        if (apiVer == 10)
            return;
        apiVer -= 1;
        displayQuesFour(apiVer);
    }

    /**
     * displays the number for the 4th question's response
     * after the increment or decrement buttons are pressed
     * @param number
     */
    private void displayQuesFour(int number) {
        TextView apiVerTextView = (TextView) findViewById(R.id.api_version);
        apiVerTextView.setText(String.valueOf(number));
    }

    /**
     * displays the user's score after the submit button has been pressed
     * @param score is the score to display onto the app
     */
    public void displayScore(int score)
    {
        TextView scoreView = (TextView)findViewById(R.id.score);
        String scoreMessage;
        if (score == 4)
        {
            scoreMessage = "Your score: " + score + " out of 4" + "\nPerfect score! Congrats!";
        }
        else
        {
            scoreMessage = "Your score: " + score + " out of 4";
        }
        Toast.makeText(getApplicationContext(),scoreMessage,Toast.LENGTH_SHORT).show();
        scoreView.setText(scoreMessage);
    }
}
