package com.example.romario.alarmtimmer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView countDown;
    Button button;
    int tijd;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tijd = 30;

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        countDown = (TextView) findViewById(R.id.countDown);
        button = (Button) findViewById(R.id.button);

        seekBar.setMax(600);
        seekBar.setProgress(tijd);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tijd = progress;
                settijdText();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
    private void settijdText(){
        int minuten = tijd / 60;
        int seconden = tijd - minuten * 60;
        String tijdString = minuten + ":" + seconden;
        if (seconden < 10){
            tijdString = minuten + ":0" + seconden;
        }
        countDown.setText(tijdString);

    }

    public void Start (View view){
        String ButtonText = button.getText().toString();
        if(ButtonText.equals("Stop")){
            timer.cancel();
            button.setText("Start");
        }
        else {
            button.setText("Stop");

            timer = new CountDownTimer(tijd * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    tijd = (int) millisUntilFinished / 1000;
                    settijdText();

                }

                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);

                    mediaPlayer.start();
                    countDown.setText("0:00");

                }
            };
            timer.start();
        }
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
