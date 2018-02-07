
package com.example.m0d1x.miwok;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {
    ArrayList<dictionary> Colors = new ArrayList<>();
    ListView listview;

    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            mMediaPlayer.release();
        }

    };


    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        listview = (ListView) findViewById(R.id.ListView_colors);

        // Create a list of words
        Colors.add(new dictionary(getString(R.string.color_red), "weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
        Colors.add(new dictionary(getString(R.string.color_green), "chokokki", R.drawable.color_green, R.raw.color_green));
        Colors.add(new dictionary(getString(R.string.color_brown), "ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
        Colors.add(new dictionary(getString(R.string.color_grey), "ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
        Colors.add(new dictionary(getString(R.string.color_black), "kululli", R.drawable.color_black, R.raw.color_black));
        Colors.add(new dictionary(getString(R.string.color_Dusty_yellow), "ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        Colors.add(new dictionary(getString(R.string.color_mustard_yellow), "chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // costum_layout.xml layout file.
        DictionaryAdapter dictionary = new DictionaryAdapter(this, R.layout.costom_layout, Colors, R.color.category_colors);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listview.setAdapter(dictionary);

        // Set a click listener to play the audio when the list item is clicked on
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            //To release all media files
                releaseMediaPlayer();


                // Create and setup the {@link MediaPlayer} for the audio resource associated
                // with the current word
                mMediaPlayer = MediaPlayer.create(ColorsActivity.this, Colors.get(i).getSoundID());
                // Start the audio file
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(onCompletionListener);


            }
        });


    }
}
