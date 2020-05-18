package sg.edu.np.WhackAMole;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main2Activity extends AppCompatActivity {
    /* Hint
        - The function setNewMole() uses the Random class to generate a random value ranged from 0 to 8.
        - The function doCheck() takes in button selected and computes a hit or miss and adjust the score accordingly.
        - The functions readTimer() and placeMoleTimer() are to inform the user X seconds before starting and loading new mole.
        - Feel free to modify the function to suit your program.
    */

    private TextView scoring;
    CountDownTimer moleCountDown;
    CountDownTimer moleTimer;
    private Button getRandomLocation;
    private List<Button> buttonList = new ArrayList<>();
    private Integer advancedScore;
    private static final String TAG = "Whack-A-Mole 2.0";

    private void readyTimer(){
        /*  HINT:
            The "Get Ready" Timer.
            Log.v(TAG, "Ready CountDown!" + millisUntilFinished/ 1000);
            Toast message -"Get Ready In X seconds"
            Log.v(TAG, "Ready CountDown Complete!");
            Toast message - "GO!"
            belongs here.
            This timer countdown from 10 seconds to 0 seconds and stops after "GO!" is shown.
         */

        moleCountDown = new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.v(TAG, "Ready Countdown!" + millisUntilFinished / 1000);
                Toast.makeText(getApplicationContext(), "Get Ready in " + millisUntilFinished / 1000 + " seconds!", Toast.LENGTH_SHORT).show();
            }

            public void onFinish() {
                Log.v(TAG, "Ready Countdown Complete!");
                Toast.makeText(getApplicationContext(), "GO!",Toast.LENGTH_SHORT).show();
                moleCountDown.cancel();
                setNewMole();
                placeMoleTimer();

            }
        };
        moleCountDown.start();

    }
    private void placeMoleTimer(){
        /* HINT:
           Creates new mole location each second.
           Log.v(TAG, "New Mole Location!");
           setNewMole();
           belongs here.
           This is an infinite countdown timer.
         */
        moleTimer = new CountDownTimer(1000,1000){
            @Override
            public void onTick(long millisUntilFinished) {

            }
            @Override
            public void onFinish() {
                Log.v(TAG,"New Mole Location!");
                reset();
                setNewMole();
                moleTimer.start();
            }
        };
        moleTimer.start();
    }
    private static final int[] BUTTON_IDS = {
        /* HINT:
            Stores the 9 buttons IDs here for those who wishes to use array to create all 9 buttons.
            You may use if you wish to change or remove to suit your codes.*/
        R.id.buttonAdv1, R.id.buttonAdv2, R.id.buttonAdv3,
        R.id.buttonAdv4, R.id.buttonAdv5, R.id.buttonAdv6,
        R.id.buttonAdv7, R.id.buttonAdv8, R.id.buttonAdv9
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*Hint:
            This starts the countdown timers one at a time and prepares the user.
            This also prepares the existing score brought over.
            It also prepares the button listeners to each button.
            You may wish to use the for loop to populate all 9 buttons with listeners.
         */

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        scoring = (TextView) findViewById(R.id.advScore);

        Intent receivingEnd = getIntent();
        advancedScore = receivingEnd.getIntExtra("sendScore",0);
        scoring.setText(Integer.toString(advancedScore));

        Log.v(TAG, "Current User Score: " + advancedScore);


        for(final int id : BUTTON_IDS){
            /*  HINT:
            This creates a for loop to populate all 9 buttons with listeners.
            You may use if you wish to remove or change to suit your codes.
            */
            final Button buttonListener = (Button) findViewById(id);
            buttonListener.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    doCheck(buttonListener);
                }
            });
            buttonList.add(buttonListener);
        }
    }
    @Override
    protected void onStart(){
        super.onStart();
    }
    private void doCheck(Button checkButton)
    {
        /* Hint:
            Checks for hit or miss
            Log.v(TAG, "Hit, score added!");
            Log.v(TAG, "Missed, point deducted!");
            belongs here.
        */
        if (checkButton.getText().toString() == "*"){
            advancedScore +=1;
            scoring.setText(Integer.toString(advancedScore));
            Log.v(TAG, "Hit, score added!");

        }

        else{
            advancedScore -=1;
            scoring.setText(Integer.toString(advancedScore));
            Log.v(TAG, "Missed, point deducted!");
        }
        reset();
        setNewMole();
    }

    public void setNewMole()
    {
        /* Hint:
            Clears the previous mole location and gets a new random location of the next mole location.
            Sets the new location of the mole.
         */
        Random ran = new Random();
        int randomLocation = ran.nextInt(9);
        getRandomLocation = buttonList.get(randomLocation);
        getRandomLocation.setText("*");
    }

    public void reset(){
        getRandomLocation.setText("O");
    }
}

