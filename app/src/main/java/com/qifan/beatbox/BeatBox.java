package com.qifan.beatbox;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BeatBox {
private static final String TAG="BeatBox";
private static final String SOUNDS_FOLDER="sample_sounds";
private AssetManager mAssetManager;
private List<Sound> mSounds=new ArrayList<>();

public BeatBox(Context context){
    mAssetManager=context.getAssets();
    loadSounds();
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
        String assetPath=SOUNDS_FOLDER+"/"+filename;
        Sound sound=new Sound(assetPath);
        mSounds.add(sound);
    }

}

    public List<Sound> getSounds() {
        return mSounds;
    }
}
