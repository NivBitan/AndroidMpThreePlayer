package nivbitan.mp3player6;

////SERVICE FOR RUNNING MP3PLAYER IN BACKGROUND :: DECIDED NOT TO USE THIS CLASS////

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import java.io.IOException;

public class PlayService extends Service implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener,MediaPlayer.OnErrorListener,
                                                    MediaPlayer.OnSeekCompleteListener,MediaPlayer.OnInfoListener, MediaPlayer.OnBufferingUpdateListener
{
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private String sntAudioLink;
    private Intent playIntent;
    public static boolean pauseMusic = false;

    @Override
    public void onCreate()
    {
        //attaching listeners to the service
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnErrorListener(this);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnSeekCompleteListener(this);
        mediaPlayer.setOnInfoListener(this);
        mediaPlayer.reset();
    }


    public int onStartCommand(Intent intent, int flags,int startId)
    {

        sntAudioLink = intent.getExtras().getString("sentAudioLink");
        mediaPlayer.reset();
        playIntent = intent;
        //set up the mediaplayer data source using the strAudioLink value
        if (!mediaPlayer.isPlaying()) { //if not playing
            try {
                mediaPlayer.setDataSource(sntAudioLink);
                mediaPlayer.prepareAsync();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
            }
        }
        return START_STICKY;
    }


    public void onDestroy()
    {

        String pause = playIntent.getExtras().getString("pause");
        if (pause == "true")
        {
            Toast.makeText(this,"pause == true, pausing media player",Toast.LENGTH_SHORT).show();
            mediaPlayer.pause();
            return;
        }
        Toast.makeText(this,"pause dosnt equal true, pause = " + pause,Toast.LENGTH_SHORT).show();
        super.onDestroy();
        if (mediaPlayer != null)
        {
            if (mediaPlayer.isPlaying())
                mediaPlayer.stop();
            mediaPlayer.release(); //release all memory that mediaplayer stores
        }

    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        stopMedia();
        stopSelf(); //stops service
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        switch (what)
        {
            //not valid for streaming
            case MediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK:
                Toast.makeText(this,"MEDIA ERROR NOT VALID FOR PROGRESSIVE PLAYBACK" + extra,Toast.LENGTH_SHORT).show();
                break;
            // service is dead
            case MediaPlayer.MEDIA_ERROR_SERVER_DIED:
                Toast.makeText(this,"MEDIA ERROR SERVER DIED" + extra,Toast.LENGTH_SHORT).show();
                break;
            case MediaPlayer.MEDIA_ERROR_UNKNOWN:
                Toast.makeText(this,"MEDIA ERROR UNKNOWN" + extra,Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
            playMedia();

    }

    @Override
    public void onSeekComplete(MediaPlayer mp) {

    }

    public void playMedia()
    {
        if (!mediaPlayer.isPlaying())
        {
            mediaPlayer.start();
        }
        else mediaPlayer.pause();
    }


    public void stopMedia()
    {
        if (mediaPlayer.isPlaying())
        {
            mediaPlayer.stop();
        }
    }
}
