package com.averageguys.gorillaimage.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by Omatt on 4/3/2015.
 */
public class GalleryImageHandler {
    private static final String TAG = "GalleryImageHandler";

    public static void setImageFromGallery(ImageView mImageView, String mCurrentPhotoPath) {
        // Get the dimensions of the View
        int targetW = mImageView.getWidth();
        int targetH = mImageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap mBitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        try {
            mBitmap = ImageHelper.rotateBitmap(mBitmap, bmOptions, mCurrentPhotoPath);
            ImageFileHandler.saveRotatedImage(mBitmap, mCurrentPhotoPath);
            mImageView.setImageBitmap(mBitmap);
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e);
            Log.e(TAG, "Exception mCurrentPhotoPath: " + mCurrentPhotoPath);
        }
    }
}
