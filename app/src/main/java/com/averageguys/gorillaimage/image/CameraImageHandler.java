package com.averageguys.gorillaimage.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

/**
 * Created by Omatt on 4/3/2015.
 */
public class CameraImageHandler {

    public static void setImageFromCamera(ImageView mImageView) {
        // Get the dimensions of the View
        int targetW = mImageView.getWidth();
        int targetH = mImageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(ImageFileHandler.mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap mBitmap = BitmapFactory.decodeFile(ImageFileHandler.mCurrentPhotoPath, bmOptions);
        mBitmap = ImageHelper.rotateBitmap(mBitmap, bmOptions, ImageFileHandler.mCurrentPhotoPath);
        ImageFileHandler.saveRotatedImage(mBitmap, ImageFileHandler.mCurrentPhotoPath);
        mImageView.setImageBitmap(mBitmap);
    }
}
