package com.qifan.beatbox;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.qifan.beatbox.databinding.FragmentBeatBoxBinding;
import com.qifan.beatbox.databinding.ListItemSoundBinding;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BeatBoxFragment extends Fragment {

    private BeatBox mBeatBox;

    public BeatBoxFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBeatBox=new BeatBox(getContext());
    }

    public static BeatBoxFragment newInstance(){
        return  new BeatBoxFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentBeatBoxBinding binding= DataBindingUtil.inflate(inflater,R.layout.fragment_beat_box,
                container,false);
        binding.recycleView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        binding.recycleView.setAdapter(new SoundAdapter(mBeatBox.getSounds()));
        return binding.getRoot();
    }



    private class SoundHolder extends RecyclerView.ViewHolder{

        private ListItemSoundBinding mBinding;
        private SoundHolder (ListItemSoundBinding binding){
            super(binding.getRoot());
            mBinding=binding;
            mBinding.setViewModel(new SoundViewModel(mBeatBox));

        }
        public void bind(Sound sound){
            mBinding.getViewModel().setSound(sound);
            mBinding.executePendingBindings();
        }
    }
    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder>{
        private List<Sound> mSounds;
        public SoundAdapter(List<Sound> sounds){
            mSounds=sounds;
        }

        @NonNull
        @Override
        public SoundHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater=LayoutInflater.from(getActivity());
            ListItemSoundBinding binding=DataBindingUtil.inflate(inflater,R.layout.list_item_sound,viewGroup,false);
            return new SoundHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull SoundHolder soundHolder, int i) {
            Sound sound = mSounds.get(i);
            soundHolder.bind(sound);
        }

        @Override
        public int getItemCount() {
            return mSounds.size();
        }
    }

}
