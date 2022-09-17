package com.example.downloadcontent;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.commons.lang3.StringUtils;

import java.net.URI;
import java.util.Objects;

public class Reels_fragments extends Fragment {
    EditText editTextReels;
    TextView getReelsButton, downloadButtonReels;
    VideoView videoViewReels;
    private MediaController mediaController;
    String reelUrl = "1";
    String URL = "";
    private Uri uri2;

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
                URL = editTextReels.getText().toString().trim();
                if (URL.isEmpty()){
                    editTextReels.setError("Enter or paste link");
                }
                else {
                    String result2 = StringUtils.substringBefore(URL,"/?");
                    URL = result2+"/?__a=1";
                    Log.d("get",URL);
                    /*URL = editTextReels.getText().toString().trim();
                    Log.d("get",URL);*/
                    processData();
                }
            }
        });

        downloadButtonReels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!reelUrl.equals("1")){
                    DownloadManager.Request request = new DownloadManager.Request(uri2);
                    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                    request.setTitle("Download");
                    request.setDescription("....");
                    request.allowScanningByMediaScanner();
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DCIM, ""+System.currentTimeMillis()+".mp4");
                    DownloadManager downloadManager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
                    downloadManager.enqueue(request);
                    Toast.makeText(getContext(), "Downloaded", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(), "No video to download", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    private void processData(){
        StringRequest request = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                MainURL mainURL = gson.fromJson(response, MainURL.class);
                reelUrl = mainURL.getGraphql().getShortcode_media().getVideo_url();
                uri2 = Uri.parse(reelUrl);
                videoViewReels.setMediaController(mediaController);
                videoViewReels.setVideoURI(uri2);
                videoViewReels.requestFocus();
                videoViewReels.start();
                /*Log.d("uri", String.valueOf(uri2));*/
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                editTextReels.setError("link not valid");
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }
}
