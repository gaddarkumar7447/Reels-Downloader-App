package com.example.downloadcontent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Igtv_fragments extends Fragment {
    EditText editTextIgtv;
    TextView getIgtvButton, downloadButtonIgtv;
    VideoView videoViewIgtv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.igtv_fragment, null);
        editTextIgtv = view.findViewById(R.id.linkTextIgtv);
        getIgtvButton = view.findViewById(R.id.getContentButtonIgtv);
        downloadButtonIgtv = view.findViewById(R.id.downloadButtonIgtv);
        videoViewIgtv = view.findViewById(R.id.particularIgtv);

        return view;
    }
}

