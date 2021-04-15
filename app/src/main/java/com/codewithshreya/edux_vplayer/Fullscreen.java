package com.codewithshreya.edux_vplayer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

public class Fullscreen extends AppCompatActivity {

    private SimpleExoPlayer player;
    private PlayerView playerView;
    TextView textView;
    private String url;
    private boolean playwhenready = false;
    private int currentWindow = 0;
    private long playbackposition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Fullscreen");

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        playerView = findViewById(R.id.exoplayer_fullscreen);
        textView = findViewById(R.id.tv_fullscreen);

        Intent i= getIntent();
        url = i.getExtras().getString("ur");
        String tilte= i.getExtras().getString("nam");


        textView.setText(tilte);

    }


    /*private MediaSource buildMediaSource(Uri uri)
    {
        DataSource.Factory datasourcedactory = new DefaultHttpDataSourceFactory("video");
        return  new ProgressiveMediaSource.Factory(datasourcedactory).createMediaSource(uri);
        player.setPlayWhenReady(playwhenready);

    }*/
}