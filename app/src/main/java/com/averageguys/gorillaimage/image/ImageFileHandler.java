package com.averageguys.gorillaimage.image;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Omatt on 4/3/2015.
 */
public class ImageFileHandler {
    private static final String TAG = "ImageFileHandler";
    public static String mCurrentPhotoPath;

    public static File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public static void saveRotatedImage(Bitmap mBitmap, String mCurrentPhotoPath) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(mCurrentPhotoPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG, "File Not Found: " + e);
        }
        if (out != null) {
            Log.e(TAG, "Save image success. File path: " + mCurrentPhotoPath);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        }
    }
}
