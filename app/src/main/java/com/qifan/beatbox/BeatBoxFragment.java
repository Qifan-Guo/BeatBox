package com.qifan.beatbox;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class BeatBoxFragment extends Fragment {


    public BeatBoxFragment() {
        // Required empty public constructor
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
        binding.recycleView.setAdapter(new SoundAdapter());
        return binding.getRoot();
    }



    private class SoundHolder extends RecyclerView.ViewHolder{

        private ListItemSoundBinding mBinding;
        private SoundHolder (ListItemSoundBinding binding){
            super(binding.getRoot());
            mBinding=binding;
        }
    }
    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder>{

        @NonNull
        @Override
        public SoundHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater=LayoutInflater.from(getActivity());
            ListItemSoundBinding binding=DataBindingUtil.inflate(inflater,R.layout.list_item_sound,viewGroup,false);
            return new SoundHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull SoundHolder soundHolder, int i) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

}
