package com.example.downloadcontent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Videos_fragments extends Fragment {
    EditText editTextVideo;
    TextView getVideoButton, downloadButtonVideo;
    VideoView videoViewVideo;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_fragments, null);
        editTextVideo = view.findViewById(R.id.linkTextVideo);
        getVideoButton = view.findViewById(R.id.getContentButtonVideo);
        downloadButtonVideo = view.findViewById(R.id.downloadButtonVideo);
        videoViewVideo = view.findViewById(R.id.particularVideo);
        return view;
    }
}
