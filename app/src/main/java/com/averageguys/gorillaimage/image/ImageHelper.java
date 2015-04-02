package com.averageguys.gorillaimage.image;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.widget.ImageView;

import java.io.IOException;

/**
 * Created by Omatt on 4/2/2015.
 */
public class ImageHelper {
    /**
     * Scale bitmap from resource to target view
     *
     * @param viewWidth Target view width
     * @param viewHeight Target view height
     * @param res Activity resource
     * @param resId Drawable resource
     * @return  Scaled bitmap
     * @see     android.graphics.Bitmap
     * @see     android.graphics.BitmapFactory
     */
    public static Bitmap setImageBitmapFromResource(int viewWidth, int viewHeight, Resources res, int resId) {
        // Get the dimensions of the View
        int targetW = viewWidth;
        int targetH = viewHeight;

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        BitmapFactory.decodeResource(res, resId);
        bmOptions.inJustDecodeBounds = true;
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;

        return BitmapFactory.decodeResource(res, resId, bmOptions);
    }

    /**
     * Scale bitmap from resource to target ImageView
     *
     * @param mImageView Target ImageView
     * @param res Activity resource
     * @param resId Drawable resource
     * @return  Scaled bitmap
     * @see     android.graphics.Bitmap
     * @see     android.graphics.BitmapFactory
     */
    public static Bitmap setImageBitmapFromResource(ImageView mImageView, Resources res, int resId) {
        // Get the dimensions of the View
        int targetW = mImageView.getWidth();
        int targetH = mImageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        BitmapFactory.decodeResource(res, resId);
        bmOptions.inJustDecodeBounds = true;
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;

        return BitmapFactory.decodeResource(res, resId, bmOptions);
    }

    public static Bitmap rotateBitmap(Bitmap mBitmap, BitmapFactory.Options bounds, String mCurrentPhotoPath) {
        Bitmap rotatedBitmap = null;
        try {
            ExifInterface exif = new ExifInterface(mCurrentPhotoPath);
            String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
            int orientation = orientString != null ? Integer.parseInt(orientString) : ExifInterface.ORIENTATION_NORMAL;

            int rotationAngle = 0;
            if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
            if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
            if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;

            Matrix matrix = new Matrix();
            matrix.setRotate(rotationAngle, (float) mBitmap.getWidth() / 2, (float) mBitmap.getHeight() / 2);
            rotatedBitmap = Bitmap.createBitmap(mBitmap, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rotatedBitmap;
    }
}
