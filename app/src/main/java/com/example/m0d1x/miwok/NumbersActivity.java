/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.m0d1x.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    //ArrayList To hold all items
    ArrayList<dictionary> NumberItems = new ArrayList<>();
    ListView listview;

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
        setContentView(R.layout.activity_numbers);

        listview = (ListView) findViewById(R.id.ListView_numbers);


        // Create a list of words
        NumberItems.add(new dictionary(getString(R.string.number_one), "lutti", R.drawable.number_one, R.raw.number_one));
        NumberItems.add(new dictionary(getString(R.string.number_two), "otiiko", R.drawable.number_two, R.raw.number_two));
        NumberItems.add(new dictionary(getString(R.string.number_three), "tolookosu", R.drawable.number_three, R.raw.number_three));
        NumberItems.add(new dictionary(getString(R.string.number_four), "oyyisa", R.drawable.number_four, R.raw.number_four));
        NumberItems.add(new dictionary(getString(R.string.number_five), "massokka", R.drawable.number_five, R.raw.number_five));
        NumberItems.add(new dictionary(getString(R.string.nmber_six), "temmokka", R.drawable.number_six, R.raw.number_six));
        NumberItems.add(new dictionary(getString(R.string.nmber_seven), "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        NumberItems.add(new dictionary(getString(R.string.nmber_eight), "kawinta", R.drawable.number_eight, R.raw.number_eight));
        NumberItems.add(new dictionary(getString(R.string.nmber_nine), "wo’e", R.drawable.number_nine, R.raw.number_nine));
        NumberItems.add(new dictionary(getString(R.string.nmber_ten), "na’aacha", R.drawable.number_ten, R.raw.number_ten));


        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        DictionaryAdapter dictionary = new DictionaryAdapter(this, R.layout.costom_layout, NumberItems);


        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listview.setAdapter(dictionary);


        // Set a click listener to play the audio when the list item is clicked on
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();

                // Create and setup the {@link MediaPlayer} for the audio resource associated
                // with the current word
                mMediaPlayer = MediaPlayer.create(NumbersActivity.this, NumberItems.get(i).getSoundID());
                // Start the audio file
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(onCompletionListener);

            }
        });

    }

}
