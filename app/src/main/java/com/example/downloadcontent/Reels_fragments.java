package com.example.downloadcontent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.net.URI;

public class Reels_fragments extends Fragment {
    EditText editTextReels;
    TextView getReelsButton, downloadButtonReels;
    VideoView videoViewReels;
    private MediaController mediaController;
    String reelUrl = "1";
    String URL = "Null";
    private URI uri2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reels_gragments, null);
        editTextReels = view.findViewById(R.id.linkTextReels);
        getReelsButton = view.findViewById(R.id.getContentButtonReel);
        downloadButtonReels = view.findViewById(R.id.downloadButtonReel);
        videoViewReels = view.findViewById(R.id.particularReel);

        mediaController = new MediaController(getContext());
        mediaController.setAnchorView(videoViewReels);
        getReelsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




        return view;
    }
}
