
package com.example.m0d1x.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {
    ListView listview;
    ArrayList<dictionary> family = new ArrayList<>();
    //Handles playback of all the sound files
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
        setContentView(R.layout.activity_family);
        listview = (ListView) findViewById(R.id.ListView_family);
        family.add(new dictionary(getString(R.string.family_father), "әpә", R.drawable.family_father, R.raw.family_father));
        family.add(new dictionary(getString(R.string.family_mother), "әṭa", R.drawable.family_mother, R.raw.family_mother));
        family.add(new dictionary(getString(R.string.family_son), "angsi", R.drawable.family_son, R.raw.family_son));
        family.add(new dictionary(getString(R.string.family_daughter), "tune", R.drawable.family_daughter, R.raw.family_daughter));
        family.add(new dictionary(getString(R.string.family_oldbrother), "taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        family.add(new dictionary(getString(R.string.family_YoungBrother), "chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        family.add(new dictionary(getString(R.string.family_oldersister), "teṭe", R.drawable.family_older_sister, R.raw.family_older_sister));
        family.add(new dictionary(getString(R.string.family_YoungSister), "kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        family.add(new dictionary(getString(R.string.family_grandmother), "ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        family.add(new dictionary(getString(R.string.family_grandfather), "paapa", R.drawable.family_grandfather, R.raw.family_father));


        DictionaryAdapter dictionary = new DictionaryAdapter(this, R.layout.costom_layout, family, R.color.category_family);
        listview.setAdapter(dictionary);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();

                // Create and setup the {@link MediaPlayer} for the audio resource associated
                // with the current word
                mMediaPlayer = MediaPlayer.create(FamilyActivity.this, family.get(i).getSoundID());
                // Start the audio file
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(onCompletionListener);

            }
        });
    }
}
