package com.example.everydaycroquis;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.InputStream;
import java.net.URL;

public class FragmentRunning extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LoadImage loadImage = new LoadImage();
        loadImage.execute("http://www.k-health.com/news/photo/201703/29397_15835_3746.jpg");
        return inflater.inflate(R.layout.fragment_running, container,false);
    }

    public void drawImage(Bitmap bitmap){
        ImageView imageView = (ImageView) getView().findViewById(R.id.imageView);
        imageView.setImageBitmap(bitmap);
    }

    private class LoadImage extends AsyncTask<String, String, Bitmap>{
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... args) {
            Bitmap bitmap = null;
            try{
                URL url = new URL(args[0]);
                bitmap = BitmapFactory.decodeStream((InputStream)url.getContent());
            }
            catch(Exception e){
                e.printStackTrace();
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if(bitmap!=null){
                drawImage(bitmap);
            }
            else{
                Toast.makeText(getView().getContext(), "Image not found", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
