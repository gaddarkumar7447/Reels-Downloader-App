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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.commons.lang3.StringUtils;


public class ProfilePic_Fragment extends Fragment {
    EditText editTextPic;
    TextView getPicButton, downloadButtonPic;
    ImageView imageViewPic;

    String reelUrl = "1";
    String URL = "";
    private Uri uri2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_pic_fragment, null);
        editTextPic = view.findViewById(R.id.linkTextPic);
        getPicButton = view.findViewById(R.id.getContentButtonPic);
        downloadButtonPic = view.findViewById(R.id.downloadButtonPic);
        imageViewPic = view.findViewById(R.id.particularPic);

        getPicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URL = editTextPic.getText().toString().trim();
                if (URL.isEmpty()){
                    editTextPic.setError("Enter or paste link");
                }
                else {
                    String result2 = StringUtils.substringBefore(URL,"/?");
                    URL = result2+"/?__a=1";
                    Log.d("get",URL);
                    processData();
                }
            }
        });
        downloadButtonPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!reelUrl.equals("1")){
                    DownloadManager.Request request = new DownloadManager.Request(uri2);
                    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                    request.setTitle("Download");
                    request.setDescription("....");
                    request.allowScanningByMediaScanner();
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DCIM, ""+System.currentTimeMillis()+".jpg");
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
                reelUrl = mainURL.getGraphql().getShortcode_media().getDisplay_url();
                uri2 = Uri.parse(reelUrl);
                Glide.with(getContext()).load(uri2).into(imageViewPic);

                /*Log.d("uri", String.valueOf(uri2));*/
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                editTextPic.setError("link not valid");
                /*Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();*/
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }
}
