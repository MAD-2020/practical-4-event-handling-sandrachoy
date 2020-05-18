package sg.edu.np.WhackAMole;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    /* Hint
        - The function setNewMole() uses the Random class to generate a random value ranged from 0 to 2.
        - The function doCheck() takes in button selected and computes a hit or miss and adjust the score accordingly.
        - The function doCheck() also decides if the user qualifies for the advance level and triggers for a dialog box to ask for user to decide.
        - The function nextLevelQuery() builds the dialog box and shows. It also triggers the nextLevel() if user selects Yes or return to normal state if user select No.
        - The function nextLevel() launches the new advanced page.
        - Feel free to modify the function to suit your program.
    */

    private Button buttonLeft;
    private Button buttonMid;
    private Button buttonRight;
    private Button getRandomLocation;
    private List<Button> buttonList = new ArrayList<>();
    private TextView scoring;
    private static final String TAG = "Whack-A-Mole 1.0";
    private Integer scoreValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLeft = (Button) findViewById(R.id.Button1);
        buttonMid = (Button) findViewById(R.id.Button2);
        buttonRight = (Button) findViewById(R.id.Button3);
        scoring = (TextView) findViewById(R.id.Score);
        scoreValue = Integer.parseInt(scoring.getText().toString());

        buttonList.add(buttonLeft);
        buttonList.add(buttonMid);
        buttonList.add(buttonRight);

        Log.v(TAG, "Finished Pre-Initialisation!");


    }
    @Override
    protected void onStart(){
        super.onStart();
        setNewMole();
        Log.v(TAG, "Starting GUI!");

        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG,"Button Left Clicked!");
                if (buttonLeft.getText().toString() == "*"){

                    scoreValue +=1;
                    scoring.setText(Integer.toString(scoreValue));

                    Log.v(TAG,"Hit, score added!");
                    doCheck(buttonLeft);
                }
                else{
                    scoreValue -=1;
                    scoring.setText(Integer.toString(scoreValue));

                    Log.v(TAG,"Missed, score deducted!");
                }
                reset();
                setNewMole();
            }
        });
        buttonMid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG,"Button Mid Clicked!");
                if (buttonMid.getText().toString() == "*"){

                    scoreValue +=1;
                    scoring.setText(Integer.toString(scoreValue));
                    Log.v(TAG,"Hit, score added!");
                    doCheck(buttonMid);
                }
                else{
                    scoreValue -=1;
                    scoring.setText(Integer.toString(scoreValue));

                    Log.v(TAG,"Missed, score deducted!");
                }
                reset();
                setNewMole();
            }
        });
        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG,"Button Right Clicked!");
                if (buttonRight.getText().toString() == "*"){

                    scoreValue +=1;
                    scoring.setText(Integer.toString(scoreValue));
                    Log.v(TAG,"Hit, score added!");
                    doCheck(buttonRight);
                }
                else{
                    scoreValue -=1;
                    scoring.setText(Integer.toString(scoreValue));

                    Log.v(TAG,"Missed, score deducted!");
                }
                reset();
                setNewMole();
            }
        });
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.v(TAG, "Paused Whack-A-Mole!");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.v(TAG, "Stopped Whack-A-Mole!");
        finish();
    }

    private void doCheck(Button checkButton) {
        /* Checks for hit or miss and if user qualify for advanced page.
            Triggers nextLevelQuery().
         */

        if (scoreValue > 0 && scoreValue % 10 == 0){
            nextLevelQuery();
        }
    }

    private void nextLevelQuery(){
        /*
        Builds dialog box here.
        Log.v(TAG, "User accepts!");
        Log.v(TAG, "User decline!");
        Log.v(TAG, "Advance option given to user!");
        belongs here*/
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Would you like to advance to advanced mode?").setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.v(TAG,"User accepts!");
                nextLevel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.v(TAG,"User decline!");

            }
        });
        AlertDialog alert = builder.create();
        alert.setTitle("Warning! Insane Whack-A-Mole incoming!");
        alert.show();
        Log.v(TAG,"Advance option given to user!");
    }

    private void nextLevel(){
        /* Launch advanced page */
        Intent AdvancedPage = new Intent(MainActivity.this, Main2Activity.class);
        AdvancedPage.putExtra("sendScore",scoreValue);
        startActivity(AdvancedPage);
    }

    private void setNewMole() {
        Random ran = new Random();
        int randomLocation = ran.nextInt(3);

        getRandomLocation = buttonList.get(randomLocation);

        //buttonList.get(randomLocation).setText("*");
        getRandomLocation.setText("*");
    }

    private void reset(){
        /*buttonLeft.setText("O");
        buttonMid.setText("O");
        buttonRight.setText("O");*/
        getRandomLocation.setText("O");
    }
}