package com.example.videodonwloader.extractor;

import android.content.Context;
import android.util.SparseArray;

import androidx.annotation.NonNull;

public abstract  class YoutubeUriExtractor extends YoutubeExtractor {
    public YoutubeUriExtractor(@NonNull Context con) {
        super(con);
    }
    @Override
    protected void onExtractionComplete(SparseArray<YTFile> ytFiles, VideoMeta videoMeta) {
        onUrisAvailable(videoMeta.getVideoId(), videoMeta.getTitle(), ytFiles);
    }

    public abstract void onUrisAvailable(String videoId, String videoTitle, SparseArray<YTFile> ytFiles);
}
