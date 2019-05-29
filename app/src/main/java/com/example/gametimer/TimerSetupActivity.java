package com.example.gametimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class TimerSetupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer_setup);

        Button create = (Button) findViewById(R.id.buttonCreateTimer);



        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int numSeconds;
                int numPlayers;

                Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
                EditText secondsInput = (EditText) findViewById(R.id.secondsEditText);
                EditText playerInput = (EditText) findViewById(R.id.editTextPlayers);
                TextView timeNeeded = (TextView) findViewById(R.id.timeForFive);

                numSeconds = 30;
                numPlayers = 6;

                if(!secondsInput.getText().toString().isEmpty()) {
                    numSeconds = Integer.parseInt(secondsInput.getText().toString());

                    Bundle extras = new Bundle();

                    extras.putInt("com.example.gametimer.secondsPass", numSeconds);
                    // extras.putInt("com.example.gametimer.playersPass", numPlayers);

                    startIntent.putExtras(extras);

                    startActivity(startIntent);
                } else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.noinput),
                            Toast.LENGTH_LONG).show();
                }
               // if(playerInput.getText().toString().isEmpty()) {
                   // numPlayers = Integer.parseInt(playerInput.getText().toString());
                //}
                //put the number of seconds here
            }
        });
    }
}
