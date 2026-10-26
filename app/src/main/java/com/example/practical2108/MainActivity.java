package com.example.practical2108;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.media.MediaPlayer;
import android.widget.VideoView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    Button btnPlay, btnPause, btnVideo;
    Button btnPauseVideo, btnRewind, btnForward;

    VideoView videoView;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect Java file with activity_main.xml
        setContentView(R.layout.activity_main);

        // Connect UI controls
        imageView = findViewById(R.id.imageView);

        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        btnVideo = findViewById(R.id.btnVideo);

        btnPauseVideo = findViewById(R.id.btnPauseVideo);
        btnRewind = findViewById(R.id.btnRewind);
        btnForward = findViewById(R.id.btnForward);

        videoView = findViewById(R.id.videoView);

        // Create MediaPlayer for audio
        mediaPlayer = MediaPlayer.create(
                this,
                R.raw.sample_audio
        );

        // Play Audio
        btnPlay.setOnClickListener(v -> {

            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();

                Toast.makeText(
                        this,
                        "Audio is playing",
                        Toast.LENGTH_SHORT
                ).show();
            }

        });

        // Pause Audio
        btnPause.setOnClickListener(v -> {

            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();

                Toast.makeText(
                        this,
                        "Audio is paused",
                        Toast.LENGTH_SHORT
                ).show();
            }

        });

        // Create Video URI
        Uri videoUri = Uri.parse(
                "android.resource://"
                        + getPackageName()
                        + "/"
                        + R.raw.sample_video
        );

        // Set Video URI
        videoView.setVideoURI(videoUri);

        // Create MediaController
        MediaController mediaController =
                new MediaController(this);

        mediaController.setAnchorView(videoView);

        videoView.setMediaController(mediaController);

        // Play Video
        btnVideo.setOnClickListener(v -> {

            videoView.start();

            Toast.makeText(
                    this,
                    "Video is playing",
                    Toast.LENGTH_SHORT
            ).show();

        });

        // Pause Video
        btnPauseVideo.setOnClickListener(v -> {

            if (videoView.isPlaying()) {
                videoView.pause();

                Toast.makeText(
                        this,
                        "Video is paused",
                        Toast.LENGTH_SHORT
                ).show();
            }

        });

        // Rewind Video by 3 seconds
        btnRewind.setOnClickListener(v -> {

            int currentPosition =
                    videoView.getCurrentPosition();

            int newPosition =
                    currentPosition - 3000;

            if (newPosition < 0) {
                newPosition = 0;
            }

            videoView.seekTo(newPosition);

            Toast.makeText(
                    this,
                    "Video rewinded by 3 seconds",
                    Toast.LENGTH_SHORT
            ).show();

        });

        // Forward Video by 3 seconds
        btnForward.setOnClickListener(v -> {

            int currentPosition =
                    videoView.getCurrentPosition();

            int duration =
                    videoView.getDuration();

            int newPosition =
                    currentPosition + 3000;

            if (newPosition > duration) {
                newPosition = duration;
            }

            videoView.seekTo(newPosition);

            Toast.makeText(
                    this,
                    "Video forwarded by 3 seconds",
                    Toast.LENGTH_SHORT
            ).show();

        });
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

        // Release MediaPlayer resources
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}