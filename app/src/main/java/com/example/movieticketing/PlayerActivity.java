package com.example.movieticketing;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PictureInPictureParams;
import android.graphics.YuvImage;
import android.os.Bundle;
import android.util.Rational;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class PlayerActivity extends YouTubeBaseActivity {
    private String id;
    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        id = getIntent().getStringExtra("id");

        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.player);

        youTubePlayerView.initialize("AIzaSyAvjN0ujFxkHyj22T0-rfMAvyCWozNA0XU",
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer youTubePlayer, boolean b) {

                        // do any work here to cue video, play video, etc.
                        player = youTubePlayer;
                        youTubePlayer.loadVideo(id);
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {

                    }
                });

    }

    public void enterPipMode(View view) {
        Button pipButton = findViewById(R.id.minimiseButton);
        Rational rational = new Rational(youTubePlayerView.getWidth(), youTubePlayerView.getHeight());
        PictureInPictureParams params = new PictureInPictureParams.Builder().setAspectRatio(rational).build();
        pipButton.setVisibility(View.INVISIBLE);
        enterPictureInPictureMode(params);

//        ImageView pictureInPictureIcon = new ImageView(this);

    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureinPictureMode) {
        super.onPictureInPictureModeChanged(isInPictureinPictureMode);
    }

}