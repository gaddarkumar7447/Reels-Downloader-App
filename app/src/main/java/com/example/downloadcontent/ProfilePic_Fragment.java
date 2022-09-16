package com.example.downloadcontent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class ProfilePic_Fragment extends Fragment {
    EditText editTextPic;
    TextView getPicButton, downloadButtonPic;
    ImageView imageViewPic;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_pic_fragment, null);
        editTextPic = view.findViewById(R.id.linkTextPic);
        getPicButton = view.findViewById(R.id.getContentButtonPic);
        downloadButtonPic = view.findViewById(R.id.downloadButtonPic);
        imageViewPic = view.findViewById(R.id.particularPic);
        downloadButtonPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextPic.setError("error");
            }
        });
        return view;

    }
}
