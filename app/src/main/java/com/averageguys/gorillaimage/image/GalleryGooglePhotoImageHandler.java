package com.averageguys.gorillaimage.image;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Omatt on 4/3/2015.
 */
public class GalleryGooglePhotoImageHandler extends AsyncTask<String, Void, Bitmap> {
    private static final String TAG = "GooglePhotoImageHandler";

    private ImageView imageView;
    private Activity activity;
    private String screen;

    public GalleryGooglePhotoImageHandler(ImageView imageView, Activity activity, String TAG) {
        this.imageView = imageView;
        this.activity = activity;
        this.screen = TAG;
    }

    protected Bitmap doInBackground(String... urls) {
        String urlDisplay = urls[0];
        Bitmap mBitmap = null;
        try {
            if (urlDisplay.startsWith("content://com.google.android.apps.photos.content")) {
                try {
                    InputStream mInputStream = activity.getContentResolver().openInputStream(Uri.parse(urlDisplay));

                    // Create new file for image
                    try {
                        ImageFileHandler.createImageFile();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    FileOutputStream fileOutput = new FileOutputStream(ImageFileHandler.mCurrentPhotoPath);

                    int totalSize = mInputStream.available();
                    int downloadedSize = 0;
                    byte[] buffer = new byte[1024];
                    int bufferLength = 0;
                    while ((bufferLength = mInputStream.read(buffer)) > 0) {
                        fileOutput.write(buffer, 0, bufferLength);
                        downloadedSize += bufferLength;
                        Log.i("Progress:", "downloadedSize:" + downloadedSize + "totalSize:" + totalSize);
                    }
                    fileOutput.close();
                    if (downloadedSize == totalSize) {
                        Log.i(TAG, "SUCCESSFUL DOWNLOAD! Image Path: " + ImageFileHandler.mCurrentPhotoPath);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Download Exception: " + e);
                }
            }
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mBitmap;
    }

    protected void onPostExecute(Bitmap result) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                // TODO result
            }
        });
        GalleryImageHandler.setImageFromGallery(imageView, ImageFileHandler.mCurrentPhotoPath);
    }
}
