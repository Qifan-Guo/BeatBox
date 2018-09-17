package com.qifan.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BeatBox {
    private static final int MAX_SOUND=5;

    private static final String TAG="BeatBox";
    private static final String SOUNDS_FOLDER="sample_sounds";
    private AssetManager mAssetManager;
    private List<Sound> mSounds=new ArrayList<>();
    private SoundPool mSoundPool;

public BeatBox(Context context){
    mAssetManager=context.getAssets();
    mSoundPool=new SoundPool.Builder().setMaxStreams(MAX_SOUND).setAudioAttributes(new AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build()).build();

    loadSounds();
}

private void load(Sound sound) throws IOException{
    AssetFileDescriptor assetFileDescriptor=
            mAssetManager.openFd(sound.getAssetPath());
    int soundId=mSoundPool.load(assetFileDescriptor,1);
    sound.setSoundID(soundId);

}
public void loadSounds(){
    String[] soundNames = null;
    try{
        soundNames=mAssetManager.list(SOUNDS_FOLDER);
        Log.i(TAG, "loadSounds: Found"+soundNames.length+"sounds");
    }catch (IOException ioe){
        Log.e(TAG, "loadSounds: Could not find the list asset",ioe );
    }
    for(String filename : soundNames){
        try{
        String assetPath=SOUNDS_FOLDER+"/"+filename;
        Sound sound=new Sound(assetPath);
        load(sound);
        mSounds.add(sound);
        } catch (IOException ioe){
            Log.e(TAG, "loadSounds: Could not load Sound"+filename,ioe );

        }
    }

}
public void release(){
    mSoundPool.release();
}

public void play(Sound sound){
    Integer soundId=sound.getSoundID();
    if(soundId == null){
        return;
    }
    mSoundPool.play(soundId,1.0f,1.0f,1,0,1.0f);
}

    public List<Sound> getSounds() {
        return mSounds;
    }
}
