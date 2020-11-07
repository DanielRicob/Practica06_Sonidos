package ud.example.practicasonidos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SoundPool sPool;
    private int sound1,sound2,sound3,sound4;


    private MediaPlayer player;
    private AudioManager audioManager;
    private Button Button3,Button4,Button5,Button6;
    private SeekBar volumen;
    private TextView total, escuchado, datovolumen, porciento;

    private ProgressBar progressBar;
   // private SeekBar seekBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes attributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            sPool = new SoundPool.Builder()
                    .setAudioAttributes(attributes)
                    .setMaxStreams(10)
                    .build();

        } else
                {
                    sPool= new SoundPool(6, AudioManager.STREAM_MUSIC,0);
                }
        sound1 = sPool.load(this, R.raw.gallina,1);
        sound2 = sPool.load(this, R.raw.perro,1);
        sound3 = sPool.load(this, R.raw.leon,1);
        sound4 = sPool.load(this, R.raw.serpiente,1);


        Button3 = findViewById(R.id.button3);
        Button4 = findViewById(R.id.button4);
        Button5 = findViewById(R.id.button5);
        Button6 = findViewById(R.id.button6);
        total = findViewById(R.id.textView3);
        datovolumen = findViewById(R.id.textView2);
        escuchado = findViewById(R.id.textView5);

        porciento = findViewById(R.id.porciento);
        progressBar= findViewById(R.id.progressBar);
        volumen = findViewById(R.id.seekBar);


       audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        volumen.setMax(audioManager.getStreamMaxVolume(audioManager.STREAM_MUSIC));



        volumen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                progressBar.setProgress(progress * 7);
                porciento.setText(" " + progress  + "%");
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);


                //audioManager.setVolumeControlStream(AudioManager.STREAM_MUSIC,i,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });

    }


    public void suenabotton1(View v){sPool.play(sound1,1,1,1,0,1);}
    public void suenabotton2(View v){sPool.play(sound2,1,1,1,0,1);}
    public void suenabotton5(View v){sPool.play(sound3,1,1,1,0,1);}
    public void suenabotton6(View v){sPool.play(sound4,1,1,1,0,1);}

    public void suenabotton3(View v) {
        if (player != null && player.isPlaying()){
            player.stop();
            Button3.setText("Reproducir MediaPlayer");

                    }else{
            player = MediaPlayer.create(this, R.raw.pharrell_williams_happy);
                    total.setText(String.valueOf(player.getDuration()));
                    player.start();
            Button3.setText("Detener MediaPlayer");

        }
    }
        public void  suenabotton4(View v) {
            if (player != null) {
                if (player.isPlaying()) {
                    player.pause();
                    Button4.setText("REINICIAR");
                    escuchado.setText(String.valueOf(player.getCurrentPosition()));
                } else {
                    player.start();
                    Button4.setText("PAUSAR");
                }

            }
        }


            @Override
            protected void onDestroy () {
                super.onDestroy();
                sPool.release();
                sPool = null;
                player.release();
                player = null;
            }
        }







