
package com.example.m0d1x.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {
    ListView listview;
    ArrayList<dictionary> Phrases = new ArrayList<>();
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
        setContentView(R.layout.activity_phrases);
        listview = (ListView) findViewById(R.id.ListView_phrases);


        Phrases.add(new dictionary(getString(R.string.phrase_where_are_you_going), "minto wuksus", R.drawable.question, R.raw.phrase_where_are_you_going));
        Phrases.add(new dictionary(getString(R.string.phrase_whats_your_name), "tinnә oyaase'nә", R.drawable.question, R.raw.phrase_what_is_your_name));
        Phrases.add(new dictionary(getString(R.string.phrase_my_name_is), "oyaaset", R.drawable.question, R.raw.phrase_my_name_is));
        Phrases.add(new dictionary(getString(R.string.phrase_how_are_you_feeling), "michәksәs", R.drawable.question, R.raw.phrase_how_are_you_feeling));
        Phrases.add(new dictionary(getString(R.string.phrase_i_am_feeling_good), "kuchi achit", R.drawable.question, R.raw.phrase_im_feeling_good));
        Phrases.add(new dictionary(getString(R.string.phrase_are_you_coming), "әәnәs'aa?", R.drawable.question, R.raw.phrase_are_you_coming));
        Phrases.add(new dictionary(getString(R.string.phrase_yes_i_am_coming), "hәә’ әәnәm", R.drawable.question, R.raw.phrase_yes_im_coming));
        Phrases.add(new dictionary(getString(R.string.phrases_I_m_coming), "әәnәm", R.drawable.question, R.raw.phrase_im_coming));
        Phrases.add(new dictionary(getString(R.string.phrases_Lets_go), "yoowutis", R.drawable.question, R.raw.phrase_lets_go));
        Phrases.add(new dictionary(getString(R.string.phrases_come_here), "әnni'nem", R.drawable.question, R.raw.phrase_come_here));


        DictionaryAdapter dictionary = new DictionaryAdapter(this, R.layout.costom_layout, Phrases, R.color.category_phrases);
        listview.setAdapter(dictionary);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();

                // Create and setup the {@link MediaPlayer} for the audio resource associated
                // with the current word
                mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, Phrases.get(i).getSoundID());
                // Start the audio file
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(onCompletionListener);

            }
        });
    }
}
