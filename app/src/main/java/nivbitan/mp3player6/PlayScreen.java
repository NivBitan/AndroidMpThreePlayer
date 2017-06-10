package nivbitan.mp3player6;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class PlayScreen extends AppCompatActivity implements  Runnable {


    private ImageButton buttonPlayPause;
    private ImageButton buttonRestart;
    boolean boolMusicPlaying = false;
    boolean pausedMusic = false;
    boolean restartedMusic = false;
    TextView timePos, timeDur;
    String strAudioLink;
    MediaPlayer mediaPlayer;
    String[] artist_names;
    String[] artist_songs;
    int songPos;
    ScheduledExecutorService myScheduledExecutorService;
    ScheduledFuture<?> FutureScheduledExecutorService;
    //---Seek Bar Variables---///
    String sntSeekPos;
    int mediaPosition;
    int mediaMax;
    private Handler handler =  null;
    Handler monitorHandler = null;
    private SeekBar seekBar;
    ImageView PlayScreenImageView;
   // public static final String BROADCAST_ACTION = 7:00

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        artist_names = getResources().getStringArray(R.array.artist_names);
        setContentView(R.layout.activity_play_screen);
        AudioControl();
        //mediaPlayer.reset();
        Intent intent = getIntent();
        String songName = intent.getStringExtra("songName");
        String artistName = intent.getStringExtra("artistName");
        String txt = songName + " By "  + artistName;
        TextView PlayScreenSongNameTextView = (TextView) findViewById(R.id.PlayScreenSongNametextview);
        PlayScreenImageView = (ImageView) findViewById(R.id.PlayScreenImageView);
        timePos = (TextView)findViewById(R.id.textViewTimePos);
        timeDur = (TextView)findViewById(R.id.textViewTimeDur);

         monitorHandler = new Handler(){

            @Override
            public void handleMessage(Message msg) {
                mediaPlayerMonitor();
            }

        };
        ScheduledExecutorService myScheduledExecutorService = Executors.newScheduledThreadPool(1);
        FutureScheduledExecutorService = myScheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                monitorHandler.sendMessage(monitorHandler.obtainMessage());

            }
        }, 200, 200, TimeUnit.MILLISECONDS);
        PlayScreenSongNameTextView.setText(txt);
        strAudioLink = setAudioLink(artistName,songName,artist_songs);
        initViews();
        setListeneres();

    }

    private void mediaPlayerMonitor()
    {
            //"%02d m, %02d s"
        String mediaDurationString = String.format("%02d:%02d",
            TimeUnit.MILLISECONDS.toMinutes(mediaPlayer.getDuration()),
            TimeUnit.MILLISECONDS.toSeconds(mediaPlayer.getDuration()) -
                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(mediaPlayer.getDuration()))
        );


        String mediaPositionString = String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(mediaPlayer.getCurrentPosition()),
                TimeUnit.MILLISECONDS.toSeconds(mediaPlayer.getCurrentPosition()) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(mediaPlayer.getCurrentPosition()))
        );
        timePos.setText(mediaPositionString);
        timeDur.setText(mediaDurationString);
    }
    public void AudioControl()
    {
        mediaPlayer = new MediaPlayer();
        seekBar = (SeekBar) findViewById(R.id.SeekBar1);
        seekBar.setMax(mediaPlayer.getDuration());
        handler = new Handler();
    }


    public String setAudioLink(String artistName,String songName,  String[] artist_songs)
    {
        if (artistName.equals("Guns N Roses")) {
            artist_songs = getResources().getStringArray(R.array.guns_N_roses_songs);
            PlayScreenImageView.setImageResource(R.drawable.guns1);
            if (songName.equals("Sweet Child Of Mine"))
                return "http://www.fmplayer.co.il/100fmPlayer/stronger.mp3";
            if (songName.equals("November Rain"))
                return "http://www.fmplayer.co.il/100fmPlayer/zero1.mp3";
            if (songName.equals("Welcome To The Jungle"))
                return "http://www.fmplayer.co.il/100fmPlayer/myhouse.mp3";
        }
        if (artistName.equals("Alice In Chains")) {
            artist_songs = getResources().getStringArray(R.array.Alice_In_Chains_songs);
            PlayScreenImageView.setImageResource(R.drawable.alice1);
            if (songName.equals("Would"))
                return "http://www.fmplayer.co.il/100fmPlayer/myhouse.mp3";
            if (songName.equals("Stone"))
                return "http://www.fmplayer.co.il/100fmPlayer/blackmagic.mp3";
            if (songName.equals("Rooster"))
                return "http://www.fmplayer.co.il/100fmPlayer/zero1.mp3";
        }
        if (artistName.equals("Red Hot Chili Peppers")) {
            artist_songs = getResources().getStringArray(R.array.Red_Hot_Chili_Peppers_songs);
            PlayScreenImageView.setImageResource(R.drawable.redhot1);
            if (songName.equals("OtherSide"))
                return "http://www.fmplayer.co.il/100fmPlayer/sugar.mp3";
            if (songName.equals("Californication"))
                return "http://www.fmplayer.co.il/100fmPlayer/blackmagic.mp3";
            if (songName.equals("Under The Bridge"))
                return "http://www.fmplayer.co.il/100fmPlayer/stronger.mp3";
            if (songName.equals("Scar Tissue"))
                return "http://www.fmplayer.co.il/100fmPlayer/zero1.mp3";
            if (songName.equals("Suck My Kiss"))
                return "http://www.fmplayer.co.il/100fmPlayer/zero1.mp3";
        }
        if (artistName.equals("Led Zeppelin")) {
            artist_songs = getResources().getStringArray(R.array.Led_Zepplin_songs);
            PlayScreenImageView.setImageResource(R.drawable.led_zeppelin1);
            if (songName.equals("Kashmir"))
                return "http://www.fmplayer.co.il/100fmPlayer/stronger.mp3";
            if (songName.equals("Stairway to Heaven"))
                return "http://www.fmplayer.co.il/100fmPlayer/zero1.mp3";
            if (songName.equals("Black Dog"))
                return "http://www.fmplayer.co.il/100fmPlayer/zero1.mp3";
            if (songName.equals("The Lemon Song"))
                return "http://www.fmplayer.co.il/100fmPlayer/zero1.mp3";
            if (songName.equals("Ramble On"))
                return "http://www.fmplayer.co.il/100fmPlayer/zero1.mp3";
        }
        if (artistName.equals("Nirvana")) {
            artist_songs = getResources().getStringArray(R.array.Nirvana_songs);
            PlayScreenImageView.setImageResource(R.drawable.nirvana1);
            if (songName.equals("Come As You Are"))
                return "http://www.fmplayer.co.il/100fmPlayer/blackmagic.mp3";
            if (songName.equals("Smell Like Teen Spirit"))
                return "http://www.fmplayer.co.il/100fmPlayer/zero1.mp3";
            if (songName.equals("Lithium"))
                return "http://www.fmplayer.co.il/100fmPlayer/sugar.mp3";
            if (songName.equals("Polly"))
                return "http://www.fmplayer.co.il/100fmPlayer/zero1.mp3";
            if (songName.equals("On A Plain"))
                return "http://www.fmplayer.co.il/100fmPlayer/zero1.mp3";
            if (songName.equals("In Bloom"))
                return "http://www.fmplayer.co.il/100fmPlayer/blackmagic.mp3";
        }
        if (artistName.equals("Disturbed")) {
            artist_songs = getResources().getStringArray(R.array.Disturbed_songs);
            PlayScreenImageView.setImageResource(R.drawable.disturbed1);
            if (songName.equals("The Game"))
                return "http://www.fmplayer.co.il/100fmPlayer/blackmagic.mp3";
            if (songName.equals("Stupify"))
                return "http://www.fmplayer.co.il/100fmPlayer/zero1.mp3";
            if (songName.equals("Down With The Sickness"))
                return "http://www.fmplayer.co.il/100fmPlayer/sugar.mp3";
        }
        if (artistName.equals("Linkin Park")) {
            artist_songs = getResources().getStringArray(R.array.Linkin_Park_songs);
            PlayScreenImageView.setImageResource(R.drawable.linkinpark1);
            if (songName.equals("In The End"))
                return "http://www.fmplayer.co.il/100fmPlayer/zero1.mp3";
            if (songName.equals("Numb"))
                return "http://www.fmplayer.co.il/100fmPlayer/zero1.mp3";
            if (songName.equals("New Divide"))
                return "http://www.fmplayer.co.il/100fmPlayer/blackmagic.mp3";
        }
        if (artistName.equals("ACDC")) {
            artist_songs = getResources().getStringArray(R.array.ACDC_songs);
            PlayScreenImageView.setImageResource(R.drawable.acdc1);
            if (songName.equals("T.N.T"))
                return "http://www.fmplayer.co.il/100fmPlayer/blackmagic.mp3";
            if (songName.equals("Thunderstruck"))
                return "http://www.fmplayer.co.il/100fmPlayer/sugar.mp3";
            if (songName.equals("Highway To Hell"))
                return "http://www.fmplayer.co.il/100fmPlayer/zero1.mp3";
        }
        if (artistName.equals("The Doors")) {
            artist_songs = getResources().getStringArray(R.array.The_Doors_songs);
            PlayScreenImageView.setImageResource(R.drawable.thedoors1);
            if (songName.equals("Riders On The Storm"))
                return "http://www.fmplayer.co.il/100fmPlayer/blackmagic.mp3";
            if (songName.equals("Alabama Song"))
                return "http://www.fmplayer.co.il/100fmPlayer/zero1.mp3";
            if (songName.equals("People Are Strange"))
                return "http://www.fmplayer.co.il/100fmPlayer/sugar.mp3";
        }
        if (artistName.equals("Metallica")) {
            artist_songs = getResources().getStringArray(R.array.Metallica_songs);
            PlayScreenImageView.setImageResource(R.drawable.metallica1);
            if (songName.equals("To Live Is to Die"))
                return "http://www.fmplayer.co.il/100fmPlayer/blackmagic.mp3";
            if (songName.equals("The Day That Never Comes"))
                return "http://www.fmplayer.co.il/100fmPlayer/zero1.mp3";
            if (songName.equals("Wherever I May Roam"))
                return "http://www.fmplayer.co.il/100fmPlayer/blackmagic.mp3";
            if (songName.equals("For Whom The Bell Tolls"))
                return "http://www.fmplayer.co.il/100fmPlayer/zero1.mp3";
        }
        if (artistName.equals("Avenge Sevenfold")) {
            artist_songs = getResources().getStringArray(R.array.Avenge_Sevenfold_songs);
            PlayScreenImageView.setImageResource(R.drawable.avengesevenfold1);
            if (songName.equals("Almost Easy"))
                return "http://www.fmplayer.co.il/100fmPlayer/blackmagic.mp3";
            if (songName.equals("Seize the Day"))
                return "http://www.fmplayer.co.il/100fmPlayer/zero1.mp3";
            if (songName.equals("Dear God"))
                return "http://www.fmplayer.co.il/100fmPlayer/sugar.mp3";
        }
        if (artistName.equals("Black Sabbath")) {
            artist_songs = getResources().getStringArray(R.array.Black_Sabbath_songs);
            PlayScreenImageView.setImageResource(R.drawable.blacksabbath1);
            if (songName.equals("Iron Ma"))
                return "http://www.fmplayer.co.il/100fmPlayer/blackmagic.mp3";
            if (songName.equals("War Pigs"))
                return "http://www.fmplayer.co.il/100fmPlayer/zero1.mp3";
            if (songName.equals("Paranoid"))
                return "http://www.fmplayer.co.il/100fmPlayer/sugar.mp3";
        }
        return "";
    }

    public void run()
    {
        int currentPosition= 0;
        int total = mediaPlayer.getDuration();
        while (mediaPlayer!=null && currentPosition<total)
        {
            try
            {
                Thread.sleep(1000);
                currentPosition= mediaPlayer.getCurrentPosition();
            } catch (InterruptedException e)
            {
                return;
            } catch (Exception e
                    ) {
                return;
            }
            seekBar.setProgress(currentPosition);
        }
    }

    private void initViews()
    {
        buttonPlayPause = (ImageButton) findViewById(R.id.ButtonPlayPause);
        buttonPlayPause.setBackgroundResource(R.drawable.playbtn);
        buttonRestart = (ImageButton) findViewById(R.id.ButtonRestrat);
        buttonRestart.setBackgroundResource(R.drawable.restartbtn);
    }

    private void setListeneres()
    {
        buttonPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonPlayPauseClick();
            }
        });

        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonRestartClick();
            }
        });

        seekBarListener();


    }

    public void seekBarListener()
    {


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress_value;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) mediaPlayer.seekTo(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void buttonRestartClick()
    {
        restartAudio();
    }

    private void buttonPlayPauseClick()
    {
        if(!boolMusicPlaying) // play
        {
            buttonPlayPause.setBackgroundResource(R.drawable.pausebtn);
            if (pausedMusic == true)
            {
                pausedMusic = false;
                resumeAudio();
            }
            else
            {
                playAudio();
            }
            boolMusicPlaying = true;
        }
        else // pause
        {
            buttonPlayPause.setBackgroundResource(R.drawable.playbtn);
            pauseAudio();
            boolMusicPlaying = false;
        }
    }

    private void  resumeAudio()
    {
        mediaPlayer.seekTo(songPos);
        mediaPlayer.start();
        Toast.makeText(this,"resume",Toast.LENGTH_SHORT).show();
    }

    private void playAudio()
    {
        try {
            mediaPlayer.setDataSource(strAudioLink);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.prepareAsync();
        if (restartedMusic == true)
        {
            restartedMusic = false;
            buttonPlayPause.setBackgroundResource(R.drawable.pausebtn);
        }
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.start();
                seekBar.setProgress(0);
                seekBar.setMax(mp.getDuration());
                new Thread(PlayScreen.this).start();
            }
        });
        Toast.makeText(this,"play",Toast.LENGTH_SHORT).show();
    }

    private void pauseAudio()
    {
        mediaPlayer.pause();
        pausedMusic = true;
        songPos = mediaPlayer.getCurrentPosition();
        Toast.makeText(this,"pause",Toast.LENGTH_SHORT).show();
    }

    private void restartAudio()
    {
        Toast.makeText(this,"restart",Toast.LENGTH_SHORT).show();
        mediaPlayer.reset();
        restartedMusic = true;
        boolMusicPlaying = true;
        playAudio();
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FutureScheduledExecutorService.cancel(true);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        if (mediaPlayer != null)
            mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }
}
