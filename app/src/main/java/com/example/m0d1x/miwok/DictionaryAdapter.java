package com.example.m0d1x.miwok;


import android.content.Context;
import android.media.Image;
import android.media.SoundPool;
import android.support.annotation.AnimatorRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


import android.media.AudioManager;

public class DictionaryAdapter extends ArrayAdapter<dictionary> {
    static class ViewHolder {
        ImageView picID;
        ImageView Play;
        TextView Word;
        TextView Transtation;

    }

    public static final String tag = "DictionaryAdapter";
    private Context mcontext;
    private int mResource;
    SoundPool mSoundPool;
    int mCSoundId = 0;

    public DictionaryAdapter(Context context, int resource, ArrayList<dictionary> objects) {
        super(context, resource, objects);
        this.mcontext = context;
        this.mResource = resource;
    }

    @Override
    public View getView(int i, View converView, ViewGroup parent) {


        // Member Variables

        String aword = getItem(i).getWord();
        String atranstation = getItem(i).getTranslate();
        int picid = getItem(i).getPicID();
        int sound = getItem(i).getSoundID();

        dictionary d = new dictionary(aword, atranstation, picid, sound);

        //create the view result for showing the animation

        LayoutInflater infuLayoutInflater = LayoutInflater.from(mcontext);
        converView = infuLayoutInflater.inflate(mResource, parent, false);
        ViewHolder holder = new ViewHolder();
        holder.picID = (ImageView) converView.findViewById(R.id.img_pic);
        holder.Play = (ImageView) converView.findViewById(R.id.img_play);
        holder.Word = (TextView) converView.findViewById(R.id.txt_word);
        holder.Transtation = (TextView) converView.findViewById(R.id.txt_translation);

        converView.setTag(holder);

        holder.picID.setImageResource(d.getPicID());

        holder.Word.setText(d.getWord());
        holder.Transtation.setText(d.getTranslate());

        return converView;
    }
}