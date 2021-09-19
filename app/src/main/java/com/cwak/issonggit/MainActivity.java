package com.cwak.issonggit;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
        private Button play, pause, press;
        private SeekBar seekBar;
        private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            play = findViewById(R.id.button);
            pause = findViewById(R.id.button2);
            press = findViewById(R.id.button3);

            seekBar = findViewById(R.id.seekBar);


            // MediaPlayer using local source;


        //    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.music);

          // MediaPlayer Using remote source;

            mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource("http://socialdance.stanford.edu/music/Newport_Deux_Temps.m4a");
        } catch (IOException e) {
            e.printStackTrace();
        }

                            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                @Override
                                public void onPrepared(MediaPlayer mp) {

                                    Toast.makeText(MainActivity.this,"Ready to play", Toast.LENGTH_SHORT).show();
                                    mp.start();

                                    seekBar.setMax(mediaPlayer.getDuration());
                                    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                        @Override
                                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                           if (fromUser){
                                               mediaPlayer.seekTo(progress);
                                           }


                                        }

                                        @Override
                                        public void onStartTrackingTouch(SeekBar seekBar) {

                                        }

                                        @Override
                                        public void onStopTrackingTouch(SeekBar seekBar) {

                                        }
                                    });
                                }
                            });

        mediaPlayer.prepareAsync();

       // mediaPlayer.start();


            play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mediaPlayer.start();


                }
            });

            pause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.pause();

                }
            });

            press.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                    startActivity(intent);

                }
            });
    }
}