package com.codewithshreya.edux_vplayer;
import  android.app.Application;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import android.net.Uri;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

import  androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends  RecyclerView.ViewHolder {


    SimpleExoPlayer exoPlayer;
    PlayerView playerView;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
         itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 mClicklistener.onItemClick(v, getAdapterPosition());

             }
         });
         itemView.setOnLongClickListener(new View.OnLongClickListener() {
             @Override
             public boolean onLongClick(View v) {
                 mClicklistener.onItemLongClick(v, getAdapterPosition());
                 return false;
             }
         });




    }
    public void  setExoPlayer(Application application, String name, String Videourl)
    {
        TextView textView  = itemView.findViewById(R.id.tv_item_name);
        playerView = itemView.findViewById(R.id.exoplayer_item);
        textView.setText(name);

        try
        {
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter.Builder(application).build();
            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            exoPlayer = (SimpleExoPlayer) ExoPlayerFactory.newSimpleInstance(application);
            Uri video = Uri.parse(Videourl);
            DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("video");
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            MediaSource mediaSource = new ExtractorMediaSource(video, dataSourceFactory, extractorsFactory, null, null);
            playerView.setPlayer(exoPlayer);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(false);


        } catch (Exception e) {
            Log.e("ViewHolder", "exoplayer error"+e.toString());
        }
    }

    private ViewHolder.Clicklistener mClicklistener;
    public  interface Clicklistener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);}

        public  void setOnClickListener(ViewHolder.Clicklistener clickListener)
        {
            mClicklistener = clickListener;


    }
}
