package com.example.downloadcontent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Photos_fragments extends Fragment {
    EditText editTextPhoto;
    TextView getPhotoButton, downloadButton;
    ImageView imageViewPhoto;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.photo_fragment, null);
        editTextPhoto = view.findViewById(R.id.linkTextPhoto);
        getPhotoButton = view.findViewById(R.id.getContentButtonPhoto);
        downloadButton = view.findViewById(R.id.downloadButtonPhoto);
        imageViewPhoto = view.findViewById(R.id.particularPhoto);
        return view;
    }
}
