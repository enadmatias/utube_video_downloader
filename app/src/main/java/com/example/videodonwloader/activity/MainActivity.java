package com.example.videodonwloader.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import com.example.videodonwloader.R;
import com.example.videodonwloader.extractor.YTFile;
import com.example.videodonwloader.extractor.YoutubeUriExtractor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    String downloadLink="https://youtu.be/QhL-Rv5VH7o";
    String newLink;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.e("Permission error","You have permission");
                haveStoragePermission();
            }
        }

    }
    public void DownloadMyVideo(View view) {
        if(haveStoragePermission()) {
            YoutubeUriExtractor youTubeUriExtractor = new YoutubeUriExtractor(MainActivity.this) {
                @Override
                public void onUrisAvailable(String videoId, String videoTitle, SparseArray<YTFile> ytFiles) {
                    if (ytFiles != null) {
                        int tag = 22;
                        newLink = ytFiles.get(tag).getUrl();
                        String title = "MyDrama";
                        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(newLink));
                        request.setTitle(title);
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, title + ".mp4");
                        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                        request.allowScanningByMediaScanner();
                        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                        downloadManager.enqueue(request);
                    }
                }

            };
            youTubeUriExtractor.execute(downloadLink);
        }
    }
    public  boolean haveStoragePermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.e("Permission error","You have permission");
                return true;
            } else {

                Log.e("Permission error","You have asked for permission");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //you dont need to worry about these stuff below api level 23
            Log.e("Permission error","You already have the permission");
            return true;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            //you have the permission now.
            YoutubeUriExtractor youTubeUriExtractor = new YoutubeUriExtractor(MainActivity.this) {
                @Override
                public void onUrisAvailable(String videoId, String videoTitle, SparseArray<YTFile> ytFiles) {
                    if (ytFiles != null) {
                        int tag = 22;
                        newLink = ytFiles.get(tag).getUrl();
                        String title = "MyDrama";
                        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(newLink));
                        request.setTitle(title);
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, title + ".mp4");
                        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                        request.allowScanningByMediaScanner();
                        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                        downloadManager.enqueue(request);
                    }
                }

            };
            youTubeUriExtractor.execute(downloadLink);
        }
    }

}